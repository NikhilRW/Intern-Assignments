public class Circle {
    float radius;
    void setData(float radius){
        this.radius=radius;
    }
    float getArea(){
            return (float)Math.PI *(radius*radius);
    }
    public static void main(String[] args) {
        Circle c = new Circle();
        c.setData(3);
        System.out.println("Area  : "+c.getArea());
    }
}
