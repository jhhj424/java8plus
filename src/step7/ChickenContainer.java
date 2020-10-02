package step7;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) // 얼마나 유지
@Target(ElementType.TYPE_USE)       // 어디에 선언
public @interface ChickenContainer {

    Chicken[] value();
}
