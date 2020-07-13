import java.util.Scanner;

public class Problem115A {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int[] tab = new int[n];
        int counter = 1;

        for(int i=0; i<n; i++) {
            tab[i] = scan.nextInt();
        }

        for(int j = 0; j<n; j++) {
            for(int k = 1; k<n; k++) {
                if(tab[j] == k && j != k - 1) {
                    counter+=1;
                }
            }
        }

        System.out.println(counter);
    }
}

