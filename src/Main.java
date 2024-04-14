public class Main {
    private static boolean isPing = true;
    private static int iterNum = 0;
    private static final Object lock = new Object();

    public static void main(String[] args) {
        new Thread(Main::ping).start();
        new Thread(Main::pong).start();
    }
    private static void ping() {
        while (true){
            synchronized (lock){
                if(isPing) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.println(iterNum + " Ping");
                iterNum++;
                isPing = true;
                lock.notify();
            }
        }
    }
    private static void pong() {
        while (true) {
            synchronized (lock){
                if(!isPing) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.println(iterNum++  + "       Pong");
                iterNum++;
                isPing = false;
                lock.notify();
            }
        }
    }
}