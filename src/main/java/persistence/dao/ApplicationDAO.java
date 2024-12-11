package main.java.persistence.dao;
import main.java.persistence.PooledDataSource;
import main.java.persistence.dto.ApplicationDTO;


import javax.sql.DataSource;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.*;

public class ApplicationDAO {
    private final DataSource ds = PooledDataSource.getDataSource();


    // 전체 입사신청서 조회
    public List<ApplicationDTO> getAllApplications() {
        String SQL = "SELECT * FROM application";
        List<ApplicationDTO> applications = new ArrayList<>();

        try (Connection conn = ds.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                ApplicationDTO application = new ApplicationDTO();
                application.setApplicationId(rs.getInt("ApplicationId"));
                application.setApplicationDate(rs.getTimestamp("ApplicationDate").toLocalDateTime());
                application.setMeal(rs.getInt("Meal"));
                application.setPreference(rs.getInt("Preference"));
                application.setStudentId(rs.getInt("StudentId"));
                application.setDormitoryId(rs.getInt("DormitoryId"));
                applications.add(application);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return applications;
    }

    // 특정 학생의 입사 신청서 조회
    public List<ApplicationDTO> getApplicationsByStudentId(int studentId) {
        String SQL = "SELECT * FROM application WHERE StudentId = ?";
        List<ApplicationDTO> applications = new ArrayList<>();

        try (Connection conn = ds.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL)) {

            stmt.setInt(1, studentId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    ApplicationDTO application = new ApplicationDTO();
                    application.setApplicationId(rs.getInt("ApplicationId"));
                    application.setApplicationDate(rs.getTimestamp("ApplicationDate").toLocalDateTime());
                    application.setMeal(rs.getInt("Meal"));
                    application.setPreference(rs.getInt("Preference"));
                    application.setStudentId(rs.getInt("StudentId"));
                    application.setDormitoryId(rs.getInt("DormitoryId"));
                    applications.add(application);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return applications;
    }



    // 입사 신청하기 (한 학생은 1~3지망의 기숙사 입시신청을 할 수 있음 즉 3개 생성)       -> 최대 2지망으로 변경
    // applicationId, preference 복합키
    // 학생의 성별과 기숙사의 성별 정보가 일치해야 데이터 삽입이 가능하게
    public boolean applyForDormitoryBatch(int studentId, int meal, List<Integer> dormitoryPreferences) {

        // 학생당 최대 3개의 신청만 가능 (WHERE (SELECT COUNT(*) FROM application WHERE StudentId = ?) < 3)
        // 더미 생성
        String CONDITIONAL_INSERT_SQL = """
            INSERT INTO application (ApplicationId, ApplicationDate, Meal, Preference, StudentId, DormitoryId)
            SELECT ?, NOW(), ?, ?, ?, ?
            FROM DUAL
            WHERE (SELECT COUNT(*) FROM application WHERE StudentId = ?) < 3
            """;

        // 새로운 applicationId를 생성하는 쿼리로 기존 최대값에 1 증가
        String GET_NEXT_ID_SQL = "SELECT IFNULL(MAX(ApplicationId), 0) + 1 AS nextId FROM application";

        // 학생의 성별정보 조회
        String GET_STUDENT_GENDER_SQL = "SELECT Gender FROM student WHERE StudentId = ?";

        // 기숙사 성별정보 조회
        String GET_DORMITORY_GENDER_SQL = "SELECT Gender FROM dormitory WHERE DormitoryId = ?";

        try (Connection conn = ds.getConnection();
             PreparedStatement getNextIdStmt = conn.prepareStatement(GET_NEXT_ID_SQL);
             PreparedStatement insertStmt = conn.prepareStatement(CONDITIONAL_INSERT_SQL);
             PreparedStatement getStudentGenderStmt = conn.prepareStatement(GET_STUDENT_GENDER_SQL);
             PreparedStatement getDormitoryGenderStmt = conn.prepareStatement(GET_DORMITORY_GENDER_SQL)) {

            // 1. ApplicationId 생성
            int applicationId = 1; // 기본값
            try (ResultSet rs = getNextIdStmt.executeQuery()) {
                if (rs.next()) {
                    applicationId = rs.getInt("nextId");
                }
            }

            // 2. 학생의 성별 조회
            int studentGender = 0;
            getStudentGenderStmt.setInt(1, studentId);
            try (ResultSet rs = getStudentGenderStmt.executeQuery()) {
                if (rs.next()) {
                    studentGender = rs.getInt("Gender");
                }
            }

            // 3. 배치 실행
            for (int i = 0; i < dormitoryPreferences.size(); i++) {
                int preference = i + 1; // 1지망, 2지망, 3지망 설정
                int dormitoryId = dormitoryPreferences.get(i);

                // 기숙사의 성별 확인
                int dormitoryGender = 0;
                getDormitoryGenderStmt.setInt(1, dormitoryId);
                try (ResultSet rs = getDormitoryGenderStmt.executeQuery()) {
                    if (rs.next()) {
                        dormitoryGender = rs.getInt("Gender");
                    }
                }

                // 학생의 성별과 기숙사의 성별이 일치하지 않으면 신청 불가
                if (studentGender != dormitoryGender) {
                    System.out.println("학생의 성별과 기숙사의 성별이 일치하지 않아 해당 기숙사에 신청이 불가합니다.");
                    return false;
                }

                // 삽입 실행
                insertStmt.setInt(1, applicationId); // 동일한 ApplicationId 사용
                insertStmt.setInt(2, meal); // 식사 옵션
                insertStmt.setInt(3, preference); // 지망 순위
                insertStmt.setInt(4, studentId); // 학생 ID
                insertStmt.setInt(5, dormitoryId); // 기숙사 ID
                insertStmt.setInt(6, studentId); // 조건: 신청 개수 확인

                int rowsAffected = insertStmt.executeUpdate();
                if (rowsAffected == 0) {
                    System.out.println("한 학생은 최대 3개의 입사 신청만 가능합니다.");
                    return false;
                }
            }

            return true; // 삽입 성공
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }



}
