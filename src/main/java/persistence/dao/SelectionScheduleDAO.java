package main.java.persistence.dao;
import main.java.persistence.PooledDataSource;
import main.java.persistence.dto.SelectionScheduleDTO;
import main.java.persistence.dto.StudentDTO;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class SelectionScheduleDAO {
    private static final DataSource ds = PooledDataSource.getDataSource();
//
//    // 1. 선발 일정 추가
//    static  public void registerSelectionSchedule(SelectionScheduleDTO schedule) {
//        String SQL = """
//                INSERT INTO selection_schedule (SelectionScheduleId, Name, Period)
//                VALUES (?, ?, ?)
//                """;
//        try (Connection conn = ds.getConnection();
//             PreparedStatement stmt = conn.prepareStatement(SQL)) {
//
//            stmt.setString(1, schedule.getSelectionScheduleId());
//            stmt.setString(2, schedule.getName());
//            stmt.setDate(3, Date.valueOf(schedule.getPeriod())); // LocalDate -> SQL Date 변환
//
//            stmt.executeUpdate();
//            System.out.println("선발 일정 추가 완료: " + schedule);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    // 2. 모든 선발 일정 조회 (Read All)
    static  public List<SelectionScheduleDTO> getAllSelectionSchedules(Connection conn) {
        List<SelectionScheduleDTO> schedules = new ArrayList<>();
        String SQL = """
                SELECT Period
                FROM selection_schedule
                """;
        try (//Connection conn = ds.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SQL)) {

            while (rs.next()) {
                SelectionScheduleDTO schedule = new SelectionScheduleDTO();
                schedule.setPeriod(rs.getDate("Period").toLocalDate()); // SQL Date -> LocalDate 변환
                schedules.add(schedule);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return schedules;
    }


    //
//    // 선발일정명, 기간 서버에서 파싱 예정
//    static  public SelectionScheduleDTO getScheduleNameById(String selectionScheduleId) {
//        String SQL = """
//                SELECT Name, Period
//                FROM selection_schedule
//                WHERE SelectionScheduleId = ?
//                """;
//
//        List<String> names = new ArrayList<>();
//        List<String> periods = new ArrayList<>();
//
//        try (Connection conn = ds.getConnection();
//             PreparedStatement stmt = conn.prepareStatement(SQL)) {
//
//            stmt.setString(1, selectionScheduleId);
//            try (ResultSet rs = stmt.executeQuery()) {
//                while (rs.next()) {
//                    names.add(rs.getString("Name"));
//                    periods.add(rs.getDate("Period").toString());
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return new SelectionScheduleDTO(names, periods);
//    }
//
//
//    // 4. 선발 일정 수정 (Update)
//    static public void updateSelectionSchedule(String selectionScheduleId, String name, Date period) {
//        String SQL = """
//                UPDATE selection_schedule
//                SET Period = ?
//                WHERE SelectionScheduleId = ? AND Name = ?
//                """;
//        try (Connection conn = ds.getConnection();
//             PreparedStatement stmt = conn.prepareStatement(SQL)) {
//
//            stmt.setDate(1, period);
//            stmt.setString(2, selectionScheduleId);
//            stmt.setString(3, name);
//
//            stmt.executeUpdate();
//            System.out.println("선발 일정 수정 완료");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    // 5. 선발 일정 삭제 (Delete)
//    static  public void deleteSelectionSchedule(String selectionScheduleId, String name) {
//        String SQL = """
//                DELETE FROM selection_schedule
//                WHERE SelectionScheduleId = ? AND Name = ?
//                """;
//        try (Connection conn = ds.getConnection();
//             PreparedStatement stmt = conn.prepareStatement(SQL)) {
//
//            stmt.setString(1, selectionScheduleId);
//            stmt.setString(2, name);
//
//            stmt.executeUpdate();
//            System.out.println("선발 일정 삭제 완료");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
    static  public List<SelectionScheduleDTO> getAllSelectionScheduleName(Connection conn) {
        List<SelectionScheduleDTO> schedules = new ArrayList<>();
        String SQL = """
                    SELECT Name
                    FROM selection_schedule
                    """;
        try (//Connection conn = ds.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SQL)) {

            while (rs.next()) {
                SelectionScheduleDTO schedule = new SelectionScheduleDTO();
                schedule.setName(rs.getString("Name"));

                schedules.add(schedule);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return schedules;
    }

}
