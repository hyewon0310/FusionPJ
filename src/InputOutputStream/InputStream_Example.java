package InputOutputStream;
import java.io.*;

public class InputStream_Example {
    public static void main(String[] args) throws IOException {
        InputStream is = System.in; // 바이트 스트림
        int i = is.read();
        System.out.println(i);

        // 입력값 : software 출력값 : 115 (s의 아스키코드 값) , 나머지 입력값 oftware는 무시됨.
        // 입력값 : 123456 출력값 : 49 (1의 아스키코드 값) , 나머지 입력값 2345는 무시됨.
    }
}
