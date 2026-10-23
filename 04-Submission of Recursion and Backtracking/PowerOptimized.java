import java.util.Scanner;

public class PowerOptimized {
    static long power(int x, int n) {
        if (n == 0) {
            return 1;
        }

        long half = power(x, n / 2);

        if (n % 2 == 0) {
            return half * half;
        }
        return x * half * half;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        int n = sc.nextInt();

        if (n < 0) {
            System.out.println("Exponent must be non-negative");
        } else {
            System.out.println(power(x, n));
        }
        sc.close();
    }
}
