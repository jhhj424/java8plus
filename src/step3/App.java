package step3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/*

 [ Stream ]
 ● 데이터를 담고 있는 저장소 (컬렉션)이 아니다.
 ● Funtional in nature, 스트림이 처리하는 데이터 소스를 변경하지 않는다.
 ● 스트림으로 처리하는 데이터는 오직 한번만 처리한다.
 ● 무제한일 수도 있다. (Short Circuit 메소드를 사용해서 제한할 수 있다.)
 ● 중개 오퍼레이션은 근본적으로 lazy 하다. ( 중개 오퍼레이션 :
 ● 손쉽게 병렬 처리할 수 있다.


(중개 오퍼레이션)
● Stream을 리턴한다.
● Stateless / Stateful 오퍼레이션으로 더 상세하게 구분할 수도 있다. (대부분은 Stateless지만 distinct나 sorted 처럼 이전 이전 소스 데이터를 참조해야 하는 오퍼레이션은 Stateful 오퍼레이션이다.)
● filter, map, limit, skip, sorted, ...

(종료 오퍼레이션)
● Stream을 리턴하지 않는다.
● collect, allMatch, count, forEach, min, max, ...

 - 종료 오퍼레이션이 없다면 해당 Stream의 중개 오퍼레이션은 실행되지 않는다. -

 */
public class App {
    public static void main(String[] args) {
        List<String> names= new ArrayList<>();
        names.add("a");
        names.add("b");
        names.add("c");
        names.add("d");

        List<String> collect = names.stream().map(s -> {
            System.out.println(s);
            return s.toUpperCase();
        }).collect(Collectors.toList());
        collect.forEach(System.out::println);

        System.out.println("------------------");

        names.forEach(System.out::println);

        System.out.println("------------------");

        // 병렬처리 스트림 - 데이터가 정말 방대한경우에 사용..
        List<String> collect1 = names.parallelStream().map(s -> {
                    System.out.println(s + " " + Thread.currentThread().getName());
                    return s.toUpperCase();
                }).collect(Collectors.toList());
//        collect1.forEach(System.out::println);

        System.out.println("------------------");
        // 일반처리 스트림
        List<String> collect2 = names.stream().map(s -> {
            System.out.println(s + " " + Thread.currentThread().getName());
            return s.toUpperCase();
        }).collect(Collectors.toList());
//        collect2.forEach(System.out::println);
    }
}
