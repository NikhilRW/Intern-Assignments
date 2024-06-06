import java.util.LinkedList;
import java.util.List;

public class LinkedListPR {
    public static void main(String[] args) {
        List list = new LinkedList<>();
        list.add(1);
        list.add(5);
        list.add(4);
        list.add(3);
        list.add("3");
        for (Object object : list) {
            System.out.println(object);
        }
        list.remove(4);
        list.remove(3);
        for (Object object : list) {
            System.out.println(object);
        }
        }
}
