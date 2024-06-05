import java.util.Scanner;

public class ArmstrongNumber {
    public static void main(String[] args) {
        int sum = 0,orgvalue,length,copyValue;
        System.out.print("Enter An Number To Check Armstrong");
        Scanner s =new Scanner(System.in);
        orgvalue =s.nextInt();
        copyValue =orgvalue;
        length = String.valueOf(orgvalue).length();
        for (int i = 0; i <length; i++) {
            int rem = copyValue%10;
            sum+= Math.pow(rem, length);
            copyValue/=10;
        }
        System.out.println(orgvalue==sum?"Armstrong Number":"Not A Armstrong");
    }
}
