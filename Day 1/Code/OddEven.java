import java.util.Scanner;

public class OddEven {
    public static void main(String[] args) {
        System.out.println("Enter A Number to Check Even Or Odd : ");
        Scanner s =new Scanner(System.in);
        int a = s.nextInt();
        // Using If-Else Condition
        if(a%2==0){
            System.out.println("Even Number");
        }
        else{
            System.out.println("Odd Number");
        }
        // Using Ternary Operator
        System.out.println(a%2==0?"Even Number":"Odd Number");
    }
}
