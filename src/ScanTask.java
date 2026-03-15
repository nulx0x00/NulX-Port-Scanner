import java.io.IOException;
import java.net.*;

public class ScanTask implements Runnable {

    private final String target;
    private final int port;
    private final int TIMEOUT; // 1 second timeout

    public ScanTask(String target, int port,  int timeout) {
        this.target = target;
        this.port = port;
        this.TIMEOUT=timeout;
    }

    @Override
    public void run() {
        try {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress(target, port), TIMEOUT);
            socket.close();
            System.out.println("[+] Port " + port + " OPEN");
        }
        catch (SocketTimeoutException e)
        {
            System.out.println("[+] Port " + port + " Closed/{timed-out}");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}