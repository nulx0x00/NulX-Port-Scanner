import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("******// NULX PORT SCANNER //******");

        // 1. Get Target
        System.out.print("ENTER URL (e.g., scanme.nmap.org): ");
        String target = input.nextLine();

        // 2. Get Range
        System.out.print("Start Port: ");
        int start = input.nextInt();
        System.out.print("End Port: ");
        int end = input.nextInt();


        // 3. Choose Mode
        System.out.println("\nSelect Mode:");
        System.out.println("1. Sequential (Slow)");
        System.out.println("2. Concurrent (Fast)");
        System.out.print("Choice: ");
        int choice = input.nextInt();


        long startTime = System.currentTimeMillis();

        if (choice == 1) {
            SequentialScanner.scan(target, start, end);
        } else {
            ConcurrentScanner.scan(target, start, end);
        }


        long endTime = System.currentTimeMillis();
        System.out.println("--------------------------------");
        System.out.println("Total Time: " + (endTime - startTime) + " ms");
        System.out.println("--------------------------------");
    }
}