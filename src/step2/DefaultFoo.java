package step2;

/*

충돌되는 Default 메소드의 경우 구현체에서 오버라이딩(재정의) 해야함

 */
public class DefaultFoo implements Foo {

    String name;

    public DefaultFoo(String name) {
        this.name = name;
    }

    // printNameUpperCase 는 Foo 인터페이스의 디폴트 메소드지만 오버라이딩 해서 재정의 가능
//    @Override
//    public void printNameUpperCase() {
//        System.out.println(this.name.toUpperCase());
//    }

    @Override
    public void pringName() {

        // 둘다 같음
        System.out.println(this.name);
//        System.out.println(name);
    }

    @Override
    public String getName() {
        return this.name;
    }
}
