package Project;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import main.java.persistence.dao.*;
import main.java.persistence.dto.DormitoryDTO;
import main.java.persistence.dto.SelectionScheduleDTO;
import main.java.persistence.dto.StudentDTO;


//// 서버 스레드는 각 클라이언트의 요청을 처리하고 응답을 보내는 역할
public class ServerThread extends Thread {
    private int portID;
    private Connection connection;
    private Socket clientSocket;
    private Protocol protocol;
    private InputStream is;
    private OutputStream os;
    //학생 정보 저장 => 로그인시 초기화
    private String name;
    private String StudentId;

    public ServerThread(Socket paramSocket, Connection paramConnection) throws IOException {
        System.out.println("[DEBUG] ServerThread 생성자 호출");
        portID = paramSocket.getPort();
        connection = paramConnection;
        this.clientSocket = paramSocket;
        this.is = paramSocket.getInputStream();
        this.os = paramSocket.getOutputStream();
        System.out.println("[DEBUG] ServerThread - InputStream 및 OutputStream 연결됨");
    }

    @Override
    public void run() {
        System.out.println("[DEBUG] ServerThread run() 호출");
        while (true) {
            try {
                protocol = read();
                handle(protocol,connection);
            } catch (Exception e) {
                System.err.println("[ERROR] run()에서 예외 발생: " + e.getMessage());
                e.printStackTrace();
                break;
            }
        }
        closeResources();
    }


    //입력들어온 함수와 비교하는...handle
    public void handle(Protocol protocol,Connection conn) throws Exception {
        System.out.println("[DEBUG] handle() 호출: packetType=" + protocol.getType() + protocol.getCode());
        int packetType = protocol.getType();

        switch (packetType) {
            case Protocol.TYPE0_LOGIN:
                System.out.println("[DEBUG] 로그인 요청 처리 중...");
                handleLogin(protocol);
                // 로그인 처리 로직
                break;
            case Protocol.TYPE1_LOGINOUT:
                System.out.println("[DEBUG] 로그아웃 요청 처리 중...");
                handleLogout(protocol);
                break;
            case Protocol.TYPE2_REGISTER_REQ:
                System.out.println("[DEBUG] 등록 요청 처리 중...");
                handleRegisterRequest(protocol);
                break;
//            case Protocol.TYPE4_DELETE_REQ:
//                System.out.println("[DEBUG] 삭제 요청 처리 중...");
//                break;
            case Protocol.TYPE6_VIEW_REQ:
                handleViewRequests(protocol,conn);
                break;
            case Protocol.TYPE8_UPDATE_REQ:
                System.out.println("[DEBUG] 업데이트 요청 처리 중...");
                handleUpdateRequest(protocol);
                break;
            case Protocol.UNDEFINED_TYPE:
                handleUndefinedType(protocol);
                break;
            default:
                System.out.println("[DEBUG] 알 수 없는 타입 처리 중...");
        }
    }

    //Type0
    private void handleLogin(Protocol protocol) throws IOException {
        switch (protocol.getCode()) {
            case Protocol.T0_CODE0_ID:
                System.out.println("[DEBUG] 로그인 요청: ID 처리");
                break;
            case Protocol.T0_CODE1_PWD:
                System.out.println("[DEBUG] 로그인 요청: 비밀번호 처리");
                break;
            case Protocol.T0_CODE2_SUCCESS:
                System.out.println("[DEBUG] 로그인 성공");
                break;
            case Protocol.T0_CODE3_FAIL:
                System.out.println("[DEBUG] 로그인 실패");
                break;
            default:
                System.out.println("[DEBUG] 알 수 없는 코드: " + protocol.getCode());
        }
    }

    //Type1
    private void handleLogout(Protocol protocol) throws IOException {
        switch (protocol.getCode()) {
            case Protocol.T1_CODE0_SUCCESS:
                System.out.println("[DEBUG] 로그아웃 성공");
                break;
            case Protocol.T1_CODE1_FAIL:
                System.out.println("[DEBUG] 로그아웃 실패");
                break;
            default:
                System.out.println("[DEBUG] 알 수 없는 코드: " + protocol.getCode());
        }
    }

