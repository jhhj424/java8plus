package step7;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME) // 얼마나 유지
@Target(ElementType.TYPE_USE)       // 어디에 선언
@Repeatable(ChickenContainer.class) // 반복할 수 있게 만들어줌 : 어노테이션을 중복으로 사용할 수 있게 함
public @interface Chicken {
    String value();
}
