package step2;

import java.util.*;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) {
        Foo foo = new DefaultFoo("jiho");

        // 구현체에서 구현한 메소드
        foo.pringName(); // jiho

        // printNameUpperCase : 디폴트 메소드
        foo.printNameUpperCase(); // JIHO

        // Foo 인터페이스에서 정의된 static 메소드!
        Foo.printAnything(); // static

        System.out.println("---------------------------");
        /* --------------------------------------------- */

        List<String> name = new ArrayList<>();
        name.add("A");
        name.add("B");
        name.add("C");
        name.add("D");

        // 아래 두개 문법은 같음
        // 1. 람다
        name.forEach(s -> {
            System.out.println(s); // A, B, C ,D
        });
        System.out.println("---------------------------");
        // 2. 메소드 레퍼런스
        name.forEach(System.out::println); // A, B, C ,D

        System.out.println("---------------------------");
        // 쪼갤 수 있는 기능을 가진 Iterator
        // 아래의 예제 : name 리스트를 반으로 쪼개어서 각각 spliterator, spliterator1에 넣고 출력함
        Spliterator<String> spliterator = name.spliterator(); // 리스트
        Spliterator<String> spliterator1 = spliterator.trySplit(); // list 앞에서 반 -> spliterator 에서 반을 쪼갰기 때문에 spliterator에는 뒤에 반만 남음
        while(spliterator.tryAdvance(System.out::println)); // C, D
        System.out.println("===========================");
        while(spliterator1.tryAdvance(System.out::println)); // A, B

        System.out.println("---------------------------");
        // Stream
        // 아래의 예제 : name 리스트 각각의 요소를 Uppercase로 변경하고 "A"로 시작하는 것들의 갯수만 센다(count).
        long k = name.stream().map(String::toUpperCase)
                     .filter(s -> s.startsWith("A"))
                     .count();
        System.out.println(k); // 1

        System.out.println("---------------------------");
        // removeIf
        name.removeIf(s -> s.startsWith("C"));
        name.forEach(System.out::println); // A, B, D

        System.out.println("---------------------------");
        // Comparator
        name.sort(String::compareToIgnoreCase); // 문자열 오름차순 정렬
        System.out.println(name);
        Comparator<String> stringComparator = String::compareToIgnoreCase;
        name.sort(stringComparator.reversed()); // 문자열 내림차순 정렬
        System.out.println(name);
//        name.sort(stringComparator.reversed().thenComparing(stringComparator)); // 1차정렬 이후(then) 추가 정렬기준 정의
//        System.out.println(name);
    }
}