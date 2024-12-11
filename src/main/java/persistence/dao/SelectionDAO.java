package main.java.persistence.dao;

import main.java.persistence.PooledDataSource;
import main.java.persistence.dto.SelectionDTO;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SelectionDAO {
    private final DataSource ds = PooledDataSource.getDataSource();


    // 우선순위별 입사신청자 리스트 조회 (1순위 : 대학원생, 경쟁이 있는 경우 리스트 조회 totalScore로 선발 예정)
    public List<SelectionDTO> getSelectedApplicants() {
        String SQL = """
                SELECT 
                    a.ApplicationId,
                    s.StudentId,
                    s.StudentTypeId, -- 대학원생(2)인지 학부생(1)인지
                    c.TotalScore,
                    d.DormitoryId,
                    d.Capacity
                FROM application a
                JOIN student s ON a.StudentId = s.StudentId
                JOIN calculation_score c ON a.ApplicationId = c.ApplicationId
                JOIN dormitory d ON a.DormitoryId = d.DormitoryId
                ORDER BY s.StudentTypeId DESC, -- 대학원생 우선
                         c.TotalScore DESC;  -- TotalScore로 정렬
                """;

        List<SelectionDTO> selectedApplicants = new ArrayList<>();

        try (Connection conn = ds.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                SelectionDTO dto = new SelectionDTO();
                dto.setApplicationId(rs.getInt("ApplicationId"));
                dto.setStudentId(rs.getInt("StudentId"));
                dto.setStudentTypeId(rs.getInt("StudentTypeId"));
                dto.setTotalScore(rs.getDouble("TotalScore"));
                dto.setDormitoryId(rs.getInt("DormitoryId"));
                dto.setCapacity(rs.getInt("Capacity"));
                selectedApplicants.add(dto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return selectedApplicants;
    }
}