    //Type2
    private void handleRegisterRequest(Protocol protocol) throws IOException {
        switch (protocol.getCode()) {
            case Protocol.T2_CODE0_APPLICATION_INFO:
                System.out.println("[DEBUG] 등록 요청: 신청서 정보 처리");
                break;
            case Protocol.T2_CODE1_PAYMENT_AMOUNT:
                System.out.println("[DEBUG] 등록 요청: 납부 금액 처리");
                break;
            case Protocol.T2_CODE2_TUBERCULOSIS_CERTIFICATE:
                System.out.println("[DEBUG] 등록 요청: 결핵 진단서 처리");
                break;
            case Protocol.T2_CODE3_UNAPPLICATION_INFO:
                System.out.println("[DEBUG] 등록 요청: 퇴사 신청서 처리");
                break;
            case Protocol.T2_CODE4_SELECTION_SCHEDULE_NAME:
                System.out.println("[DEBUG] 등록 요청: 선발 일정명 처리");
                break;
            case Protocol.T2_CODE5_SELECTION_SCHEDULE_PERIOD:
                System.out.println("[DEBUG] 등록 요청: 선발 일정 기간 처리");
                break;
            case Protocol.T2_CODE6_DORMITORY_FEE:
                System.out.println("[DEBUG] 등록 요청: 생활관 사용료 처리");
                break;
            case Protocol.T2_CODE7_MEAL_COST:
                System.out.println("[DEBUG] 등록 요청: 급식비 처리");
                break;
            default:
                System.out.println("[DEBUG] 알 수 없는 코드: " + protocol.getCode());
        }
    }

    //Type3
    private void handleRegisterResponse(Protocol protocol) throws IOException {
        switch (protocol.getCode()) {
            case Protocol.T3_CODE0_SUCCESS:
                System.out.println("[DEBUG] 등록 응답: 성공");
                break;
            case Protocol.T3_CODE1_FAIL:
                System.out.println("[DEBUG] 등록 응답: 실패");
                break;
            default:
                System.out.println("[DEBUG] 알 수 없는 코드: " + protocol.getCode());
        }
    }

