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
}
