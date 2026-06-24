import java.util.Scanner;

public class P2_Sol {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int[] records;
        do {
            try {
                System.out.println("Battle records considered:");
                String[] recordsStr = sc.nextLine().split(" ");
                records = new int[recordsStr.length];
                for (int i = 0; i < recordsStr.length; i++) {
                    records[i] = Integer.parseInt(recordsStr[i]);
                }
            } catch (Exception e) {
                records = null;
                System.out.println("Invalid input.");
            }
        } while (records == null);
        System.out.println("Enter the trainer you wish to analyse (A or B): ");
        char trainer = ' ';
        do {
            String trainerStr = sc.nextLine().toUpperCase();
            if (trainerStr.length() != 1) {
                System.out.println("Invalid input. Please enter only one character.");
            } else {
                trainer = trainerStr.charAt(0);
            }
            if (trainer != 'A' && trainer != 'B') System.out.println("Invalid input. Please enter A or B.");
        } while (trainer != 'A' && trainer != 'B');
        System.out.println();

        long start, end;
        start = System.nanoTime();
        int res = netDamageIterative(records, trainer);
        char other = trainer == 'A' ? 'B' : 'A';
        int resOther = netDamageIterative(records, other);
        end = System.nanoTime();
        System.out.println("Net damage Trainer " + trainer + " (Iterative):     " + res + "  (Time: " + (end - start) + " ns)");
        System.out.println();
        char winner = res > resOther ? trainer : other;
        System.out.println("The winner is Trainer " + winner + "!");
    }

    private static int netDamageIterative(int[] records, char trainer) {
        int m = trainer == 'A' ? 1 : -1;
        int total = 0;
        for (int record : records) {
            if (record * m > 0) total += record * m;
        }
        return total;
    }
}
