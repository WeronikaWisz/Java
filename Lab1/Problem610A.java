import java.util.Scanner;

public class Problem610A {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int[] tab = new int[4];
        int counter;
        counter = 0;

        for(int i=1; i<n/3; i++){
            tab[0] = i;
            tab[1] = i;
            tab[2] = (n - 2*i)/2;
            tab[3] = (n - 2*i)/2;
            if(tab[0] + tab[1] + tab[2] + tab[3] == n && tab[0] != tab[2]){
                counter = counter + 1;
            }
        }

        System.out.println("Number of possible ways: " + counter);

    }
}
