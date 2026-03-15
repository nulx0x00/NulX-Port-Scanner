import java.util.concurrent.*;

public class ConcurrentScanner {
    public static void scan(String target, int start, int end, int timeout, int thread) {

        ConcurrentLinkedQueue<Integer> openPorts = new ConcurrentLinkedQueue<>();

        System.out.println("[*] Starting Concurrent Scan (" + thread + " Threads)...");
        ExecutorService worker = Executors.newFixedThreadPool(thread);

        for (int port = start; port <= end; port++) {
            worker.execute(new ScanTask(target, port, timeout, openPorts));
        }

        worker.shutdown();
        try {
            worker.awaitTermination(timeout * (end - start + 1), TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            worker.shutdownNow();
        }

        System.out.print("Total open ports: " + openPorts.size() + " {");
        for (int p : openPorts) System.out.print(p + ", ");
        System.out.println("}");
    }
}