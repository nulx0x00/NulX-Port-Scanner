import java.net.*;

public class ScanTask implements Runnable {

    private final String target;
    private final int port;
    private static final int TIMEOUT = 1000; // 1 second timeout

    public ScanTask(String target, int port) {
        this.target = target;
        this.port = port;
    }

    @Override
    public void run() {
        try {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress(target, port), TIMEOUT);
            socket.close();
            System.out.println("[+] Port " + port + " OPEN");
        } catch (Exception ignored) {

        }
    }
}