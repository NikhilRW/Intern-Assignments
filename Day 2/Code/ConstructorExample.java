class Rectangle{
    int length,breadth;
    Rectangle(int length,int breadth){
        this.length=length;
        this.breadth=breadth;
    }
    int getLenght(){
        return length;
    }
    int getBreadth(){
        return breadth;
    }
    int getArea(){
        return length*breadth;
    }
}
public class ConstructorExample {
    public static void main(String[] args) {
        Rectangle r =new Rectangle(3, 4);
        System.out.println("Area : "+r.getArea());
        System.out.println("Length : "+r.getLenght());
        System.out.println("Breadth : "+r.getBreadth());
    }
}
