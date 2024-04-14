public class Main {
    private static volatile boolean isPing = true;
    private static int iterNum = 0;
    
    public static void main(String[] args) {
        new Thread(Main::ping).start();
        new Thread(Main::pong).start();
    }
    private static void ping() {
        while (true){
            if(isPing) {
                System.out.println(iterNum+ " Ping");
                iterNum++;
                isPing = false;
            }
        }
    }
    private static void pong() {
        while (true) {
            if (!isPing) {
                System.out.println(iterNum + "        Pong");
                iterNum++;
                isPing = true;
            }
        }
    }
}