package Project;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws Exception{
        Scanner scanner = new Scanner(System.in);
        ClientHandler clientHandler = new ClientHandler();
//        String[] result = clientHandler.scheduleList_viewReq();
//        for (int i = 0; i < result.length; i++) {
//            System.out.println(result[i]);
//        }

        System.out.println("기숙사 이름 입력 : ");
        String name = scanner.nextLine().trim();
        System.out.println("기숙사 유형 입력 : ");
        String type = scanner.nextLine().trim();
        System.out.println("기숙사 성별 입력 : ");
        String gender = scanner.nextLine().trim();

        System.out.println("=====기숙사 정보 출력=====");
        String[] result = clientHandler.dormitoryInfo_viewReq(name, type, gender);
        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }
//        Protocol protocol = new Protocol();
//        byte[] buffer = protocol.getPacket();
//        System.out.print(clientHandler.login("20231089", "123456")[0]);
    }
}