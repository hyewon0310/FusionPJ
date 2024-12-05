package InputOutputStream;

import java.util.Scanner;
public class IO_Example {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Name: ");
        String name = sc.next();    // 한 글자 입력 받는 메소드

        System.out.print("Age: ");
        int age = sc.nextInt();

        System.out.println("Name " +name + ", Age " + age);

        sc.close();
    }
}