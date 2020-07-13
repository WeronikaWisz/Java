import java.util.Scanner;

public class Fibo {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        if (n<1 || n>45) {
            return;
        }
        int[] tab = new int[n];
        tab[0]=1;
        tab[1]=2;
                for(int i=2;i<n;i++){
                    tab[i]=tab[i-1]+tab[i-2];
                }
                for(int j: tab) {
                    System.out.println(j);
                }
    }
}
