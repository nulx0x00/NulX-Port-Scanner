import java.net.*;

public class SequentialScanner {
    public static void scan(String target, int start, int end) {
        System.out.println("[*] Starting Sequential Scan...");

        for (int port = start; port <= end; port++) {
            try {
                Socket socket = new Socket();
                socket.connect(new InetSocketAddress(target, port), 1000);
                socket.close();
                System.out.println("[+] Port " + port + " OPEN");
            } catch (Exception ignored) {

            }
        }
    }
}