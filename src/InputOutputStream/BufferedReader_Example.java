package InputOutputStream;
import java.io.*;

public class BufferedReader_Example {
    public static void main(String[] args) throws IOException {
        InputStream is = System.in;
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);

        String s = br.readLine();   // 한 줄의 데이터 읽은 후 변수 s에 저장
        System.out.println(s);
    }
}
