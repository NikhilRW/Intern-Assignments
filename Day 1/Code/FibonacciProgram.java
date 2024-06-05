import java.util.Scanner;

public class FibonacciProgram {
    public static void main(String[] args) {
        int a=0,b=1,sum=0;
        System.out.println("Enter Number Of Fibonnaci Serires : ");
        Scanner s =new Scanner(System.in);
        int no = s.nextInt();
        System.out.print(a+" "+b+" ");
        for (int i = 0; i < no-2; i++) {
            sum =a+b;
            System.out.print(sum+" ");
            a=b;
            b=sum;
        }
    }
}
