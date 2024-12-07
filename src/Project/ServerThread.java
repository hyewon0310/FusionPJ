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

// 데이터베이스 DAO 연결 필요
// 프로토콜 연결 필요

// 서버 스레드는 각 클라이언트의 요청을 처리하고 응답을 보내는 역할
public class ServerThread extends Thread {
    private int portID;
    private Connection connection;
    private Socket clientSocket;
    private Protocol protocol;
    private InputStream is;
    private OutputStream os;

    // 스레드 생성자
    public ServerThread(Socket paramSocket, Connection paramConnection) throws IOException
    {
      portID = paramSocket.getPort();
     // connection = paramConnection;
      is = paramSocket.getInputStream();
      os = paramSocket.getOutputStream();
    }

    @Override
    public void run() {
        while (true) {
            try {
                protocol = read();
                handle(protocol);
            } catch (Exception e) {
                //this.stop();
            }
        }
    }

      public void handle(Protocol protocol) throws Exception {
        int packetType = protocol.getType();

        switch (packetType) {
          case Protocol.TYPE0_LOGIN: //로그인
//            String[] s = (String[]) protocol.getBody();
//            String SQL = "SELECT user_section FROM USERS WHERE user_id = ?";
//            PreparedStatement pstmt = null;
//            ResultSet rs = null;
//
//            pstmt = connection.prepareStatement(SQL);
//            pstmt.setString(1, s[0]);
//            rs = pstmt.executeQuery();
//            rs.next();
//
//            Protocol sndData = new Protocol(Protocol.TYPE0_LOGIN,Protocol.T0_CODE0_ID);
//            sndData.setBody(rs.getInt(1));
//            send(sndData);
            break;
          case Protocol.TYPE1_LOGINOUT:
            break;
          case Protocol.TYPE2_REGISTER_REQ: // 등록요청
            //send(registerReq(protocol));
            break;
          case Protocol.TYPE3_REGISTER_RES: //  등록응답
            //send(viewReq(protocol));
            break;
          case Protocol.TYPE4_DELETE_REQ: // 삭제 요청
            //send(updateReq(protocol));
            break;
          case Protocol.TYPE5_DELETE_RES: // 삭제 응답
            //send(deleteReq(protocol));
            break;
          case Protocol.TYPE6_VIEW_REQ: // 조회 요청
            //send(checkReq(protocol));
            break;
          case Protocol.TYPE7_VIEW_RES: // 조회 응답
              handleViewAllScheduleNameRequest(protocol);
              handleViewAllScheduleSRequest(protocol);
            break;
          case Protocol.TYPE8_UPDATE_REQ: // 조회 응답
                //logoutReq(protocol);
            break;
          case Protocol.TYPE9_UPDATE_RES: // 조회 응답
                //logoutReq(protocol);
            break;
          case Protocol.UNDEFINED_TYPE: // 조회 응답
                //logoutReq(protocol);
            break;
        }
      }
      public int getPortID(){
        return portID;
      }

      public void send(Protocol protocol) throws IOException {
        os.write(protocol.createPacket());
        os.flush();
      }

    // 프로토콜 수신
    private Protocol recv(int type, int code) throws Exception {
        byte[] header = new byte[Protocol.LEN_HEADER];
        Protocol protocol = new Protocol();
        try {
            int receiveLength, readSize;
            receiveLength = 0;
            readSize = 0;
            is.read(header, 0, Protocol.LEN_HEADER);
            protocol.setPacketHeader(header);
            byte[] buf = new byte[protocol.getBodyLength()];
            while (receiveLength < protocol.getBodyLength()) {
                readSize = is.read(buf, receiveLength, protocol.getBodyLength() - receiveLength);
                if (readSize == -1) {
                    throw new Exception("통신오류: 연결 끊어짐");
                }
                receiveLength += readSize;
            }
            protocol.setPacketBody(buf);
            return protocol;
        } catch (IOException e) {
            throw new Exception("통신오류: 데이터 수신 실패함");
        }
    }


    public void close() throws IOException {
        if (clientSocket != null)
            clientSocket.close();
        if (is != null)
            is.close();
        if (os != null)
            os.close();
    }

    public Protocol handleViewAllScheduleNameRequest(Protocol protocol) throws IOException {
        Protocol response;
        try {
            // 데이터베이스에서 일정 조회
//            List<String> schedules = fetchAllSchedulesFromDB(); // DB에서 일정 목록 가져오기
           //테스트용
            List<String> schedules = fetchMockSchedules(); // DB에서 일정 목록 가져오기

            // 문자열 배열로 변환
            String[] scheduleArray = schedules.toArray(new String[0]);

            // 응답 본문 설정
            response = new Protocol(Protocol.TYPE7_VIEW_RES, Protocol.T7_CODE0_SELECTION_SCHEDULENAME_LIST);
            response.setBody(scheduleArray, 5);
            send(response);
        } catch (Exception e) {
            // 오류 처리
            response = new Protocol(Protocol.TYPE7_VIEW_RES, Protocol.T7_CODE18_FAIL);
            send(response);
        }

        return response;
    }

    public Protocol handleViewAllScheduleSRequest(Protocol protocol) throws IOException {
        Protocol response;
        try {
            // 데이터베이스에서 일정 조회
//            List<String> schedules = fetchAllSchedulesFromDB(); // DB에서 일정 목록 가져오기
            //테스트용
            List<String> schedules = fetchMockSchedules(); // DB에서 일정 목록 가져오기

            // 문자열 배열로 변환
            String[] scheduleArray = schedules.toArray(new String[0]);

            // 응답 본문 설정
            response = new Protocol(Protocol.TYPE7_VIEW_RES, Protocol.T7_CODE17_SELECTION_PERIOD_OF_SCHEDULE_LIST);
            response.setBody(scheduleArray, 5);
            send(response);
        } catch (Exception e) {
            // 오류 처리
            response = new Protocol(Protocol.TYPE7_VIEW_RES, Protocol.T7_CODE18_FAIL);
            send(response);
        }

        return response;
    }

    private List<String> fetchMockSchedules() {
        // 테스트용 일정 데이터
        return Arrays.asList(
                "Meeting with Team A at 10:00 AM",
                "Lunch Break at 12:00 PM",
                "Project Review at 2:00 PM",
                "Weekly Sync-Up at 4:00 PM"
        );
    }

    public Protocol read() throws IOException {
        byte[] header = new byte[Protocol.LEN_HEADER];
        Protocol protocol = new Protocol();
        int totalReceived = 0;
        int readSize = 0;
        is.read(header, 0, Protocol.LEN_HEADER);
        protocol.setPacketHeader(header);
        byte[] buf = new byte[protocol.getBodyLength()];
        while (totalReceived < protocol.getBodyLength()) {
            readSize = is.read(buf, totalReceived, protocol.getBodyLength() - totalReceived);
            totalReceived += readSize;
        }
        protocol.setPacketBody(buf);
        return protocol;
    }


}
