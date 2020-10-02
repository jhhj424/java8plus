package step6;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*

    [ CompletableFuture ]

    자바에서 비동기(Asynchronous) 프로그래밍을 가능케하는 인터페이스.
      ● Future를 사용해서도 어느정도 가능했지만 하기 힘들 일들이 많았다.

    Future로는 하기 어렵던 작업들
      ● Future를 외부에서 완료 시킬 수 없다. 취소하거나, get()에 타임아웃을 설정할 수는 있다.
      ● 블로킹 코드(get())를 사용하지 않고서는 작업이 끝났을 때 콜백을 실행할 수 없다.
      ● 여러 Future를 조합할 수 없다, 예) Event 정보 가져온 다음 Event에 참석하는 회원 목록 가져오기
      ● 예외 처리용 API를 제공하지 않는다.


    비동기로 작업 실행하기
      ● 리턴값이 없는 경우: runAsync()
      ● 리턴값이 있는 경우: supplyAsync()
      ● 원하는 Executor(쓰레드풀)를 사용해서 실행할 수도 있다. (기본은 ForkJoinPool.commonPool())

    콜백 제공하기
      ● thenApply(Function): 리턴값을 받아서 다른 값으로 바꾸는 콜백
      ● thenAccept(Consumer): 리턴값을 또 다른 작업을 처리하는 콜백 (리턴없이)
      ● thenRun(Runnable): 리턴값 받지 다른 작업을 처리하는 콜백
      ● 콜백 자체를 또 다른 쓰레드에서 실행할 수 있다.

 */
public class App5 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        CompletableFuture<String> future = new CompletableFuture<>();
//        future.complete("Jiho");

        // 리턴이 없을때
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(() -> {
            System.out.println("Hello " + Thread.currentThread().getName());
        });
        voidCompletableFuture.get();

        // 리턴이 있을때
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello return  " + Thread.currentThread().getName());
            return "Hello return";
        });
        System.out.println(future.get());

        // 콜백 ( 리턴용 )
        future = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello return  " + Thread.currentThread().getName());
            return "Hello thenApply";
        }).thenApply(s -> {
            System.out.println(s + " thenApply " + Thread.currentThread().getName());
            return s;
        });

        System.out.println(future.get());


        // 콜백 ( void )
        CompletableFuture<Void> voidFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello return  " + Thread.currentThread().getName());
            return "Hello thenAccept";
        }).thenAccept(s -> {
            System.out.println(s + " thenAccept " + Thread.currentThread().getName());
        });
        voidFuture.get();


        // 바로 실행!
        CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello return  " + Thread.currentThread().getName());
            return "Hello thenRun";
        }).thenRun(() -> {
            System.out.println("thenRun " + Thread.currentThread().getName());
        });

        // ForkJoinPool : Executor 를 구현한 구현체
        // DeQueue 를 사용..


        // 직접 쓰레드 풀을 만들어서 사용하려면...
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello return  " + Thread.currentThread().getName());
            return "Hello thenRun";
        }, executorService).thenRunAsync(() -> {
            System.out.println("thenRun " + Thread.currentThread().getName());
        }, executorService);
    }
}
