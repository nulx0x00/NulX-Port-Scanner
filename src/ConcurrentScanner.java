import java.util.Scanner;
import java.util.concurrent.*;

public class ConcurrentScanner {
    public static void scan(String target, int start, int end , int timeout) {

        Scanner input = new Scanner(System.in);
        System.out.print("Threads: ");
        int thread = input.nextInt();

        System.out.println("[*] Starting Concurrent Scan ("+thread+" Threads)...");
        ExecutorService worker = Executors.newFixedThreadPool(thread);

        for (int port = start; port <= end; port++) {
            worker.execute(new ScanTask(target, port,timeout ));
        }

        worker.shutdown();
        try {
            worker.awaitTermination(timeout, TimeUnit.SECONDS);
        }
        catch (InterruptedException e) {
            worker.shutdownNow();
        }
    }
}