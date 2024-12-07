package Project;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.List;

//// 데이터베이스 DAO 연결 필요
//// 프로토콜 연결 필요
//
//// 서버 스레드는 각 클라이언트의 요청을 처리하고 응답을 보내는 역할
//public class ServerThread extends Thread {
//    private int portID;
//    private Connection connection;
//    private Socket clientSocket;
//    private Protocol protocol;
//    private InputStream is;
//    private OutputStream os;
//
//    // 스레드 생성자
//    public ServerThread(Socket paramSocket, Connection paramConnection) throws IOException
//    {
//      portID = paramSocket.getPort();
//     // connection = paramConnection;
//      is = paramSocket.getInputStream();
//      os = paramSocket.getOutputStream();
//    }
//
//    @Override
//    public void run() {
//        while (true) {
//            try {
//                protocol = read();
//                handle(protocol);
//            } catch (Exception e) {
//                //this.stop();
//            }
//        }
//    }
//
//      public void handle(Protocol protocol) throws Exception {
//        int packetType = protocol.getType();
//
//        switch (packetType) {
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
//          case Protocol.TYPE1_LOGINOUT:
//            break;
//          case Protocol.TYPE2_REGISTER_REQ: // 등록요청
//            //send(registerReq(protocol));
//            break;
//          case Protocol.TYPE3_REGISTER_RES: //  등록응답
//            //send(viewReq(protocol));
//            break;
//          case Protocol.TYPE4_DELETE_REQ: // 삭제 요청
//            //send(updateReq(protocol));
//            break;
//          case Protocol.TYPE5_DELETE_RES: // 삭제 응답
//            //send(deleteReq(protocol));
//            break;
//          case Protocol.TYPE6_VIEW_REQ: // 조회 요청
//            //send(checkReq(protocol));
//            break;
//          case Protocol.TYPE7_VIEW_RES: // 조회 응답
//              handleViewAllScheduleNameRequest(protocol);
//              handleViewAllScheduleSRequest(protocol);
//            break;
//          case Protocol.TYPE8_UPDATE_REQ: // 조회 응답
//                //logoutReq(protocol);
//            break;
//          case Protocol.TYPE9_UPDATE_RES: // 조회 응답
//                //logoutReq(protocol);
//            break;
//          case Protocol.UNDEFINED_TYPE: // 조회 응답
//                //logoutReq(protocol);
//            break;
//        }
//      }
//      public int getPortID(){
//        return portID;
//      }
//
//      public void send(Protocol protocol) throws IOException {
//        os.write(protocol.createPacket());
//        os.flush();
//      }
//
//    // 프로토콜 수신
//    private Protocol recv(int type, int code) throws Exception {
//        byte[] header = new byte[Protocol.LEN_HEADER];
//        Protocol protocol = new Protocol();
//        try {
//            int receiveLength, readSize;
//            receiveLength = 0;
//            readSize = 0;
//            is.read(header, 0, Protocol.LEN_HEADER);
//            protocol.setPacketHeader(header);
//            byte[] buf = new byte[protocol.getBodyLength()];
//            while (receiveLength < protocol.getBodyLength()) {
//                readSize = is.read(buf, receiveLength, protocol.getBodyLength() - receiveLength);
//                if (readSize == -1) {
//                    throw new Exception("통신오류: 연결 끊어짐");
//                }
//                receiveLength += readSize;
//            }
//            protocol.setPacketBody(buf);
//            return protocol;
//        } catch (IOException e) {
//            throw new Exception("통신오류: 데이터 수신 실패함");
//        }
//    }
//
//
//    public void close() throws IOException {
//        if (clientSocket != null)
//            clientSocket.close();
//        if (is != null)
//            is.close();
//        if (os != null)
//            os.close();
//    }
//
//    public Protocol handleViewAllScheduleNameRequest(Protocol protocol) throws IOException {
//        Protocol response;
//        try {
//            // 데이터베이스에서 일정 조회
////            List<String> schedules = fetchAllSchedulesFromDB(); // DB에서 일정 목록 가져오기
//           //테스트용
//            List<String> schedules = fetchMockSchedules(); // DB에서 일정 목록 가져오기
//
//            // 문자열 배열로 변환
//            String[] scheduleArray = schedules.toArray(new String[0]);
//
//            // 응답 본문 설정
//            response = new Protocol(Protocol.TYPE7_VIEW_RES, Protocol.T7_CODE0_SELECTION_SCHEDULENAME_LIST);
//            response.setBody(scheduleArray, 5);
//            send(response);
//        } catch (Exception e) {
//            // 오류 처리
//            response = new Protocol(Protocol.TYPE7_VIEW_RES, Protocol.T7_CODE18_FAIL);
//            send(response);
//        }
//
//        return response;
//    }
//
//    public Protocol handleViewAllScheduleSRequest(Protocol protocol) throws IOException {
//        Protocol response;
//        try {
//            // 데이터베이스에서 일정 조회
////            List<String> schedules = fetchAllSchedulesFromDB(); // DB에서 일정 목록 가져오기
//            //테스트용
//            List<String> schedules = fetchMockSchedules(); // DB에서 일정 목록 가져오기
//
//            // 문자열 배열로 변환
//            String[] scheduleArray = schedules.toArray(new String[0]);
//
//            // 응답 본문 설정
//            response = new Protocol(Protocol.TYPE7_VIEW_RES, Protocol.T7_CODE17_SELECTION_PERIOD_OF_SCHEDULE_LIST);
//            response.setBody(scheduleArray, 5);
//            send(response);
//        } catch (Exception e) {
//            // 오류 처리
//            response = new Protocol(Protocol.TYPE7_VIEW_RES, Protocol.T7_CODE18_FAIL);
//            send(response);
//        }
//
//        return response;
//    }
//    private List<String> fetchMockSchedules() {
//        // 테스트용 일정 데이터
//        return Arrays.asList(
//                "Meeting with Team A at 10:00 AM",
//                "Lunch Break at 12:00 PM",
//                "Project Review at 2:00 PM",
//                "Weekly Sync-Up at 4:00 PM"
//        );
//    }
//
//    public Protocol read() throws IOException {
//        byte[] header = new byte[Protocol.LEN_HEADER];
//        Protocol protocol = new Protocol();
//        int totalReceived = 0;
//        int readSize = 0;
//        is.read( header, 0,Protocol.LEN_HEADER);
//        protocol.setPacketHeader(header);
//        byte[] buf = new byte[protocol.getBodyLength()];
//        while (totalReceived < protocol.getBodyLength()) {
//            readSize = is.read(buf, totalReceived, protocol.getBodyLength() - totalReceived);
//            totalReceived += readSize;
//        }
//        protocol.setPacketBody(buf);
//        return protocol;
//    }
//}

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

    public void handle(Protocol protocol) throws Exception {
        System.out.println("[DEBUG] handle() 호출: packetType=" + protocol.getType() + protocol.getCode());
        int packetType = protocol.getType();

        switch (packetType) {
            case Protocol.TYPE0_LOGIN:
                System.out.println("[DEBUG] 로그인 요청 처리 중...");
                // 로그인 처리 로직
                break;
            case Protocol.TYPE1_LOGINOUT:
                System.out.println("[DEBUG] 로그아웃 요청 처리 중...");
                break;
            case Protocol.TYPE2_REGISTER_REQ:
                System.out.println("[DEBUG] 등록 요청 처리 중...");
                break;
            case Protocol.TYPE3_REGISTER_RES:
                System.out.println("[DEBUG] 등록 응답 처리 중...");
                break;
            case Protocol.TYPE4_DELETE_REQ:
                System.out.println("[DEBUG] 삭제 요청 처리 중...");
                break;
            case Protocol.TYPE5_DELETE_RES:
                System.out.println("[DEBUG] 삭제 응답 처리 중...");
                break;
            case Protocol.TYPE6_VIEW_REQ:
                System.out.println("[DEBUG] 조회 요청 처리 중...");
                if (protocol.getCode() == Protocol.T6_CODE0_SELECTION_SCHEDULENAME_LIST) handleViewAllScheduleNameRequest(protocol);
                System.out.println("호출이 되는가!");
                if (protocol.getCode() == Protocol.T6_CODE17_SELECTION_PERIOD_OF_SCHEDULE_LIST) handleViewAllScheduleSRequest(protocol);
                break;
            case Protocol.TYPE7_VIEW_RES:
                System.out.println("[DEBUG] 조회 응답 처리 중...");
                break;
            case Protocol.TYPE8_UPDATE_REQ:
                System.out.println("[DEBUG] 업데이트 요청 처리 중...");
                break;
            case Protocol.TYPE9_UPDATE_RES:
                System.out.println("[DEBUG] 업데이트 응답 처리 중...");
                break;
            default:
                System.out.println("[DEBUG] 알 수 없는 타입 처리 중...");
        }
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

        //if (protocol.getBodyLength() != 0)
    System.out.println("[DEBUG] handleViewAllScheduleNameRequest() 호출");
        Protocol response;
        try {
            List<String> schedules = fetchMockSchedules();
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

    public Protocol handleViewAllScheduleSRequest(Protocol protocol) throws IOException {
        System.out.println("[DEBUG] handleViewAllScheduleSRequest() 호출");
        Protocol response;
        try {
            System.out.println("1");
            List<String> schedules = fetchMockSchedules();
            System.out.println("2");
            String[] scheduleArray = schedules.toArray(new String[0]);
            System.out.println("3");
            response = new Protocol(Protocol.TYPE7_VIEW_RES, Protocol.T7_CODE17_SELECTION_PERIOD_OF_SCHEDULE_LIST);
            response.setBody(scheduleArray, 3);
            System.out.println("4");
            send(response);
            System.out.println("5");
            System.out.println("[EBUG] 조회 기간 응답 전송 완료");
        } catch (Exception e) {
            System.err.println("[ERROR] handleViewAllScheduleSRequest()에서 예외 발생: " + e.getMessage());
            response = new Protocol(Protocol.TYPE7_VIEW_RES, Protocol.T7_CODE18_FAIL);
            send(response);
            System.out.println("6");
        }
        return response;
    }

    private List<String> fetchMockSchedules() {
        System.out.println("[DEBUG] fetchMockSchedules() 호출");
        return Arrays.asList(
                "M"
        );
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
