package step6;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/*

    [ CompletableFuture ]

    자바에서 비동기(Asynchronous) 프로그래밍을 가능케하는 인터페이스.
      ● Future를 사용해서도 어느정도 가능했지만 하기 힘들 일들이 많았다.

    Future로는 하기 어렵던 작업들
      ● Future를 외부에서 완료 시킬 수 없다. 취소하거나, get()에 타임아웃을 설정할 수는 있다.
      ● 블로킹 코드(get())를 사용하지 않고서는 작업이 끝났을 때 콜백을 실행할 수 없다.
      ● 여러 Future를 조합할 수 없다, 예) Event 정보 가져온 다음 Event에 참석하는 회원 목록 가져오기
      ● 예외 처리용 API를 제공하지 않는다.

    조합하기
      ● thenCompose(): 두 작업이 서로 이어서 실행하도록 조합
      ● thenCombine(): 두 작업을 독립적으로 실행하고 둘 다 종료 했을 때 콜백 실행
      ● allOf(): 여러 작업을 모두 실행하고 모든 작업 결과에 콜백 실행
      ● anyOf(): 여러 작업 중에 가장 빨리 끝난 하나의 결과에 콜백 실행

    예외처리
      ● exeptionally(Function)
      ● handle(BiFunction)

 */
public class App6 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello  " + Thread.currentThread().getName());
            return "Hello";
        });

        // thenCompose : 두개의 비동기 쓰레드를 연결
        CompletableFuture<String> future = hello.thenCompose(App6::getWorld);
        System.out.println(future.get());



        // 두개의 비동기 쓰레드가 의존성이 없는경우
        hello = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello  " + Thread.currentThread().getName());
            return "Hello";
        });

        CompletableFuture<String> world = CompletableFuture.supplyAsync(() -> {
            System.out.println("world  " + Thread.currentThread().getName());
            return "world";
        });

        CompletableFuture<String> future1 = hello.thenCombine(world, (h, w) -> h + " " + w);
        System.out.println(future1);


        // 쓰레드가 두개 이상일때 모든 테스크를 합쳐서 실행 : 블록킹되지않음
        List<CompletableFuture<String>> futures = Arrays.asList(hello, world);
        CompletableFuture[] futuresArray = futures.toArray(new CompletableFuture[futures.size()]);

        CompletableFuture<List<String>> result = CompletableFuture.allOf(futuresArray)
                .thenApply(v -> futures.stream()
                        .map(CompletableFuture::join)
                        .collect(Collectors.toList()));

        result.get().forEach(System.out::println);

        // 에러처리 : handle
        boolean throwError = true;
        CompletableFuture<String> handle = CompletableFuture.supplyAsync(() -> {
            if (throwError) {
                throw new IllegalArgumentException();
            }

            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello ";
        }).handle((r, e) -> {
            if (e != null) {
                System.out.println(e);
                return "ERROR!";
            }
            return r;
        });
    }

    private static CompletableFuture<String> getWorld(String message) {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println(message + Thread.currentThread().getName());
            return message + " world";
        });
    }
}
