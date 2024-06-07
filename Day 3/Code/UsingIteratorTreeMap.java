import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

public class UsingIteratorTreeMap {
    public static void main(String[] args) {
        Map<Integer, String> map = new TreeMap<Integer, String>();
        map.put(3, "Three");
        map.put(1, "One");
        map.put(2, "Two");
        map.put(4, "Four");
        map.put(5, "Five");
        Iterator<Entry<Integer,String>> iterator = map.entrySet().iterator();
        System.out.println("List Before All Operations  : " + map);
        while (iterator.hasNext()) {
            Map.Entry<Integer,String> pair = iterator.next();
            System.out.println(pair.getKey() + " = " + pair.getValue());
            if (pair.getKey()==3) {
                iterator.remove();
            }
        }
        System.out.println("List After All Operations  : " + map);
    }
}
