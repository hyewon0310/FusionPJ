package main.java.persistence.dao;

import main.java.persistence.PooledDataSource;
import main.java.persistence.dto.CalculationScoreDTO;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CalculationScoreDAO {
    private final DataSource ds = PooledDataSource.getDataSource();

    // 입사 신청자 점수 전체 조회
    public List<CalculationScoreDTO> getCalculationScores() {
        String SQL = "SELECT * FROM calculation_score";
        List<CalculationScoreDTO> result = new ArrayList<>();

        try (Connection conn = ds.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                CalculationScoreDTO dto = new CalculationScoreDTO();
                dto.setScoreId(rs.getInt("ScoreId"));
                dto.setApplicationId(rs.getInt("ApplicationId"));
                dto.setRegionName(rs.getString("RegionName"));
                dto.setTotalScore(rs.getDouble("TotalScore"));
                result.add(dto);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


    // 부여된 applicationId로 calculationScore 생성
    public boolean registerCalculationScoreByApplicationId(int applicationId) {
        String SQL = """
                INSERT INTO calculation_score (ScoreId, ApplicationId, RegionName, TotalScore)
                SELECT 
                    NULL AS ScoreId, -- AUTO_INCREMENT 
                    a.ApplicationId,
                    ds.RegionName AS RegionName,
                    (AVG(g.Grade) * 0.6 + s.DistanceScore * 0.4) AS TotalScore
                FROM application a
                JOIN student s ON a.StudentId = s.StudentId
                JOIN grade g ON s.StudentId = g.StudentId
                JOIN distance_score ds ON s.Address LIKE CONCAT('%', ds.RegionName, '%') 
                WHERE a.ApplicationId = ? -- 특정 ApplicationId만 처리
                  AND g.Semester IN (1, 2)
                GROUP BY a.ApplicationId, ds.RegionName, s.DistanceScore;
                """;

        try (Connection conn = ds.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {

            // applicationId 파라미터 설정
            pstmt.setInt(1, applicationId);

            int rowsInserted = pstmt.executeUpdate();
            System.out.println(rowsInserted + " rows inserted into calculation_score table for ApplicationId: " + applicationId);
            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
