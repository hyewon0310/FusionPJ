package Project;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

// 데이터베이스 DAO 연결 필요
// 프로토콜 연결 필요

public class ServerThread extends Thread {
    private int userID; // 로그인에 사용되는 ID
    private Socket socket;  // 각 클라이언트랑 연결하기 위한 서버의 소켓인 것으로 추정됨
    private Connection connect; // 데이터베이스랑 연결에 필요한 듯 - 아마 맞을 듯
    private Protocol protocol;
    // 입출력 관련 구현은 조금 생각해보고

    // 스레드 생성자
    public ServerThread(Socket socket, Connection connection) throws IOException
    {

    }




}
