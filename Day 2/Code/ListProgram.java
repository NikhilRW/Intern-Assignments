import java.util.ArrayList;
import java.util.List;

public class ListProgram {
    public static void main(String[] args) {
        List<String> list = new  ArrayList<>();
        list.add("Mango");
        list.add("Stawberry");
        list.add("Banana");

        System.out.println("Element At Index 1"+list.get(0));
        list.remove(0);
        System.out.println("Element At Index 1"+list.get(0));


    }
}
