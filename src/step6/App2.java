package step6;

/*
CompletableFuture

Concurrent 소프트웨어 : 동시에 여러 작업을 할 수 있는 소프트웨어

 */
public class App2 {

    // 쓰레드의 실행 순서는 보장할 수 없다.

    public static void main(String[] args) throws InterruptedException {
        // 람다를 이용한 쓰레드 사용
        Thread thread = new Thread(() -> {
            System.out.println("Thread1 " + Thread.currentThread().getName());
        });
        // Runnable 구현 쓰레드 사용
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread2 " + Thread.currentThread().getName());
            }
        });
        thread.start();
        t.start();

        System.out.println("Hello " + Thread.currentThread().getName());


        // 쓰레드 sleep  ->  다른 쓰레드를 먼저 처리함
        Thread thread1 = new Thread(() -> {
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) { // 누군가가 이 쓰레드를 깨우면 catch
                e.printStackTrace();
            }
            System.out.println("Sleep " + Thread.currentThread().getName());
        });
        thread1.start();

        // Interrupt
        Thread thread2 = new Thread(() -> {
            while (true) {
                System.out.println("무한루프 Thread " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) { // 누군가가 이 쓰레드를 깨우면 catch
                    System.out.println("exit!");
                    return; // 종료
                }
            }
        });
        thread2.start();

        // 3초정도 뒤에 thread2를 깨움
        Thread.sleep(3000L);
        thread2.interrupt();

        // 쓰레드를 기다림
        thread2.join();
        System.out.println(thread2 + " is finished");
    }
}
