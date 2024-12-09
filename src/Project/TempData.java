package Project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class TempData {
    public static List<String> fetchMockSchedules() {
        System.out.println("[DEBUG] fetchMockSchedules() 호출");
        return Arrays.asList(
                "M"
        );
    }
    private static final List<Map<String, Object>> UserData = new ArrayList<>();
    private static final List<Map<String, Object>> UserTypeData = new ArrayList<>();

    static {
        initializeUserData();
        initializeUserTypeData();
    }

    // USER 테이블 데이터 초기화
    private static void initializeUserData() {
        UserData.add(Map.of(
                "아이디", "user001", // 사용자 아이디
                "비번", "password123", // 사용자 비밀번호
                "유저타입ID", 1 // 유저타입 ID 참조
        ));
        UserData.add(Map.of(
                "아이디", "user002",
                "비번", "password456",
                "유저타입ID", 2
        ));
        UserData.add(Map.of(
                "아이디", "user003",
                "비번", "password789",
                "유저타입ID", 2
        ));
    }

    // 유저타입 테이블 데이터 초기화
    private static void initializeUserTypeData() {
        UserTypeData.add(Map.of(
                "유저타입ID", 1, // 사용자 유형 ID
                "유저명", "Admin" // 사용자 유형 이름
        ));
        UserTypeData.add(Map.of(
                "유저타입ID", 2,
                "유저명", "User"
        ));
    }
}
