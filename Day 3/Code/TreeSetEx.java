import java.util.TreeSet;

public class TreeSetEx {
    public static void main(String[] args) {
        TreeSet<String> treeSet = new TreeSet<>();
        treeSet.add("Banana");
        treeSet.add("Apple");
        treeSet.add("Cherry");
        treeSet.add("Mango");
        System.out.println(treeSet);

        treeSet.remove("Banana");
        System.out.println(treeSet);

        System.out.println("Contains Banana : " +treeSet.contains("Banana"));

    }
}
