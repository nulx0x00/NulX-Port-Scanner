import java.util.concurrent.*;

public class ConcurrentScanner {
    public static void scan(String target, int start, int end , int timeout , int thread) {

        System.out.println("[*] Starting Concurrent Scan ("+thread+" Threads)...");
        ExecutorService worker = Executors.newFixedThreadPool(thread);

        for (int port = start; port <= end; port++) {
            worker.execute(new ScanTask(target, port,timeout ));
        }

        worker.shutdown();
        try {
            worker.awaitTermination(timeout * (end - start + 1), TimeUnit.MILLISECONDS);
        }
        catch (InterruptedException e) {
            worker.shutdownNow();
        }
    }
}