    // Type6
    private void handleViewRequests(Protocol protocol,Connection conn) throws IOException {
        switch (protocol.getCode()) {
            case Protocol.T6_CODE0_SELECTION_SCHEDULENAME_LIST:
                System.out.println("[DEBUG] 처리 중: 선발 일정명 리스트 요청");
                handleViewAllScheduleNameRequest(protocol,conn);
                break;
            case Protocol.T6_CODE1_SELECTION_PERIOD_OF_SCHEDULE_LIST:
                System.out.println("[DEBUG] 처리 중: 생활관 사용료 리스트 요청");
                handleViewAllSchedulesRequest(protocol,conn);
                break;
            case Protocol.T6_CODE2_DORMITORY_NAME_LIST:
                System.out.println("[DEBUG] 처리 중: 급식비 리스트 요청");
                break;
            case Protocol.T6_CODE3_DORMITORY_TYPE_LIST:
                System.out.println("[DEBUG] 처리 중: 선발 상태 요청");
                break;
            case Protocol.T6_CODE4_DORMITORY_GENDER_LIST:
                System.out.println("[DEBUG] 처리 중: 선발 정보 요청");
                break;
            case Protocol.T6_CODE5_DORMITORYFEE_LIST:
                System.out.println("[DEBUG] 처리 중: 생활관 사용료 요청");
                //handleStudentFeeRequest(protocol);
                break;
            case Protocol.T6_CODE6_MEALTYPE_LIST:
                System.out.println("[DEBUG] 처리 중: 급식비 요청");
                break;
            case Protocol.T6_CODE7_MEALCOST_LIST:
                System.out.println("[DEBUG] 처리 중: 환불 상태 요청");
                break;
            case Protocol.T6_CODE8_SELECTION_STATUS:
                System.out.println("[DEBUG] 처리 중: 생활관 ID 요청");
                break;
            case Protocol.T6_CODE9_SELECTION_INFO:
                System.out.println("[DEBUG] 처리 중: 입사 신청 학생 리스트 요청");
                break;
            case Protocol.T6_CODE10_DORMITORY_FEE:
                System.out.println("[DEBUG] 처리 중: 선발 학생 리스트 요청");
                break;
            case Protocol.T6_CODE11_MEAL_COST:
                System.out.println("[DEBUG] 처리 중: 남은 침대 리스트 요청");
                break;
            case Protocol.T6_CODE12_REFUND_STATUS:
                System.out.println("[DEBUG] 처리 중: 납부자 리스트 요청");
               // handlePaidStudentsRequest(protocol);
                break;
            case Protocol.T6_CODE13_DORMITORY_ID:
                System.out.println("[DEBUG] 처리 중: 기숙사ID 조회 요청");
                //viewDormitoryID(protocol);
                break;
            case Protocol.T6_CODE14_APPLICATION_STUDENT_LIST:
                System.out.println("[DEBUG] 처리 중: 결핵 진단서 제출자 리스트 요청");
                break;
            case Protocol.T6_CODE15_SETECTION_STUDENT_LIST:
                System.out.println("[DEBUG] 처리 중: 결핵 진단서 요청");
                break;
            case Protocol.T6_CODE16_UNUSEDBED_LIST:
                System.out.println("[DEBUG] 처리 중: 퇴사 신청자 리스트 요청");
                break;
            case Protocol.T6_CODE17_PAID_STUDENT:
                System.out.println("[DEBUG] 처리 중: 선발 일정 기간 리스트 요청");
                break;
            case Protocol.T6_CODE18_UNPAID_STUDENT:
                System.out.println("[DEBUG] 처리 중: 선발 일정 기간 리스트 요청");
                break;
            case Protocol.T6_CODE19_TUBERCULOSIS_CERTIFICATE_SUBMITTER:
                System.out.println("[DEBUG] 처리 중: 선발 일정 기간 리스트 요청");
                break;
            case Protocol.T6_CODE20_TUBERCULOSIS_CERTIFICATE:
                System.out.println("[DEBUG] 처리 중: 선발 일정 기간 리스트 요청");
                break;
            case Protocol.T6_CODE21_UNAPPLICATION_STUDENT:
                System.out.println("[DEBUG] 처리 중: 선발 일정 기간 리스트 요청");
                break;
            case Protocol.T6_CODE22_DORMITORYINFO:
                System.out.println("[DEBUG] 처리 중: 기숙사 정보 조회 요청");
                //viewDormitoryInfo(protocol);
                break;
            default:
                System.out.println("[DEBUG] 처리 중: 알 수 없는 코드");
        }
    }

