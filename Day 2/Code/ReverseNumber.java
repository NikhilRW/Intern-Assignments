import java.util.Scanner;

public class ReverseNumber {
    public static void main(String[] args) {
        System.out.print("Enter A Number : ");
        Scanner s  =new Scanner(System.in);
        int num=s.nextInt();
        int rev =0;
        while (num!=0) {
            rev=(rev*10)+(num%10);
            num/=10;
        }
        System.out.println("Reverse Number : "+rev);
        s.close();
    }
}
