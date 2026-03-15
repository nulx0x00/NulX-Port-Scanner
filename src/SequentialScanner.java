import java.io.IOException;
import java.net.*;
import java.util.*;

public class SequentialScanner {
    public static void scan(String target, int start, int end, int timeout) {
        System.out.println("[*] Starting Sequential Scan...");
        int openports = 0;
        ArrayList<Integer> openport = new ArrayList<>();

        for (int port = start; port <= end; port++) {
            try {
                Socket socket = new Socket();
                socket.connect(new InetSocketAddress(target, port), timeout);
                socket.close();
                System.out.println("[+] Port " + port + " OPEN");
                openport.add(port);
                openports += 1;

            }
            catch (SocketTimeoutException e)
            {
                System.out.println("[+] Port " + port + " Closed/{timed-out}");
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        System.out.print("The Total no. of open port: " +openports);
        System.out.print("{");
        for (int elements : openport){
            System.out.print(elements+", ");
        }
        System.out.println("}");
    }
}