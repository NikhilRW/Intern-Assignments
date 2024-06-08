import java.util.List;
import java.util.ArrayList;

public class LowerBounded {
    public static void addNumbers(List<? super Integer> list) {
        list.add(10);
        list.add(20);
    }
    public static void main(String[] args) {
        List<Integer> integerList = new ArrayList<Integer>();
        List<Object> objectList = new ArrayList<Object>();
        List<Byte> byteList = new ArrayList<Byte>();
        List<Double> doubleList = new ArrayList<Double>();

        addNumbers(integerList);
        addNumbers(objectList);
       // addNumbers(doubleList); // It Is Not Allowed Because It is Lower Bounded

       System.out.println(integerList);
       System.out.println(objectList);
    }
}

