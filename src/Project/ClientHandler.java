package Project;

import java.io.*;
import java.net.Socket;
import java.time.LocalDate;

public class ClientHandler {
    private static Socket clientSocekt;
    private static InputStream is;
    private static OutputStream os;
    // private static BufferedReader br;
    // private static BufferedWriter bw;

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
    private static void send(Protocol protocol) throws Exception {
        try {
            os.write(protocol.createPacket());
            System.out.println("서버에게" + " 전송");
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}

    // 프로토콜 수신
//    private static Protocol receive(int type) throws Exception {
//        // header부터 처리
//
//        byte[] header = new byte[Protocol.LEN_HEADER];
//        Protocol protocol = new Protocol();
//        // undefined 타입은 필요하면 추가해서 수정
//        try {
//            int totalReceived, readSize;
//            do {
//                totalReceived = 0;
//                readSize = 0;
//                is.read(header, 0, Protocol.LEN_HEADER);
//                protocol.setPacketHeader(header);
//
//                byte[] buf = new byte[protocol.getBodyLength()];
//                while (totalReceived < protocol.getBodyLength()) {
//                    readSize = is.read(buf, totalReceived, protocol.getBodyLength() - totalReceived);
//                    totalReceived += readSize;
//                    if (readSize == -1) {
//                        throw new Exception("통신오류: 연결 끊어짐");
//                    }
//                }
//                protocol.setPacketBody(buf);
//                if (protocol.getType() == Protocol.UNDEFINED)
//                    throw new Exception("통신오류: 서버에서 오류 발생함");
//                else if (protocol.getType() == type)
//                    return protocol;
//            } while (true); // 현재 필요한 응답이 아닐경우 무시하고 다음 응답을 대기
//        } catch (IOException e) {
//            throw new Exception("통신오류: 데이터 수신 실패함");
//        }
//    }
//
//    // 선발 일정 및 비용 확인 조회 요청 및 확인
//    public static String[] scheduleList_req() {
//        Protocol protocol = new Protocol(Protocol.TYPE6_VIEW_REQ, Protocol.T6_CODE0_SELECTION_SCHEDULE_LIST);
//    }
//}
