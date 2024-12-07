package Project;

import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) throws Exception{
        ClientHandler clientHandler = new ClientHandler();
        // Protocol protocol = new Protocol();
        byte[] buffer = protocol.getPacket();
        System.out.print(clientHandler.login("20231089", "123456")[0]);
    }
}