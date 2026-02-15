import java.util.concurrent.*;

public class ConcurrentScanner {
    public static void scan(String target, int start, int end) {


        System.out.println("[*] Starting Concurrent Scan (50 Threads)...");
        ExecutorService worker = Executors.newFixedThreadPool(50);

        for (int port = start; port <= end; port++) {
            worker.execute(new ScanTask(target, port));
        }

        worker.shutdown();
        try {
            worker.awaitTermination(1000, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            worker.shutdownNow();
        }
    }
}