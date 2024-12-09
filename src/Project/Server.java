package Project;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;
import java.sql.*;


public class Server {
    // 서버 : 클라이언트 관계 = 1 : 1 관계
    private static ServerSocket serverSocket;  // 대기 소켓
    // 다중 처리 기술
    private static ServerThread[] clients;
    private static int clientCount;
    private InputStream is;
    private OutputStream os;

    public static void main(String[] args) {
        try {
            Server server = new Server();
            server.run();
        } catch (Exception e) {
            System.err.println("서버 실행 중 오류 발생: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // 서버 생성자
    public Server() throws IOException {
        serverSocket = new ServerSocket(7777);
        clients = new ServerThread[50]; // 최대 50명 수용 -> 수정 가능
        clientCount = 0;
    }


    // 구동
    public void run() throws Exception {
        while (serverSocket != null) {
            // 클라이언트 접속 대기 및 통신 담당 소켓 담당
            Socket commSocekt = serverSocket.accept();  // 통신 소켓
            System.out.println("새 클라이언트 접속: " + commSocekt.getInetAddress().getHostAddress());

            //DB 연결 추가하기
            Connection connection;
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost/mydb?characterEncoding=utf8&serverTimezone=UTC&useSSL=false";
            String user = "root";
            String pw = "kwom@9604844";
            connection = DriverManager.getConnection(url, user, pw);
            System.out.println("DB연결 성공");


            if (connection == null) {
                System.err.println("DB 연결 실패. 클라이언트 접속을 거부합니다.");
                commSocekt.close();
                continue;
            }

//            addThread(commSocekt, conn);
            addThread(commSocekt, connection);
        }
    }

      // 쓰레드 동기화
    public synchronized void addThread(Socket socket, Connection connection) throws Exception {
        if (clientCount < clients.length) {
            clients[clientCount] = new ServerThread(socket, connection);
            clients[clientCount].start();
            clientCount++;
        } else {
            System.out.println("Client refused: maximum " + clients.length + " reached.");
        }
    }

    // 나중에 예외처리 추가하기
//    private synchronized void addThread(Socket socket, Connection connection) {
//        if (clientCount < MAX_CLIENTS) {
//            ServerThread serverThread = new ServerThread(socket, connection);
//            clients[clientCount] = serverThread;
//            serverThread.start();
//            clientCount++;
//            System.out.println("클라이언트 연결 수: " + clientCount);
//        } else {
//            System.err.println("최대 클라이언트 수에 도달했습니다. 클라이언트 거부됨.");
//            try {
//                socket.close();
//            } catch (IOException e) {
//                System.err.println("클라이언트 소켓 닫기 실패: " + e.getMessage());
//            }
//        }
//    }


    public synchronized static void remove(int targetPortID) throws IOException {
        int pos = findClient(targetPortID);
        if (pos >= 0) {
            ServerThread toTerminate = clients[pos];    // 변수 네이밍은 나중에 생각
            if (pos < clientCount - 1)
                for (int i = pos + 1; i < clientCount; i++)
                    clients[i - 1] = clients[i];
            clientCount--;
            // System.out.print("Current Using Client Number : ");
            // System.out.print(clientCount);
            toTerminate.close();
            toTerminate.stop();
        }
    }

    public static int findClient(int targetPortID) {
        for (int i = 0; i < clientCount; i++)
            if (clients[i].getPortID() == targetPortID)
                return i;
        return -1;
    }
}