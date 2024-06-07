public class TryCatchExample {
    public static void main(String[] args) {

        try{
            int a[] = {1,2,3};
            System.out.println(a[5]); // ---> This Will Throw An Array Out Of Bounds Exception That's Why We used Catch To Catch The Error In The First Place
        }
        catch(ArrayIndexOutOfBoundsException  e){
            System.out.println("Array Out Of Bounds : "+e.getMessage());
        }
    }
}