    //Type7
    private void handleViewResponse(Protocol protocol) throws IOException {
        switch (protocol.getCode()) {
            case Protocol.T7_CODE0_SELECTION_SCHEDULENAME_LIST:
                System.out.println("[DEBUG] 조회 응답: 일정명 목록");
                break;
            case Protocol.T7_CODE1_SELECTION_PERIOD_OF_SCHEDULE_LIST:
                System.out.println("[DEBUG] 조회 응답: 생활관 사용료 목록");
                break;
            case Protocol.T7_CODE2_DORMITORY_NAME_LIST:
                System.out.println("[DEBUG] 조회 응답: 급식비 목록");
                break;
            case Protocol.T7_CODE3_DORMITORY_TYPE_LIST:
                System.out.println("[DEBUG] 조회 응답: 선발 상태");
                break;
            case Protocol.T7_CODE4_DORMITORY_GENDER_LIST:
                System.out.println("[DEBUG] 조회 응답: 선발 정보");
                break;
            case Protocol.T7_CODE5_DORMITORYFEE_LIST:
                System.out.println("[DEBUG] 조회 응답: 생활관 사용료");
                break;
            case Protocol.T7_CODE6_MEALTYPE_LIST:
                System.out.println("[DEBUG] 조회 응답: 급식비");
                break;
            case Protocol.T7_CODE7_MEALCOST_LIST:
                System.out.println("[DEBUG] 조회 응답: 환불 상태");
                break;
            case Protocol.T7_CODE8_SELECTION_STATUS:
                System.out.println("[DEBUG] 조회 응답: 생활관 ID");
                break;
            case Protocol.T7_CODE9_SELECTION_INFO:
                System.out.println("[DEBUG] 조회 응답: 입사 신청 학생 목록");
                break;
            case Protocol.T7_CODE10_DORMITORY_FEE:
                System.out.println("[DEBUG] 조회 응답: 선발 학생 목록");
                break;
            case Protocol.T7_CODE11_MEAL_COST:
                System.out.println("[DEBUG] 조회 응답: 남은 침대 정보");
                break;
            case Protocol.T7_CODE12_REFUND_STATUS:
                System.out.println("[DEBUG] 조회 응답: 납부 학생 목록");
                break;
            case Protocol.T7_CODE13_DORMITORY_ID:
                System.out.println("[DEBUG] 조회 응답: 미납 학생 목록");
                break;
            case Protocol.T7_CODE14_APPLICATION_STUDENT_LIST:
                System.out.println("[DEBUG] 조회 응답: 결핵 진단서 제출자");
                break;
            case Protocol.T7_CODE15_SETECTION_STUDENT_LIST:
                System.out.println("[DEBUG] 조회 응답: 결핵 진단서");
                break;
            case Protocol.T7_CODE16_UNUSEDBED_LIST:
                System.out.println("[DEBUG] 조회 응답: 퇴사 신청자 명단");
                break;
            case Protocol.T7_CODE17_PAID_STUDENT:
                System.out.println("[DEBUG] 조회 응답: 일정 기간");
                break;
            case Protocol.T7_CODE18_UNPAID_STUDENT:
                System.out.println("[DEBUG] 조회 응답: 실패");
                break;
            case Protocol.T7_CODE19_TUBERCULOSIS_CERTIFICATE_SUBMITTER:
                System.out.println("[DEBUG] 조회 응답: 실패");
                break;
            case Protocol.T7_CODE20_TUBERCULOSIS_CERTIFICATE:
                System.out.println("[DEBUG] 조회 응답: 실패");
                break;
            case Protocol.T7_CODE21_UNAPPLICATION_STUDENT:
                System.out.println("[DEBUG] 조회 응답: 실패");
                break;
            case Protocol.T7_CODE22_DORMITORYINFO:
                System.out.println("[DEBUG] 조회 응답: 실패");
                break;
            case Protocol.T7_CODE23_FAIL:
                System.out.println("[DEBUG] 조회 응답: 실패");
                break;
            default:
                System.out.println("[DEBUG] 알 수 없는 조회 응답 코드: " + protocol.getCode());
        }
    }

    //Type8
    private void handleUpdateRequest(Protocol protocol) throws IOException {
        switch (protocol.getCode()) {
            case Protocol.T8_CODE0_ROOM_AND_BED_ASSIGNMENT:
                System.out.println("[DEBUG] 업데이트 요청: 방 및 침대 배정");
                break;
            case Protocol.T8_CODE1_REFUND_STATUS:
                System.out.println("[DEBUG] 업데이트 요청: 환불 상태");
                break;
            default:
                System.out.println("[DEBUG] 알 수 없는 업데이트 요청 코드: " + protocol.getCode());
        }
    }

    //Type9
    private void handleUpdateResponse(Protocol protocol) throws IOException {
        switch (protocol.getCode()) {
            case Protocol.T9_CODE0_SUCCESS:
                System.out.println("[DEBUG] 업데이트 응답: 성공");
                break;
            case Protocol.T9_CODE1_FAIL:
                System.out.println("[DEBUG] 업데이트 응답: 실패");
                break;
            default:
                System.out.println("[DEBUG] 알 수 없는 업데이트 응답 코드: " + protocol.getCode());
        }
    }

    //Type10
    private void handleUndefinedType(Protocol protocol) {
        System.out.println("[DEBUG] 처리되지 않은 타입 요청");
    }


    public int getPortID() {
        System.out.println("[DEBUG] getPortID() 호출");
        return portID;
    }

//    public void send(Protocol protocol) throws IOException {
//        System.out.println("[DEBUG] send() 호출: protocol=" + protocol);
//        try {
//            os.write(protocol.createPacket());
//            os.flush();
//            System.out.println("[DEBUG] 패킷 전송 성공");
//        } catch (IOException e) {
//            System.err.println("[ERROR] send()에서 예외 발생: " + e.getMessage());
//            e.printStackTrace();
//            throw e;
//        }
//    }

