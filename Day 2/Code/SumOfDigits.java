public class SumOfDigits {
    public static void main(String[] args) {
        int num = 12345,sum=0;
        while (num!=0) {
            int rem = num %10;
            sum+=rem;
            num/=10;
        }
        System.out.println("Sum Of Digits : "+sum);
    }
}