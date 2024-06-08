public class UpperBoundMethodExample {
    public static <T extends Comparable<T>> T findMax(T[] array){
        T max  = array[0];
        for (T element : array) {
            if(element.compareTo(max) > 0 ){
                max = element;
            }
        }
        return max;
    }
    public static void main(String[] args) {
        Integer[] integersArray = { 1,2,4,52,1};
        System.out.println("Max Value Of Integer Array : "+ findMax(integersArray));
        
        Double[] doubleArray = { 1.2,2.2,4.2,5.2,1.21};
        System.out.println("Max Value Of Double Array : "+ findMax(doubleArray));

        Float[] floatArray = { 1.2f,2.2f,433.22f,5.2f,1.21f};
        System.out.println("Max Value Of Float Array : "+ findMax(floatArray));
        
    }
}

