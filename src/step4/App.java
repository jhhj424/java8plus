package step4;

import java.util.ArrayList;
import java.util.List;

/*
 [ Optional ]

 Optional
 ● 오직 값 한 개가 들어있을 수도 없을 수도 있는 컨네이너.

 주의할 것
 ● 리턴값으로만 쓰기를 권장한다. (메소드 매개변수 타입, 맵의 키 타입, 인스턴스 필드 타입으로 쓰지 말자.)
 ● Optional을 리턴하는 메소드에서 null을 리턴하지 말자.
 ● 프리미티브 타입용 Optional을 따로 있다. OptionalInt, OptionalLong,...
 ● Collection, Map, Stream Array, Optional은 Opiontal로 감싸지 말 것.

 */
public class App {

    public static void main(String[] args) {
        List<OnlineClass> springClasses = new ArrayList<>();
        springClasses.add(new OnlineClass(1, "spring boot", true));
        springClasses.add(new OnlineClass(2, "spring data jpa", true));
        springClasses.add(new OnlineClass(3, "spring mvc", false));
        springClasses.add(new OnlineClass(4, "spring core", false));
        springClasses.add(new OnlineClass(5, "rest api development", false));

        OnlineClass spring_boot = new OnlineClass(1, "spring boot", true);
    }

}
