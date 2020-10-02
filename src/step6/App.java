package step6;

/*
CompletableFuture

Concurrent 소프트웨어 : 동시에 여러 작업을 할 수 있는 소프트웨어

 */
public class App {

    // 쓰레드의 실행 순서는 보장할 수 없다.
    // Thread클래스를 상속받은 클래스를 만들어서 쓰레드 사용
    public static void main(String[] args) {
        Thread myThread = new MyThread();
        myThread.start();

        System.out.println("Hello " + Thread.currentThread().getName());
    }

    static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("Thread " + Thread.currentThread().getName());
        }
    }

}
