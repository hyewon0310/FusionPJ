package Project;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.util.List;
//          case Protocol.TYPE0_LOGIN: //로그인
////            String[] s = (String[]) protocol.getBody();
////            String SQL = "SELECT user_section FROM USERS WHERE user_id = ?";
////            PreparedStatement pstmt = null;
////            ResultSet rs = null;
////
////            pstmt = connection.prepareStatement(SQL);
////            pstmt.setString(1, s[0]);
////            rs = pstmt.executeQuery();
////            rs.next();
////
////            Protocol sndData = new Protocol(Protocol.TYPE0_LOGIN,Protocol.T0_CODE0_ID);
////            sndData.setBody(rs.getInt(1));
////            send(sndData);
//            break;

//// 데이터베이스 DAO 연결 필요
//// 프로토콜 연결 필요
//// 서버 스레드는 각 클라이언트의 요청을 처리하고 응답을 보내는 역할
public class ServerThread extends Thread {
    private int portID;
    private Connection connection;
    private Socket clientSocket;
    private Protocol protocol;
    private InputStream is;
    private OutputStream os;

    public ServerThread(Socket paramSocket) throws IOException {
        System.out.println("[DEBUG] ServerThread 생성자 호출");
        portID = paramSocket.getPort();
        // connection = paramConnection;
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
                handle(protocol);
            } catch (Exception e) {
                System.err.println("[ERROR] run()에서 예외 발생: " + e.getMessage());
                e.printStackTrace();
                break;
            }
        }
        closeResources();
    }

    //입력들어온 함수와 비교하는...handle
    public void handle(Protocol protocol) throws Exception {
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
            case Protocol.TYPE4_DELETE_REQ:
                System.out.println("[DEBUG] 삭제 요청 처리 중...");
                break;
            case Protocol.TYPE6_VIEW_REQ:
                handleViewRequests(protocol);
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
    private void handleViewRequests(Protocol protocol) throws IOException {
        switch (protocol.getCode()) {
            case Protocol.T6_CODE0_SELECTION_SCHEDULENAME_LIST:
                System.out.println("[DEBUG] 처리 중: 선발 일정명 리스트 요청");
                handleViewAllScheduleNameRequest(protocol);
                break;
            case Protocol.T6_CODE1_DORMITORY_FEE_LIST:
                System.out.println("[DEBUG] 처리 중: 생활관 사용료 리스트 요청");
                break;
            case Protocol.T6_CODE2_MEAL_COST_LIST:
                System.out.println("[DEBUG] 처리 중: 급식비 리스트 요청");
                break;
            case Protocol.T6_CODE3_SELECTION_STATUS:
                System.out.println("[DEBUG] 처리 중: 선발 상태 요청");
                break;
            case Protocol.T6_CODE4_SELECTION_INFO:
                System.out.println("[DEBUG] 처리 중: 선발 정보 요청");
                break;
            case Protocol.T6_CODE5_DORMITORY_FEE:
                System.out.println("[DEBUG] 처리 중: 생활관 사용료 요청");
                break;
            case Protocol.T6_CODE6_MEAL_COST:
                System.out.println("[DEBUG] 처리 중: 급식비 요청");
                break;
            case Protocol.T6_CODE7_REFUND_STATUS:
                System.out.println("[DEBUG] 처리 중: 환불 상태 요청");
                break;
            case Protocol.T6_CODE8_DORMITORY_ID:
                System.out.println("[DEBUG] 처리 중: 생활관 ID 요청");
                break;
            case Protocol.T6_CODE9_APPLICATION_STUDENT_LIST:
                System.out.println("[DEBUG] 처리 중: 입사 신청 학생 리스트 요청");
                break;
            case Protocol.T6_CODE10_SETECTION_STUDENT_LIST:
                System.out.println("[DEBUG] 처리 중: 선발 학생 리스트 요청");
                break;
            case Protocol.T6_CODE11_UNUSEDBED_LIST:
                System.out.println("[DEBUG] 처리 중: 남은 침대 리스트 요청");
                break;
            case Protocol.T6_CODE12_PAID_STUDENT:
                System.out.println("[DEBUG] 처리 중: 납부자 리스트 요청");
                break;
            case Protocol.T6_CODE13_UNPAID_STUDENT:
                System.out.println("[DEBUG] 처리 중: 미납자 리스트 요청");
                break;
            case Protocol.T6_CODE14_TUBERCULOSIS_CERTIFICATE_SUBMITTER:
                System.out.println("[DEBUG] 처리 중: 결핵 진단서 제출자 리스트 요청");
                break;
            case Protocol.T6_CODE15_TUBERCULOSIS_CERTIFICATE:
                System.out.println("[DEBUG] 처리 중: 결핵 진단서 요청");
                break;
            case Protocol.T6_CODE16_UNAPPLICATION_STUDENT:
                System.out.println("[DEBUG] 처리 중: 퇴사 신청자 리스트 요청");
                break;
            case Protocol.T6_CODE17_SELECTION_PERIOD_OF_SCHEDULE_LIST:
                System.out.println("[DEBUG] 처리 중: 선발 일정 기간 리스트 요청");
                handleViewAllSchedulesRequest(protocol);
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
            case Protocol.T7_CODE1_DORMITORY_FEE_LIST:
                System.out.println("[DEBUG] 조회 응답: 생활관 사용료 목록");
                break;
            case Protocol.T7_CODE2_MEAL_COST_LIST:
                System.out.println("[DEBUG] 조회 응답: 급식비 목록");
                break;
            case Protocol.T7_CODE3_SELECTION_STATUS:
                System.out.println("[DEBUG] 조회 응답: 선발 상태");
                break;
            case Protocol.T7_CODE4_SELECTION_INFO:
                System.out.println("[DEBUG] 조회 응답: 선발 정보");
                break;
            case Protocol.T7_CODE5_DORMITORY_FEE:
                System.out.println("[DEBUG] 조회 응답: 생활관 사용료");
                break;
            case Protocol.T7_CODE6_MEAL_COST:
                System.out.println("[DEBUG] 조회 응답: 급식비");
                break;
            case Protocol.T7_CODE7_REFUND_STATUS:
                System.out.println("[DEBUG] 조회 응답: 환불 상태");
                break;
            case Protocol.T7_CODE8_DORMITORY_ID:
                System.out.println("[DEBUG] 조회 응답: 생활관 ID");
                break;
            case Protocol.T7_CODE9_APPLICATION_STUDENT_LIST:
                System.out.println("[DEBUG] 조회 응답: 입사 신청 학생 목록");
                break;
            case Protocol.T7_CODE10_SETECTION_STUDENT_LIST:
                System.out.println("[DEBUG] 조회 응답: 선발 학생 목록");
                break;
            case Protocol.T7_CODE11_UNUSEDBED_LIST:
                System.out.println("[DEBUG] 조회 응답: 남은 침대 정보");
                break;
            case Protocol.T7_CODE12_PAID_STUDENT:
                System.out.println("[DEBUG] 조회 응답: 납부 학생 목록");
                break;
            case Protocol.T7_CODE13_UNPAID_STUDENT:
                System.out.println("[DEBUG] 조회 응답: 미납 학생 목록");
                break;
            case Protocol.T7_CODE14_TUBERCULOSIS_CERTIFICATE_SUBMITTER:
                System.out.println("[DEBUG] 조회 응답: 결핵 진단서 제출자");
                break;
            case Protocol.T7_CODE15_TUBERCULOSIS_CERTIFICATE:
                System.out.println("[DEBUG] 조회 응답: 결핵 진단서");
                break;
            case Protocol.T7_CODE16_UNAPPLICATION_STUDENT:
                System.out.println("[DEBUG] 조회 응답: 퇴사 신청자 명단");
                break;
            case Protocol.T7_CODE17_SELECTION_PERIOD_OF_SCHEDULE_LIST:
                System.out.println("[DEBUG] 조회 응답: 일정 기간");
                break;
            case Protocol.T7_CODE18_FAIL:
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

    public void send(Protocol protocol) throws IOException {
        System.out.println("[DEBUG] send() 호출: protocol=" + protocol);
        try {
            os.write(protocol.createPacket());
            os.flush();
            System.out.println("[DEBUG] 패킷 전송 성공");
        } catch (IOException e) {
            System.err.println("[ERROR] send()에서 예외 발생: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

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

    public Protocol handleViewAllScheduleNameRequest(Protocol protocol) throws IOException {


    System.out.println("[DEBUG] handleViewAllScheduleNameRequest() 호출");
        Protocol response;
        try {
            List<String> schedules = TempData.fetchMockSchedules();
            String[] scheduleArray = schedules.toArray(new String[0]);
            response = new Protocol(Protocol.TYPE7_VIEW_RES, Protocol.T7_CODE0_SELECTION_SCHEDULENAME_LIST);
            response.setBody(scheduleArray, 3);
            send(response);
            System.out.println("[DEBUG] 조회 이름 응답 전송 완료");
        } catch (Exception e) {
            System.err.println("[ERROR] handleViewAllScheduleNameRequest()에서 예외 발생: " + e.getMessage());
            response = new Protocol(Protocol.TYPE7_VIEW_RES, Protocol.T7_CODE18_FAIL);
            send(response);
        }
        return response;
    }

    public Protocol handleViewAllSchedulesRequest(Protocol protocol) throws IOException {
        System.out.println("[DEBUG] handleViewAllSchedulesRequest() 호출");
        Protocol response;
        try {
            List<String> schedules = TempData.fetchMockSchedules();
            String[] scheduleArray = schedules.toArray(new String[0]);
            response = new Protocol(Protocol.TYPE7_VIEW_RES, Protocol.T7_CODE17_SELECTION_PERIOD_OF_SCHEDULE_LIST);
            response.setBody(scheduleArray, 3);
            send(response);
            System.out.println("[EBUG] 조회 기간 응답 전송 완료");
        } catch (Exception e) {
            System.err.println("[ERROR] handleViewAllSchedulesRequest()에서 예외 발생: " + e.getMessage());
            response = new Protocol(Protocol.TYPE7_VIEW_RES, Protocol.T7_CODE18_FAIL);
            send(response);
        }
        return response;
    }

    public Protocol read() throws IOException {
        System.out.println("[DEBUG] read() 호출");
        byte[] header = new byte[Protocol.LEN_HEADER];
        Protocol protocol = new Protocol();
        System.out.println("문제가 없음");
        int bytesRead = is.read(header, 0, Protocol.LEN_HEADER);
        System.out.println("[DEBUG] 헤더 읽기 완료: bytesRead=" + bytesRead);

        if (bytesRead < Protocol.LEN_HEADER) {
            throw new IOException("헤더 데이터가 충분하지 않습니다.");
        }

        protocol.setPacketHeader(header);
        System.out.println("[DEBUG] 헤더 설정 완료: bodyLength=" + protocol.getBodyLength());

        byte[] buf = new byte[protocol.getBodyLength()];
        int totalReceived = 0;
        while (totalReceived < protocol.getBodyLength()) {
            int readSize = is.read(buf, totalReceived, protocol.getBodyLength() - totalReceived);
            if (readSize == -1) {
                throw new IOException("통신 오류: 데이터가 중단되었습니다.");
            }
            totalReceived += readSize;
            System.out.println("[DEBUG] 본문 읽기 진행 중: totalReceived=" + totalReceived);
        }

        if (bytesRead > Protocol.LEN_HEADER) protocol.setPacketBody(buf);
        System.out.println("[DEBUG] 바디 읽기 완료");
        return protocol;
    }

}
