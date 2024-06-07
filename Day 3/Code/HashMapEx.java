import java.util.HashMap;
import java.util.Map;

public class HashMapEx {
    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<Integer, String>();
        map.put(1, "One");
        map.put(2, "Two");
        map.put(3, "Three");

        System.out.println("Map : " + map);
        
        map.remove(2);
        System.out.println("Map After Removal Of Key 2 " + map);
        System.out.println("Value Of Key 3 " + map.get(3));
    }
}
