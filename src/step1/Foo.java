package step1;

import java.util.function.IntConsumer;

public class Foo {
    public static void main(String[] args) {
        // 익명 내부 클래스 anonymous inner class
        RunSomething runSomething = (number) -> number + 100;
        System.out.println(runSomething.doIt(10));
        System.out.println(runSomething.doIt(10));
        System.out.println(runSomething.doIt(10));

        run();
    }

    private static void run() {
        int baseNumber = 10; //effective final

        // 람다
        IntConsumer printInt = (i) -> {
            System.out.println(baseNumber + i);
        };

        printInt.accept(10);
    }
}
