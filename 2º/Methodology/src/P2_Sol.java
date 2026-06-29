import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class P2_Sol {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        session1();
        System.out.println();
        session2();
    }

    private static void session1() {
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

    private static void session2() {
        String firstFile = "combate_liga.txt";
        String secondFile = "combate_mundial.txt";
        
        int[] records;
        printData(firstFile);
        System.out.println();
        printData(secondFile);
        System.out.println();

        System.out.println("Generating random battle");
        int size, range;
        do {
            System.out.print("Size: ");
            size = sc.nextInt();
            System.out.print("Range (+/-): ");
            range = sc.nextInt();
        } while (size <= 0 || range <= 0);
        int[] randomRecords = new int[size];
        for (int i = 0; i < size; i++) {
            randomRecords[i] = (int) (Math.random() * (range * 2 + 1)) - range;
        }
        System.out.println();
        for (int record : randomRecords) {
            System.out.print(record + " ");
        }
        System.out.println();
        System.out.println("Winner of random battle: " + calculateWinner(randomRecords));
        System.out.println("Strongest pokemon A of random battle: " + strongestPokemonIterative(randomRecords, 'A'));
        System.out.println("Strongest pokemon B of random battle: " + strongestPokemonIterative(randomRecords, 'B'));
    }

    private static void printData(String fileName) {
        int[] records;
        System.out.println("Reading file " + fileName);
        try {
            records = readRecords(fileName);
            System.out.println("Number of records: " + records.length);
            System.out.println();

            long start, end;
            start = System.nanoTime();
            int res = netDamageIterative(records, 'A');
            end = System.nanoTime();
            System.out.println("Net damage Trainer A (Iterative):     " + res + "  (Time: " + (end - start) + " ns)");
            start = System.nanoTime();
            res = netDamageDivideAndConquer(records, 'A', 0, records.length - 1);
            end = System.nanoTime();
            System.out.println("Net damage Trainer A (Recursive DnC): " + res + "  (Time: " + (end - start) + " ns)");

            start = System.nanoTime();
            res = netDamageIterative(records, 'B');
            end = System.nanoTime();
            System.out.println("Net damage Trainer B (Iterative):     " + res + "  (Time: " + (end - start) + " ns)");
            start = System.nanoTime();
            res = netDamageDivideAndConquer(records, 'B', 0, records.length - 1);
            end = System.nanoTime();
            System.out.println("Net damage Trainer B (Recursive DnC): " + res + "  (Time: " + (end - start) + " ns)");
            System.out.println();

            char winner = calculateWinner(records);
            System.out.println("The winner is Trainer " + winner + "!");

            System.out.println("\n");
            start = System.nanoTime();
            res = strongestPokemonIterative(records, 'A');
            end = System.nanoTime();
            System.out.println("Strongest Pokemon A (Iterative): pos=" + res + ", dmg=" + records[res] + " (Time: " + (end - start) + " ns)");
            start = System.nanoTime();
            res = strongestPokemonDivideAndConquer(records, 'A', 0, records.length - 1);
            end = System.nanoTime();
            System.out.println("Strongest Pokemon A (Recursive DnC): pos=" + res + ", dmg=" + records[res] + " (Time: " + (end - start) + " ns)");
            start = System.nanoTime();
            res = strongestPokemonIterative(records, 'B');
            end = System.nanoTime();
            System.out.println("Strongest Pokemon B (Iterative): pos=" + res + ", dmg=" + records[res] + " (Time: " + (end - start) + " ns)");
            start = System.nanoTime();
            res = strongestPokemonDivideAndConquer(records, 'B', 0, records.length - 1);
            end = System.nanoTime();
            System.out.println("Strongest Pokemon B (Recursive DnC): pos=" + res + ", dmg=" + records[res] + " (Time: " + (end - start) + " ns)");
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + fileName);
        }
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

    private static int strongestPokemonIterative(int[] records, char trainer) {
        int m = trainer == 'A' ? 1 : -1;
        int max = -1;

        for (int i = 0; i < records.length; i++) {
            int record = records[i];
            if (record * m > 0 && (max == -1 || record * m > records[max] * m)) {
                max = i;
            }
        }

        return max;
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

    private static int strongestPokemonDivideAndConquer(int[] records, char trainer, int start, int end) {
        if (start > end) {
            return -1;
        }

        if (start == end) { // base case
            int record = records[start];
            int m = trainer == 'A' ? 1 : -1;
            return (record * m > 0) ? start : -1;
        } else { // recursive case
            int mid = (start + end) / 2;
            // conquer part 1
            int leftIndex = strongestPokemonDivideAndConquer(records, trainer, start, mid);
            // conquer part 2
            int rightIndex = strongestPokemonDivideAndConquer(records, trainer, mid + 1, end);
            // combine results
            int m = trainer == 'A' ? 1 : -1;

            if (leftIndex == -1) {
                return rightIndex;
            }
            if (rightIndex == -1) {
                return leftIndex;
            }

            return (records[leftIndex] * m >= records[rightIndex] * m) ? leftIndex : rightIndex;
        }
    }

    private static int[] readRecords(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        Scanner sc = new Scanner(file);
        int[] records = new int[countLines(fileName)];
        for (int i = 0; i < records.length; i++) {
            records[i] = Integer.parseInt(sc.nextLine());
        }
        return records;
    }

    private static int countLines(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        Scanner sc = new Scanner(file);
        int count = 0;
        while (sc.hasNextLine()) {
            count++;
            sc.nextLine();
        }
        return count;
    }
}
