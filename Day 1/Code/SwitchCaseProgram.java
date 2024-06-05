import java.util.Scanner;

public class SwitchCaseProgram {
    public static void main(String[] args) {
        System.out.print("Enter Number Of The Day In A Week : ");
        Scanner s =new Scanner(System.in);
        int day =s.nextInt();
        switch (day) {
            case 1:
                System.out.println("Sunday");
                break;
            case 2:
                System.out.println("Monday");
                break;
            case 3:
                System.out.println("Tuesday");
                break;
        
            case 4:
                System.out.println("Wendesday");
                break;
        
            case 5:
                System.out.println("Thursday");
                break;
        
            case 6:
                System.out.println("Friday");
                break;
        
            case 7:
                System.out.println("Saturday");
                break;

            default:
                    System.out.println("Invalid Input");
                break;
        }
    }
}
