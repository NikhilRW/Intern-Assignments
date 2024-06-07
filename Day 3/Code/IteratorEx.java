import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

public class IteratorEx {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("Banana");
        list.add("Apple");
        list.add("Mango");
        list.add("Guava");
        list.add("Grapes");

        Iterator<String> iterator = list.iterator();
        System.out.println("List Before All Operations  : " + list);
        while (iterator.hasNext()) {
            String fruit = iterator.next();
            System.out.println(fruit);

            if (fruit.equals("Apple")) {
                iterator.remove();
            }
        }
        System.out.println("List After All Operations  : " + list);

    }
}
