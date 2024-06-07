import java.util.Scanner;
public class PrimeNumber {
    public static void main(String[] args) {
        System.out.print("Enter A Number To Check Its Prime or Not  : ");
        Scanner s = new Scanner(System.in);
        int a = s.nextInt();
        int count = 0;
        for (int i = 1; i <=a; i++) {
            count = a%i==0?++count:count;
        }
        System.out.println(count==2?"Prime Number ":"Not A Prime Number");
    }
}
