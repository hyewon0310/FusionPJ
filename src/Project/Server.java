package Project;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;
import java.sql.*;
import java.util.ArrayList;

public class Server {
    // 서버 : 클라이언트 관계 = 1 : 1 관계
    // 인텔리제이가 제안하는 것이
    // 1. ServerSocket의 객체를 final로 만들수 있다.
    // 2. ServerThread 배열과 clientCount 변수는 static 안사용해도 되지 않을까?
    // 다음에 대해서는 언니랑 이야기 해보기

    private static ServerSocket serverSocket;  // 대기 소켓
    // 다중 처리 기술
    private static ServerThread clients[];
    private static int clientCount;

    public static void main(String[] args) throws Exception {
        Server server = new Server();
        server.run();
    }

    // 서버 생성자
    public Server() throws IOException {
        serverSocket = new ServerSocket();
        boolean on = true;
        serverSocket.setReuseAddress(true);
        // SO_TIMEOUT 옵션과 SO_KEEPALIVE 옵션 설정을 해줄지에 대해 얘기해보기

        // 서버의 port 번호 설정
        SocketAddress serverAddress = new InetSocketAddress(3000);
        serverSocket.bind(serverAddress);

        // pool로 구현할 수 있으면 pool로 구현 수정하기
        clients = new ServerThread[50]; // 최대 50명 수용 -> 수정 가능
        clientCount = 0;
    }

    // 클라이언트에게 서버의 ip를 알려주는 메소드
    public static String getServerAddress() throws IOException {
            InetAddress localHost = InetAddress.getLocalHost();
            return localHost.getHostAddress();
        }
    }


        // 구동
    public void run() {
        // 클라이언트 접속 대기 및 통신 담당 소켓 담당
        Socket commSocekt = serverSocket.accept();  // 통신 소켓
        //DB 연결 추가하기
    }
}