    //기존 것 int readSize를 while문 밖에서 0으로 초기화하고 while문 안에서 할당해줬는데 합쳐짐
    private Protocol recv(int type, int code) throws Exception {
        System.out.println("[DEBUG] recv() 호출: type=" + type + ", code=" + code);
        byte[] header = new byte[Protocol.LEN_HEADER];
        System.out.println("serverThread 클래스의 recv 함수 호출중입니다.");
        Protocol protocol = new Protocol();
        try {
            is.read(header, 0, Protocol.LEN_HEADER);
            protocol.setPacketHeader(header);
            System.out.println("[DEBUG] 헤더 수신 성공");

            byte[] buf = new byte[protocol.getBodyLength()];
            int receiveLength = 0;
            while (receiveLength < protocol.getBodyLength()) {
                int readSize = is.read(buf, receiveLength, protocol.getBodyLength() - receiveLength);
                if (readSize == -1) {
                    throw new IOException("통신오류: 연결이 끊어졌습니다.");
                }
                receiveLength += readSize;
            }
            protocol.setPacketBody(buf);
            System.out.println("[DEBUG] 바디 수신 성공");
            return protocol;
        } catch (IOException e) {
            System.err.println("[ERROR] recv()에서 예외 발생: " + e.getMessage());
            e.printStackTrace();
            throw new Exception("통신오류: 데이터 수신 실패");
        }
    }

    public void close() throws IOException {
        System.out.println("[DEBUG] close() 호출");
        closeResources();
    }

    private void closeResources() {
        try {
            if (clientSocket != null) clientSocket.close();
            if (is != null) is.close();
            if (os != null) os.close();
            System.out.println("[DEBUG] 리소스 정리 완료");
        } catch (IOException e) {
            System.err.println("[ERROR] closeResources()에서 예외 발생: " + e.getMessage());
        }
    }

    public Protocol handleViewAllScheduleNameRequest(Protocol protocol,Connection conn) throws IOException {
        System.out.println("[DEBUG] handleViewAllScheduleNameRequest() 호출");
        Protocol response;
        try {
            // 1. 데이터베이스에서 스케줄 가져오기
            List<SelectionScheduleDTO> schedules = SelectionScheduleDAO.getAllSelectionScheduleName(conn);

            String[] scheduleArray = schedules.stream()
                    .map(SelectionScheduleDTO::getName)
                    .toArray(String[]::new);

            response = new Protocol(Protocol.TYPE7_VIEW_RES, Protocol.T7_CODE0_SELECTION_SCHEDULENAME_LIST);
            response.setBody(scheduleArray, 40);

            send(response);
            System.out.println("[DEBUG] 조회 이름 응답 전송 완료");
        } catch (Exception e) {
            System.err.println("[ERROR] handleViewAllScheduleNameRequest()에서 예외 발생: " + e.getMessage());
            e.printStackTrace();

            // 실패 응답
            response = new Protocol(Protocol.TYPE7_VIEW_RES, Protocol.T7_CODE23_FAIL);
            send(response);
        }
        return response;
    }


    public Protocol handleViewAllSchedulesRequest(Protocol protocol,Connection conn) throws IOException {
        System.out.println("[DEBUG] handleViewAllSchedulesRequest() 호출");
        Protocol response;

        try {
            List<SelectionScheduleDTO> schedules = SelectionScheduleDAO.getAllSelectionSchedules(conn);

            if (schedules.isEmpty()) {
                throw new IllegalStateException("스케줄 데이터가 존재하지 않습니다.");
            }

            String[] schedulePeriods = schedules.stream()
                    .map(schedule -> schedule.getPeriod() != null
                            ? schedule.getPeriod().toString()
                            : "미정")
                    .toArray(String[]::new);

            // 3. Protocol 응답 생성 및 바디 설정
            response = new Protocol(Protocol.TYPE7_VIEW_RES, Protocol.T7_CODE1_SELECTION_PERIOD_OF_SCHEDULE_LIST);
            response.setBody(schedulePeriods, 10);

            // 4. 응답 전송
            send(response);
            System.out.println("[DEBUG] 조회 기간 응답 전송 완료");
        } catch (Exception e) {
            System.err.println("[ERROR] handleViewAllSchedulesRequest()에서 예외 발생: " + e.getMessage());
            e.printStackTrace();

            // 실패 응답
            response = new Protocol(Protocol.TYPE7_VIEW_RES, Protocol.T7_CODE23_FAIL);
            send(response);
        }

        return response;
    }

