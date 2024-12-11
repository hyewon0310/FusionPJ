package main.java.persistence.dao;
import main.java.persistence.PooledDataSource;
import main.java.persistence.dto.DormitoryDTO;

import java.sql.Connection;
import javax.sql.DataSource;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class DormitoryDAO {
    private final DataSource ds = PooledDataSource.getDataSource();

    private Connection coneection;
    public DormitoryDAO(Connection connection){
        this.coneection = connection;
    }

    // 생활관 정보 전체 조회   DormitoryId, DormitoryName, DormitoryType,
    public List<DormitoryDTO> getAllDormitories() {
        String SQL = """
                SELECT Cost, Gender, Capacity
                FROM dormitory
                """;
        List<DormitoryDTO> dormitories = new ArrayList<>();

        try (Connection conn = ds.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SQL)) {

            while (rs.next()) {
                DormitoryDTO dormitory = new DormitoryDTO();
              //  dormitory.setDormitoryId(rs.getInt("DormitoryId"));
              //  dormitory.setDormitoryName(rs.getString("DormitoryName"));
              //  dormitory.setDormitoryType(rs.getInt("DormitoryType"));
                dormitory.setDormitoryCost(rs.getInt("Cost"));
                dormitory.setGender(rs.getBoolean("Gender"));
                dormitory.setCapacity(rs.getInt("Capacity"));

                dormitories.add(dormitory);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dormitories;
    }

    // 특정 생활관 정보 조회
    public DormitoryDTO getDormitoryById(int dormitoryId) {
        String SQL = """
                SELECT DormitoryId, DormitoryName, DormitoryType, Cost, Gender, Capacity
                FROM dormitory
                WHERE DormitoryId = ?
                """;
        DormitoryDTO dormitory = null;

        try (Connection conn = ds.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL)) {

            stmt.setInt(1, dormitoryId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    dormitory = new DormitoryDTO();
                    dormitory.setDormitoryId(rs.getInt("DormitoryId"));
                    dormitory.setDormitoryName(rs.getString("DormitoryName"));
                    dormitory.setDormitoryType(rs.getInt("DormitoryType"));
                    dormitory.setDormitoryCost(rs.getInt("Cost"));
                    dormitory.setGender(rs.getBoolean("Gender"));
                    dormitory.setCapacity(rs.getInt("Capacity"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dormitory;
    }

    // Dormitory 데이터 추가
    public void insertDormitory(DormitoryDTO dormitory) {
        String SQL = """
                INSERT INTO dormitory (DormitoryId, DormitoryName, DormitoryType, Cost, Gender, Capacity)
                VALUES (?, ?, ?, ?, ?, ?)
                """;
        try (Connection conn = ds.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL)) {

            stmt.setInt(1, dormitory.getDormitoryId());
            stmt.setString(2, dormitory.getDormitoryName());
            stmt.setInt(3, dormitory.getDormitoryType());
            stmt.setInt(4, dormitory.getDormitoryCost());
            stmt.setBoolean(5, dormitory.isGender());
            stmt.setInt(6, dormitory.getCapacity());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Test 용
    public String findDormitoryId(String dormitoryName, String dormitoryType, String gender) {
        String SQL = """
                SELECT DormitoryId 
                FROM dormitory 
                WHERE DormitoryName = ? 
                  AND DormitoryType = ? 
                  AND Gender = ?
                """;

        try (Connection conn = ds.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {

            pstmt.setString(1, dormitoryName);
            pstmt.setString(2, dormitoryType);
            pstmt.setString(3, gender);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("DormitoryId");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //  Dormitory 수정
    public void updateDormitory(DormitoryDTO dormitory) {
        String SQL = """
                UPDATE dormitory
                SET DormitoryName = ?, DormitoryType = ?, Cost = ?, Gender = ?, Capacity = ?
                WHERE DormitoryId = ?
                """;
        try (Connection conn = ds.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL)) {

            stmt.setString(1, dormitory.getDormitoryName());
            stmt.setInt(2, dormitory.getDormitoryType());
            stmt.setInt(3, dormitory.getDormitoryCost());
            stmt.setBoolean(4, dormitory.isGender());
            stmt.setInt(5, dormitory.getCapacity());
            stmt.setInt(6, dormitory.getDormitoryId());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Dormitory 데이터 수정 완료: " + dormitory);
            } else {
                System.out.println("Dormitory 데이터 수정 실패: ID를 확인하세요.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
