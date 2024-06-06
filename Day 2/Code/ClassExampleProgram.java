class Rectangle{
    int length,breadth;
    void setData(int length,int breadth){
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
public class ClassExampleProgram {
    public static void main(String[] args) {
        Rectangle r =new Rectangle();
        r.setData(3, 4);
        System.out.println("Area : "+r.getArea());
        System.out.println("Length : "+r.getLenght());
        System.out.println("Breadth : "+r.getBreadth());
    }
    
}