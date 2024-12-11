//package Project;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Map;
//
//public class TempData {
//    public static List<String> fetchMockSchedules() {
//        System.out.println("[DEBUG] fetchMockSchedules() 호출");
//        return Arrays.asList(
//                "M"
//        );
//    }
//
//    private static final List<Map<String, Object>> UserData = new ArrayList<>();
//    private static final List<Map<String, Object>> UserTypeData = new ArrayList<>();
//
//    static {
//        initializeUserData();
//        initializeUserTypeData();
//    }
//
//    // USER 테이블 데이터 초기화
//    private static void initializeUserData() {
//        UserData.add(Map.of(
//                "아이디", "user001", // 사용자 아이디
//                "비번", "password123", // 사용자 비밀번호
//                "유저타입ID", 1 // 유저타입 ID 참조
//        ));
//        UserData.add(Map.of(
//                "아이디", "user002",
//                "비번", "password456",
//                "유저타입ID", 2
//        ));
//        UserData.add(Map.of(
//                "아이디", "user003",
//                "비번", "password789",
//                "유저타입ID", 2
//        ));
//    }
//
//    // 유저타입 테이블 데이터 초기화
//    private static void initializeUserTypeData() {
//        UserTypeData.add(Map.of(
//                "유저타입ID", 1, // 사용자 유형 ID
//                "유저명", "Admin" // 사용자 유형 이름
//        ));
//        UserTypeData.add(Map.of(
//                "유저타입ID", 2,
//                "유저명", "User"
//        ));
//    }
//}

package Project;

import java.util.*;

public class TempData {
        public static List<String> fetchMockSchedules() {
        System.out.println("[DEBUG] fetchMockSchedules() 호출");
        return Arrays.asList(
                "M"
        );
    }
    // 학생 데이터
    public static List<Map<String, Object>> StudentData = new ArrayList<>();

    // 생활관 데이터
    public static List<Map<String, Object>> DormitoryData = new ArrayList<>();

    // 초기화 블록
    static {
        initializeStudentData();
        initializeDormitoryData();
    }

    // 학생 데이터 초기화
    private static void initializeStudentData() {
        StudentData.add(Map.of("학생ID", "20231001", "이름", "김철수", "생활관ID", 1, "납부여부", false));
        StudentData.add(Map.of("학생ID", "20231002", "이름", "이영희", "생활관ID", 1, "납부여부", true));
        StudentData.add(Map.of("학생ID", "20231003", "이름", "박민수", "생활관ID", 2, "납부여부", false));
    }

    // 생활관 데이터 초기화
    private static void initializeDormitoryData() {
        DormitoryData.add(Map.of("생활관ID", 1, "생활관명", "A동", "사용료", 500000));
        DormitoryData.add(Map.of("생활관ID", 2, "생활관명", "B동", "사용료", 450000));
    }

    // 데이터 조회 메서드 (예: 학생ID로 검색)
    public static Map<String, Object> findStudentById(String studentId) {
        return StudentData.stream()
                .filter(student -> student.get("학생ID").equals(studentId))
                .findFirst()
                .orElse(null);
    }

    public static List<Map<String, Object>> findPaidStudentsByDormitory(int dormitoryId) {
        return StudentData.stream()
                .filter(student -> (int) student.get("생활관ID") == dormitoryId && (boolean) student.get("납부여부"))
                .toList();
    }

    public static List<Map<String, Object>> findUnpaidStudentsByDormitory(int dormitoryId) {
        return StudentData.stream()
                .filter(student -> (int) student.get("생활관ID") == dormitoryId && !(boolean) student.get("납부여부"))
                .toList();
    }
}
