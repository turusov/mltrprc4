import java.util.concurrent.Semaphore;

public class Main {
    private static boolean isPing = true;
    private static int iterNum = 0;
    private static Semaphore semaphore = new Semaphore(1, true);

    public static void main(String[] args) {
        new Thread(Main::ping).start();
        new Thread(Main::pong).start();
    }
    private static void ping() {
        while (true){
            try {
                semaphore.acquire();
                System.out.println(iterNum++ + " Ping");
                semaphore.release();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    private static void pong() {
        while (true) {
            try {
                semaphore.acquire();
                System.out.println(iterNum++ + "       Pong");
                semaphore.release();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}