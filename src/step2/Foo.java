package step2;

public interface Foo {

    void pringName();


    // 인터페이스의 디폴트 메소드 : 해당 인터페이스를 implement해도 해당 메소드는 필수로 구현하지 않아도 된다. 그리고 해당 메소드는 모든 구현체에서 사용할 수 있다.
    // @implSpec 을 사용하여 설명 적어주는것이 좋음

    /**
     * @implSpec 이 구현체는 getName()으로 가져온 문자열을 대문자로 바꿔 출력한다.
     */
    default void printNameUpperCase() {
        System.out.println(getName().toUpperCase());
    }

    // Object 클래스의 디폴트 메소드는 오버라이딩(재정의) 불가능
//    default String toString() {
//
//    }

    static void printAnything() {
        System.out.println("static");
    }

    String getName();

}
