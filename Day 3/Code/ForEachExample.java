import java.util.ArrayList;
import java.util.List;

public class ForEachExample {
    public static void main(String[] args) {
        List<String> fruits = new ArrayList<>();
        fruits.add("Banana");
        fruits.add("Apple");
        fruits.add("Mango");
        fruits.add("Guava");
        fruits.add("Grapes");

        for (String fruit : fruits) {
            System.out.println(fruit);
        }

    }
}
