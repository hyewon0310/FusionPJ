package Project;

import java.io.*;
import java.net.*;
import java.util.Arrays;

public class Client {
//    public static void main(String[] args) throws Exception{
//        ClientHandler clientHandler = new ClientHandler();
//        String[] result = clientHandler.scheduleList_viewReq();
//        for (int i = 0; i < result.length; i++) {
//            System.out.println(result[i]);
//        }
////        Protocol protocol = new Protocol();
////        byte[] buffer = protocol.getPacket();
////        System.out.print(clientHandler.login("20231089", "123456")[0]);
//    }
public static void main(String[] args)  {
    try {
        ClientHandler clientHandler = new ClientHandler();
        String[] result = clientHandler.scheduleList_viewReq();
        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }
//        System.out.println("[TEST] 특정 학생의 생활관 비용 조회");
//        String studentFee = clientHandler.requestStudentFee("20201111");
//        System.out.println("[RESULT] " + studentFee);
//
//        System.out.println("[TEST] 생활관별 납부자 조회");
//        String[] paidStudents = clientHandler.requestPaidStudents(1);
//        System.out.println("[RESULT] " + Arrays.toString(paidStudents));
//
//        System.out.println("[TEST] 생활관별 미납부자 조회");
//        String[] unpaidStudents = clientHandler.requestUnpaidStudents(1);
//        System.out.println("[RESULT] " + Arrays.toString(unpaidStudents));

    } catch (Exception e) {
        e.printStackTrace();
    }
}


}