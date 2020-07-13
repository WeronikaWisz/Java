import java.util.Scanner;
import java.util.Locale;

public class SimpleIo {
    public static void main(String[] args) {
        System.out.print("...");
        System.out.println("...");
        System.out.printf("String %s int %d double %f");

        Scanner scan = new Scanner(System.in);
        String s = scan.next();
        int i = scan.nextInt();
        double d = scan.nextDouble();
        System.out.printf("Wczytano %s , %d, %f",s,i,d);
    }
}
