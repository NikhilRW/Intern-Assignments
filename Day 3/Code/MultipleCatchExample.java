public class MultipleCatchExample {
    public static void main(String[] args) {
        try{
            int a[] = {1,2,3};
            System.out.println(a[5]); 
            int b = 1/0;
        }
        catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Array Out Of Bounds : "+e.getMessage());
        }
        catch(ArithmeticException  e){
            e.printStackTrace();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
