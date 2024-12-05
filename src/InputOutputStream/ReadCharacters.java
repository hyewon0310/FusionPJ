package InputOutputStream;

import java.io.*;
public class ReadCharacters {
    public static void main(String args[]) throws java.io.IOException {
        int data;
        while ((data = System.in.read()) >= 0) // 스트림에서 한 문자씩 읽음
            System.out.write(data);
    }
}