    public Protocol handleView(Protocol protocol,Connection conn) throws IOException {
        System.out.println("[DEBUG] handleViewAllScheduleNameRequest() 호출");
        Protocol response;
        try {
            // 1. 데이터베이스에서 스케줄 가져오기
            List<SelectionScheduleDTO> schedules = SelectionScheduleDAO.getAllSelectionScheduleName(conn);

            String[] scheduleArray = schedules.stream()
                    .map(SelectionScheduleDTO::getName)
                    .toArray(String[]::new);

            response = new Protocol(Protocol.TYPE7_VIEW_RES, Protocol.T7_CODE0_SELECTION_SCHEDULENAME_LIST);
            response.setBody(scheduleArray, 40);

            send(response);
            System.out.println("[DEBUG] 조회 이름 응답 전송 완료");
        } catch (Exception e) {
            System.err.println("[ERROR] handleViewAllScheduleNameRequest()에서 예외 발생: " + e.getMessage());
            e.printStackTrace();

            // 실패 응답
            response = new Protocol(Protocol.TYPE7_VIEW_RES, Protocol.T7_CODE23_FAIL);
            send(response);
        }
        return response;
    }



//    public Protocol read() throws IOException {
//        System.out.println("[DEBUG] read() 호출");
//        byte[] header = new byte[Protocol.LEN_HEADER];
//        Protocol protocol = new Protocol();
//        System.out.println("문제가 없음");
//        int bytesRead = is.read(header, 0, Protocol.LEN_HEADER);
//        System.out.println("[DEBUG] 헤더 읽기 완료: bytesRead=" + bytesRead);
//
//        if (bytesRead < Protocol.LEN_HEADER) {
//            throw new IOException("헤더 데이터가 충분하지 않습니다.");
//        }
//
//        protocol.setPacketHeader(header);
//        System.out.println("[DEBUG] 헤더 설정 완료: bodyLength=" + protocol.getBodyLength());
//
//        byte[] buf = new byte[protocol.getBodyLength()];
//        int totalReceived = 0;
//        while (totalReceived < protocol.getBodyLength()) {
//            int readSize = is.read(buf, totalReceived, protocol.getBodyLength() - totalReceived);
//            if (readSize == -1) {
//                throw new IOException("통신 오류: 데이터가 중단되었습니다.");
//            }
//            totalReceived += readSize;
//            System.out.println("[DEBUG] 본문 읽기 진행 중: totalReceived=" + totalReceived);
//        }
//
//        if (bytesRead > Protocol.LEN_HEADER) protocol.setPacketBody(buf);
//        System.out.println("[DEBUG] 바디 읽기 완료");
//        return protocol;
//    }

