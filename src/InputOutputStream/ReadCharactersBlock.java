package InputOutputStream;

import java.io.*;
public class ReadCharactersBlock
{
    public static void main(String args[]) throws java.io.IOException {
        byte[] buffer = new byte[80];
        int numberRead;
        while((numberRead = System.in.read(buffer)) >= 0) // 내용을 블록단위로 읽어 buffer에 저장
            System.out.write(buffer, 0, numberRead);
    }
}