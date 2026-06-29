import java.util.Scanner;

public class P1_Sol {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        session1();
        System.out.println();
        session2();
    }

    private static void session1() {
        int n;
        System.out.print("N: ");
        do {
            n = sc.nextInt();
            if (n <= 0) System.out.println("N must be greater than 0");
        } while (n <= 0);
        long formula;
        try {
            formula = hexFormula(n);
            System.out.println("Formula: " + formula);
        } catch (StackOverflowError e) {
            formula = 0;
            System.out.println("Formula: StackOverflow");
        }

        long iterative;
        try {
            iterative = hexIter(n);
            System.out.println("Iterative: " + iterative);
        } catch (StackOverflowError e) {
            iterative = 0;
            System.out.println("Iterative: StackOverflow");
        }

        long recursive;
        try {
            recursive = hexRecursive(n);
            System.out.println("Recursive: " + recursive);
        } catch (StackOverflowError e) {
            recursive = 0;
            System.out.println("Recursive: StackOverflow");
        }

        if (formula == iterative && formula == recursive) {
            System.out.println("They are the same.");
        }
    }

    private static long hexFormula(long n) {
        return 2L * n * n - n;
    }

    private static long hexIter(long n) {
        long res = 0;
        for (int i=0; i<n; i++)
            res=res+4*i+1;
        return res;
    }

    private static long hexRecursive(long n) {
        if (n<=1)
            return n;
        else return hexRecursive(n-1)+4*(n-1)+1;
    }

    private static void session2() {
        System.out.println("Choose a unit:\n1 - Nanos \n2 - Millis");
        int measure;
        do {
            measure = sc.nextInt();
            if (measure != 1 && measure != 2) System.out.println("Invalid option.");
        } while (measure != 1 && measure != 2);

        int[] numbers = {10, 100, 500, 1000, 5000, 8000, 10000, 11000, 15000, 50000};

        System.out.println("     N    |     Value     |     Formula     |     Iterative     |     Recursive     |\n" +
                           " ---------+---------------+-----------------+-------------------+-------------------+");

        String timeFormula, timeIterative, timeRecursive;
        long formula, iterative, recursive;
        for (int n : numbers) {
            long timeBefore = measure == 1 ? System.nanoTime() : System.currentTimeMillis();
            try {
                formula = hexFormula(n);
                long timeFormulaNum = (measure == 1 ? System.nanoTime() : System.currentTimeMillis()) - timeBefore;
                timeFormula = String.format("%13s", String.format("%,d", timeFormulaNum));
                timeFormula += measure == 1 ? "ns" : "ms";
            } catch (StackOverflowError e) {
                formula = 0;
                timeFormula = String.format("%15s", "StackOverflow");
            }

            timeBefore = measure == 1 ? System.nanoTime() : System.currentTimeMillis();
            try {
                iterative = hexIter(n);
                long timeIterativeNum = (measure == 1 ? System.nanoTime() : System.currentTimeMillis()) - timeBefore;
                timeIterative = String.format("%15s", String.format("%,d", timeIterativeNum));
                timeIterative += measure == 1 ? "ns" : "ms";
            } catch (StackOverflowError e) {
                iterative = 0;
                timeIterative = String.format("%17s", "StackOverflow");
            }

            timeBefore = measure == 1 ? System.nanoTime() : System.currentTimeMillis();
            try {
                recursive = hexRecursive(n);
                long timeRecursiveNum = (measure == 1 ? System.nanoTime() : System.currentTimeMillis()) - timeBefore;
                timeRecursive = String.format("%15s", String.format("%,d", timeRecursiveNum));
                timeRecursive += measure == 1 ? "ns" : "ms";
            } catch (StackOverflowError e) {
                recursive = 0;
                timeRecursive = String.format("%17s", "StackOverflow");
            }

            System.out.printf("%8d  | %13d | %s | %s | %s |\n", n, formula, timeFormula, timeIterative, timeRecursive);
        }
    }
}