    public void send(Protocol protocol) throws IOException {
        System.out.println("[DEBUG] send() 호출: protocol=" + protocol);
        try {
            byte[] packet = protocol.createPacket();
            os.write(packet);
            os.flush();
            System.out.println("[DEBUG] 패킷 전송 성공, 데이터: " + Arrays.toString(packet));
        } catch (IOException e) {
            System.err.println("[ERROR] send()에서 예외 발생: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    public Protocol read() throws IOException {
        System.out.println("[DEBUG] read() 호출");
        byte[] header = new byte[Protocol.LEN_HEADER];
        int bytesRead = is.read(header);
        if (bytesRead < Protocol.LEN_HEADER) {
            throw new IOException("헤더 데이터가 충분하지 않습니다.");
        }
        Protocol protocol = new Protocol();
        protocol.setPacketHeader(header);
        byte[] body = new byte[protocol.getBodyLength()];
        int totalReceived = 0;
        while (totalReceived < protocol.getBodyLength()) {
            int readSize = is.read(body, totalReceived, protocol.getBodyLength() - totalReceived);
            if (readSize == -1) throw new IOException("통신 오류: 데이터가 중단되었습니다.");
            totalReceived += readSize;
        }
        protocol.setPacketBody(body);
        System.out.println("[DEBUG] 읽기 완료: " + Arrays.toString(body));
        return protocol;
    }

    ///기능 4번 테스트를 위한 공간
    // 특정 학생의 생활관 비용 조회
//    private void handleStudentFeeRequest(Protocol protocol) throws IOException {
//        System.out.println("[DEBUG] handleStudentFeeRequest 호출");
//        try {
//            String studentId = new String(protocol.getBody());
//            System.out.println("[DEBUG] 요청받은 학생 ID: " + studentId);
//
//            Map<String, Object> student = TempData.findStudentById(studentId);
//
//            Protocol response;
//            if (student != null) {
//                String message = "학생 이름: " + student.get("이름") + ", 납부 상태: " + student.get("납부여부");
//                response = new Protocol(Protocol.TYPE7_VIEW_RES, Protocol.T7_CODE5_DORMITORY_FEE);
//                response.setBody(message);
//            } else {
//                response = new Protocol(Protocol.TYPE7_VIEW_RES, Protocol.T7_CODE18_FAIL);
//            }
//            send(response);
//        } catch (Exception e) {
//            System.err.println("[ERROR] handleStudentFeeRequest 예외 발생: " + e.getMessage());
//        }
//    }
//
//    private void handlePaidStudentsRequest(Protocol protocol) throws IOException {
//        System.out.println("[DEBUG] handlePaidStudentsRequest 호출");
//        try {
//            int dormitoryId = Integer.parseInt(new String(protocol.getBody()));
//            List<Map<String, Object>> paidStudents = TempData.findPaidStudentsByDormitory(dormitoryId);
//
//            String[] names = paidStudents.stream()
//                    .map(student -> student.get("이름").toString())
//                    .toArray(String[]::new);
//
//            Protocol response = new Protocol(Protocol.TYPE7_VIEW_RES, Protocol.T7_CODE12_PAID_STUDENT);
//            response.setBody(names, 50);
//            send(response);
//        } catch (Exception e) {
//            System.err.println("[ERROR] handlePaidStudentsRequest 예외 발생: " + e.getMessage());
//        }
//    }
//
//    private void handleUnpaidStudentsRequest(Protocol protocol) throws IOException {
//        System.out.println("[DEBUG] handleUnpaidStudentsRequest 호출");
//        try {
//            int dormitoryId = Integer.parseInt(new String(protocol.getBody()));
//            List<Map<String, Object>> unpaidStudents = TempData.findUnpaidStudentsByDormitory(dormitoryId);
//
//            String[] names = unpaidStudents.stream()
//                    .map(student -> student.get("이름").toString())
//                    .toArray(String[]::new);
//
//            Protocol response = new Protocol(Protocol.TYPE7_VIEW_RES, Protocol.T7_CODE13_UNPAID_STUDENT);
//            response.setBody(names, 50);
//            send(response);
//        } catch (Exception e) {
//            System.err.println("[ERROR] handleUnpaidStudentsRequest 예외 발생: " + e.getMessage());
//        }
//    }
   /*
   * public void viewDormitoryID(Protocol protocol) throws Exception {
        Protocol response;
        try
        {
            System.out.println("서버에서 읽어지는 지 확인");
            String[] dormitoryInfo = protocol.dataExtraction(protocol.getBody(), 3);
//            String dormitoryName = dormitoryInfo[0];
            String dormitoryType = dormitoryInfo[1];
            String gender = dormitoryInfo[2];

            String dormitoryName = dormitoryInfo[0];
//            int dormitoryType = Integer.parseInt(dormitoryInfo[1]);
//            int gender = Integer.parseInt(dormitoryInfo[2]);

            // DB 서버에서 로그인 한 학생의 gender (성별)을 읽어 반환하는 코드 필요

            String dormitoryID = dao.findDormitoryId(dormitoryName, dormitoryType, gender);
            if (Integer.parseInt(dormitoryID) > 0) response = new Protocol(Protocol.TYPE7_VIEW_RES, Protocol.T7_CODE13_DORMITORY_ID);
            else response = new Protocol(Protocol.TYPE7_VIEW_RES, Protocol.T7_CODE23_FAIL);
            response.setBody(dormitoryID);
            send(response);

            System.out.println("[System] 생활관ID 조회 응답 전송 완료");
        } catch (Exception e) {
            System.err.println("[ERROR] viewMealTypeList()에서 예외 발생" + e.getMessage());
            response = new Protocol(Protocol.TYPE7_VIEW_RES, Protocol.T7_CODE23_FAIL);
            send(response);
        }
    }*/

}
