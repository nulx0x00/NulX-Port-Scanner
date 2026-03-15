import java.io.IOException;
import java.net.*;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ScanTask implements Runnable {

    private final String target;
    private final int port;
    private final int TIMEOUT;
    private final ConcurrentLinkedQueue<Integer> openPorts;

    public ScanTask(String target, int port, int timeout, ConcurrentLinkedQueue<Integer> openPorts) {
        this.target = target;
        this.port = port;
        this.TIMEOUT = timeout;
        this.openPorts = openPorts;
    }

    @Override
    public void run() {
        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress(target, port), TIMEOUT);
            System.out.println("[+] Port " + port + " OPEN");
            openPorts.add(port);
        } catch (IOException ignored) {}
    }
}