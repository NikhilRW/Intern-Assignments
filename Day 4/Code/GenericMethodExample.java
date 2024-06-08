public class GenericMethodExample {
    public static <T> void printArray(T[] array){
        for (T element : array) {
            System.out.print(element + " ");
        }
        System.out.println();
    }
    public static void main(String[] args) {

        Integer[] integerArray = {1,2,4,5,6,76,7,4};
        String[] stringArray = {"1","2","3","4","5"};
        Double[] doubleArray = {1.1,2.2,4.3,5.5,6.6,7.6,7.7,4.7};

        printArray(integerArray);
        printArray(stringArray);
        printArray(doubleArray);
    }
}
