package Project;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;
import java.sql.*;
import java.util.ArrayList;

public class Server {
    // 서버 : 클라이언트 관계 = 1 : 1 관계
    private static ServerSocket serverSocket;  // 대기 소켓
    // 다중 처리 기술
    private static ServerThread clients[];
    private static int clientCount;
    private InputStream is;
    private OutputStream os;

//    public static void main(String[] args) throws Exception {
//        Server server = new Server();
//        server.run();
//    }

    // 서버 생성자
    public Server() throws IOException {
        serverSocket = new ServerSocket(7777);
        clients = new ServerThread[50]; // 최대 50명 수용 -> 수정 가능
        clientCount = 0;
    }

    // 구동
//    public void run() throws IOException {
//        while (serverSocket != null) {
//            // 클라이언트 접속 대기 및 통신 담당 소켓 담당
//            Socket commSocekt = serverSocket.accept();  // 통신 소켓
//            //DB 연결 추가하기
//
//            addThread(commSocekt, conn);
//        }
//    }

    //  쓰레드 동기화
//    public synchronized void addThread(Socket socket, Connection conn) throws Exception {
//        if (clientCount < clients.length) {
//            clients[clientCount] = new ServerThread(socket, conn);
//            clients[clientCount].start();
//            clientCount++;
//        } else {
//            System.out.println("Client refused: maximum " + clients.length + " reached.");
//        }
//    }

//    public synchronized static void remove(int targetPortID) throws IOException {
//        int pos = findClient(targetPortID);
//        if (pos >= 0) {
//            ServerThread toTerminate = clients[pos];    // 변수 네이밍은 나중에 생각
//            if (pos < clientCount - 1)
//                for (int i = pos + 1; i < clientCount; i++)
//                    clients[i - 1] = clients[i];
//            clientCount--;
//            // System.out.print("Current Using Client Number : ");
//            // System.out.print(clientCount);
//            toTerminate.close();
//            toTerminate.stop();
//        }
//    }
//
//    public static int findClient(int targetPortID) {
//        for (int i = 0; i < clientCount; i++)
//            if (clients[i].getPortID() == targetPortID)
//                return i;
//        return -1;
//    }
}