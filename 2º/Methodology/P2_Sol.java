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
        start = System.nanoTime();
        resOther = netDamageDivideAndConquer(records, trainer, 0, records.length - 1);
        end = System.nanoTime();
        System.out.println("Net damage Trainer " + trainer + " (Divide and Conquer): " + resOther + "  (Time: " + (end - start) + " ns)");
        System.out.println();
        char winner = calculateWinner(records);
        System.out.println("The winner is Trainer " + winner + "!");
    }

    private static char calculateWinner(int[] records) {
        int damageA = netDamageDivideAndConquer(records, 'A', 0, records.length - 1);
        int damageB = netDamageDivideAndConquer(records, 'B', 0, records.length - 1);
        return damageA > damageB ? 'A' : 'B';
    }

    private static int netDamageIterative(int[] records, char trainer) {
        int m = trainer == 'A' ? 1 : -1;
        int total = 0;
        for (int record : records) {
            if (record * m > 0) total += record * m;
        }
        return total;
    }

    private static int netDamageDivideAndConquer(int[] records, char trainer, int start, int end) {
        if (start == end) { // base case
            int record = records[start];
            int m = trainer == 'A' ? 1 : -1;
            return Math.max(record * m, 0);
        } else { // recursive case
            int mid = (start + end) / 2;
            // conquer part 1
            int left = netDamageDivideAndConquer(records, trainer, start, mid);
            // conquer part 2
            int right = netDamageDivideAndConquer(records, trainer, mid + 1, end);
            // combine results
            return left + right;
        }
    }
}
