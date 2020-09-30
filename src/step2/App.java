package step2;

public class App {
    public static void main(String[] args) {
        Foo foo = new DefaultFoo("jiho");

        // 구현체에서 구현한 메소드
        foo.pringName();

        // printNameUpperCase : 디폴트 메소드
        foo.printNameUpperCase();

        // Foo 인터페이스에서 정의된 static 메소드!
        Foo.printAnything();
    }
}