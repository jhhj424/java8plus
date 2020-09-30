package step1;


import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

// 메소드 레퍼런스
public class App {

    public static void main(String[] args) {
//        UnaryOperator<String> hi = (s) -> "hi " + s;
        UnaryOperator<String> hi = Greeting::hi; // hi의 구현체를 Greeting 클래스의 static hi 메소드를 메소드 레퍼런스 하겠다. // :: - 메소드 레퍼런스
        System.out.println(hi.apply("JIHO"));

        Greeting greeting = new Greeting();
        UnaryOperator<String> hello = greeting::hello; // hi의 구현체를 Greeting 클래스 인스턴드의 hello 인스턴스 메소드를 메소드 레퍼런스 하겠다.
        System.out.println(hello.apply("jiho"));

        // 아무것도 없는 생성자
        Supplier<Greeting> supplier = Greeting::new;
        Greeting g = supplier.get();

        // 문자열을 받는 생성자
        Function<String, Greeting> jihoGreeting = Greeting::new;
        Greeting jiho = jihoGreeting.apply("jihoGreeting");
        System.out.println(jiho.getName());

        String[] names = {"Ajiho", "CJIHO", "Bjitoro"};
        Arrays.sort(names, String::compareToIgnoreCase);
        System.out.println(Arrays.toString(names));
    }
}
