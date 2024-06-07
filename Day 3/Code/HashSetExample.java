import java.util.HashSet;
import java.util.Set;

public class HashSetExample {
    public static void main(String[] args) {
        Set<String> hs = new HashSet<>();
        hs.add("One");
        hs.add("Two");
        hs.add("Three");
        hs.add("Four");
        hs.add("One");
        hs.add("one");
        // It Will Be Not Added In The Hash Set
        System.out.println(hs);

        hs.remove("Four");
        // Removed Four From Hash Set Using 'remove' Method
        System.out.println(hs);

        System.out.println("Set Contains Three : " + hs.contains("Three"));
        hs.clear();

    }
}