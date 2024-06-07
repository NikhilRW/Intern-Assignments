import java.util.TreeMap;
import java.util.Map;

public class TreeMapExample {
    public static void main(String[] args) {
        // Map<Integer, String> map = new TreeMap<Integer, String>();

        // map.put(1, "One");
        // map.put(3, "Three");
        // map.put(2, "Two");
        
        // // TreeMap Automatically Sorts The Elements According To Thier Key Values In Ascending Order
        // System.out.println("Sorted TreeMap : " + map);
        
        // map.remove(1);
        // System.out.println("Map After Removal Of Key 1 " + map);
        // System.out.println("Value Of Key 3 " + map.get(3));
        Map<String, Integer> map = new TreeMap<String, Integer>();

        map.put("awo",1 );
        map.put("Ahree",3);
        map.put( "two",2);
        
        System.out.println(map.get("Two"));
        // TreeMap Automatically Sorts The Elements According To Thier Key Values In Ascending Order
        System.out.println("Sorted TreeMap : " + map);
        
        // map.remove(1);
        // System.out.println("Map After Removal Of Key 1 " + map);
        // System.out.println("Value Of Key 3 " + map.get(3));
    }
}
