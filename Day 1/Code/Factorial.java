import java.util.Scanner;

public class Factorial {
    public static void main(String[] args) {
        System.out.println("Enter Number To Find Its Factorial : ");
        Scanner s =new Scanner(System.in);
        int no = s.nextInt();
        int fact=1;
        for (int i = no; i>0; i--) {
            fact*=i;
        }
        System.out.println(fact);
    }
}
