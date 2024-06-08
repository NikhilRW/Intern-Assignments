public class UpperBounded {
    public static void main(String[] args) {
     UpperBound<Number> upperBound = new UpperBound<Number>(12l) ;  
     System.out.println("Data Upper Bounded By Number Class : "+upperBound.data);
    }
}
class UpperBound <T extends Number>{
    T data;
    UpperBound(T data){
        this.data = data;
    }
    public T getData() {
        return data;
    }
}
