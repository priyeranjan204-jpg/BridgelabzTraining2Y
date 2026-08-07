import java.util.Scanner;

public class PowerCalculation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double b = sc.nextDouble();
        double e = sc.nextDouble();
        double ans = Math.pow(b, e);
        System.out.println(ans);
    }
}