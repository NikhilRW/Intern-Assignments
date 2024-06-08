
class GenericClass<T>{
    private T data;
    GenericClass(T data){
        this.data=data;
    }
    public T getData() {
        return data;
    }
}
public class GenreicClass {
    public static void main(String[] args) {
        GenericClass<Integer> genericClassObject = new GenericClass<Integer>(10);
        System.out.println("Created Object using Generic Class");
        System.out.println("Data Of The Generic Class : "+genericClassObject.getData());
    }
}
