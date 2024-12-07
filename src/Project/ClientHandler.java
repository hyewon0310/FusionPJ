package Project;

import java.io.*;
import java.net.Socket;

public class ClientHandler {
    private static Socket clientSocekt;
    private static InputStream is;
    private static OutputStream os;
    // private static BufferedReader br;
    // private static BufferedWriter bw;
    private static final int MAX_RETRYCOUNT = 3;


    // 클라이언트 관리자 생성자
    public ClientHandler() {
        try {
            clientSocekt = new Socket("192.168.0.57", 3000);
            // 서버의 ip번호랑 port 번호
            is = clientSocekt.getInputStream();
            // buffer 정의를 여기서 해주어야 하는 지 고민
            //br = new BufferedReader(new InputStreamReader(is));
            os = clientSocekt.getOutputStream();
            //bw = new BufferedWriter(new OutputStreamWriter(os));
            System.out.println("Client Connected / 서버 접속 중입니다.");
        } catch (IOException ioException) {
            System.err.println(ioException);
        }
    }

    // 프로토콜 송신
    //private static void send(Protocol protocol) throws Exception {
    private void send(Protocol protocol) throws Exception {

       try {
            os.write(protocol.createPacket());
            System.out.println("서버에게" + " 전송");
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    // 프로토콜 수신
    //private static Protocol receive(int type, int code) throws Exception
    private Protocol receive(int type, int code) throws Exception {
        // header부터 처리
        int retryCount = 0;
        byte[] header = new byte[Protocol.LEN_HEADER];
        Protocol protocol = new Protocol();
        while (retryCount < MAX_RETRYCOUNT) {
            try {
                // 프로토콜 헤더 수신 및 설정
                is.read(header, 0, Protocol.LEN_HEADER);
                protocol.setPacketHeader(header);

                // 본문 길이 확인
                int bodyLength = protocol.getBodyLength();
                if (bodyLength < 0 || bodyLength > Protocol.LEN_BODYLENGTH) {
                    throw new Exception("통신오류: 비정상적인 본문 길이");
                }

                // 프로토콜 본문 수신
                byte[] buf = new byte[bodyLength];
                int ReceivedLength = 0;
                while (ReceivedLength < bodyLength) {
                    int readSize = is.read(buf, ReceivedLength, bodyLength - ReceivedLength);
                    if (readSize == -1) {
                        throw new Exception("통신오류: 연결 끊어짐");
                    }
                    ReceivedLength += readSize;
                }
                protocol.setPacketBody(buf);

                // 타입 검사: 성공 시 반환
                if (protocol.getType() == type && protocol.getCode() == code) {
                    return protocol; // 성공하면 종료
                }
                // 타입이 맞지 않으면 다음 시도
                retryCount++;
            } catch (IOException e) {
                // 연결 실패 등 예외 발생 시
                retryCount++;
                System.err.println("수신 실패 시도: " + retryCount + " / " + MAX_RETRYCOUNT);
            }
        }
        throw new Exception("통신오류: 응답 타임아웃");
    }

    // 선발 일정 및 비용 확인 조회 요청 및 확인
    // public static String[] scheduleList_viewReq() throws Exception {
//    public String[] scheduleList_viewReq() throws Exception {
//        Protocol requestNameList = new Protocol(Protocol.TYPE6_VIEW_REQ, Protocol.T6_CODE0_SELECTION_SCHEDULENAME_LIST);
//        send(requestNameList);
//        Protocol responseNameList = receive(Protocol.TYPE7_VIEW_RES, Protocol.T7_CODE0_SELECTION_SCHEDULENAME_LIST);
//        String[] nameList= (responseNameList.getBody());
//
//        Protocol requestPeriodList = new Protocol(Protocol.TYPE6_VIEW_REQ, Protocol.T6_CODE17_SELECTION_PERIOD_OF_SCHEDULE_LIST);
//        send(requestPeriodList);
//        Protocol responsePeriodList = new Protocol(Protocol.TYPE7_VIEW_RES, Protocol.T7_CODE17_SELECTION_PERIOD_OF_SCHEDULE_LIST);
//        String[] periodList = responsePeriodList.getBody();
//        String[] result = new String[nameList.length];
//        for (int i = 0; i < nameList.length; i++)
//        {
//            result[i] = "입사 일정명 : " + nameList[i] + ", 입사 일정 기간 : " + periodList[i];
//        }
//        return result;
//    }

//    // public static String[] dormitoryFeeList_viewReq() throws Exception {
//    public String[] dormitoryFeeList_viewReq() throws Exception {
//        Protocol requestProtocol = new Protocol(Protocol.TYPE6_VIEW_REQ, Protocol.T6_CODE1_DORMITORY_FEE_LIST);
//        send(requestProtocol);
//        Protocol responseProtocol = receive(Protocol.TYPE7_VIEW_RES, Protocol.T7_CODE1_DORMITORY_FEE_LIST);
//        return responseProtocol.getBody();
//    }
//
//    // public static String[] mealCostList_viewReq() throws Exception {
//    public String[] mealCostList_viewReq() throws Exception {
//        Protocol requestProtocol = new Protocol(Protocol.TYPE6_VIEW_REQ, Protocol.T7_CODE2_MEAL_COST_LIST);
//        send(requestProtocol);
//        Protocol responseProtocol = receive(Protocol.TYPE7_VIEW_RES, Protocol.T7_CODE2_MEAL_COST_LIST);
//        return responseProtocol.getBody();
//    }
//
//    // public static int scheduleName_registerReq(String scheduleName) throws Exception {
//    public int scheduleName_registerReq(String scheduleName) throws Exception {
//        Protocol requestProtocol = new Protocol(Protocol.TYPE2_REGISTER_REQ, Protocol.T2_CODE4_SELECTION_SCHEDULE_NAME);
//        requestProtocol.setBody(scheduleName);
//        send(requestProtocol);
//        Protocol responseProtocol = receive(Protocol.TYPE3_REGISTER_RES, Protocol.T3_CODE0_SUCCESS);
//        return responseProtocol.getCode();
//    }
}
