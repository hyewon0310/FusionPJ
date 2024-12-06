package InputOutputStream;
import java.io.*;

public class InputStreamReader_Example {
    public static void main(String[] args) throws IOException {
        InputStream is = System.in; // 바이트 스트림
        InputStreamReader isr = new InputStreamReader(is);  // 문자 스트림

        int i = isr.read(); // 인수로 InputStream 객체를 받아 바이트 스트림을 문자 스트림으로 변환
        // 즉, InputStreamReader의 read() 메소드는 한 문자 데이터만 읽어온다. (입력값 software에서 oftware 무시됨)
        char ch = (char) i;
        System.out.println(ch);
    }
}
