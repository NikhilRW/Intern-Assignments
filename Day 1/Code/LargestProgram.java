public class LargestProgram {
    public static void main(String[] args) {
        int a=10,b=30,c=40,largest;
        if(a>=b){
            if(a>=c){
                largest=a;
            }
            else{
                largest=b;
            }
        }
        else
        {
            if(b>=c){
                largest=b;
            }
            else{
                largest = c;
            }

        }
        System.out.println(largest);
    }
    
}
