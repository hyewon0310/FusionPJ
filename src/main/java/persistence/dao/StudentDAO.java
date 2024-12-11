package main.java.persistence.dao;
import main.java.persistence.PooledDataSource;
import main.java.persistence.dto.StudentDTO;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

public class StudentDAO {
    private final DataSource ds = PooledDataSource.getDataSource();

    // 모든 학생 정보 조회
    public List<StudentDTO> getAllStudents() {
        List<StudentDTO> students = new ArrayList<>();
        String SQL = """
                SELECT studentId, name, major, gender, address, studentTypeId, userId
                FROM student
                """;
        try (Connection conn = ds.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SQL)) {

            while (rs.next()) {
                StudentDTO student = new StudentDTO();
                student.setStudentId(rs.getInt("studentId"));
                student.setName(rs.getString("name"));
                student.setMajor(rs.getString("major"));
                student.setGender(rs.getBoolean("gender"));
                student.setAddress(rs.getString("address"));
                student.setStudentTypeId(rs.getInt("studentTypeId"));
                student.setUserId(rs.getString("userId"));

                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    // 특정 학생 정보 조회 (By StudentId)
    public StudentDTO getStudentById(int studentId) {
        StudentDTO student = null;
        String SQL = """
                SELECT studentId, name, major, gender, address, studentTypeId, userId
                FROM student
                WHERE studentId = ?
                """;
        try (Connection conn = ds.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL)) {

            stmt.setInt(1, studentId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    student = new StudentDTO();
                    student.setStudentId(rs.getInt("studentId"));
                    student.setName(rs.getString("name"));
                    student.setMajor(rs.getString("major"));
                    student.setGender(rs.getBoolean("gender"));
                    student.setAddress(rs.getString("address"));
                    student.setStudentTypeId(rs.getInt("studentTypeId"));
                    student.setUserId(rs.getString("userId"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

    // 학생 정보 추가
    public void addStudent(StudentDTO student) {
        String SQL = """
                INSERT INTO student (studentId, name, major, gender, address, studentTypeId, userId)
                VALUES (?, ?, ?, ?, ?, ?, ?)
                """;
        try (Connection conn = ds.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL)) {

            stmt.setInt(1, student.getStudentId());
            stmt.setString(2, student.getName());
            stmt.setString(3, student.getMajor());
            stmt.setBoolean(4, student.isGender());
            stmt.setString(5, student.getAddress());
            stmt.setInt(6, student.getStudentTypeId());
            stmt.setString(7, student.getUserId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 학생 정보 수정 (이름, 학과, 주소)
    public void updateStudent(int studentId, String name, String major, String address) {
        String SQL = """
                UPDATE student
                SET name = ?, major = ?, address = ?
                WHERE studentId = ?
                """;
        try (Connection conn = ds.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL)) {

            stmt.setString(1, name);
            stmt.setString(2, major);
            stmt.setString(3, address);
            stmt.setInt(4, studentId);

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 학생 정보 삭제
    public void deleteStudent(int studentId) {
        String SQL = """
                DELETE 
                FROM student
                WHERE studentId = ?
                """;
        try (Connection conn = ds.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL)) {

            stmt.setInt(1, studentId);

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
