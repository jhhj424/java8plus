package step2;

public interface Bar extends Foo {

    // Foo에 정의된 디폴트 메소드인 printNameUpperCase를 디폴트로 사용하지 않기위해서는 추상메소드로 정의하면됨.
    void printNameUpperCase();

}
