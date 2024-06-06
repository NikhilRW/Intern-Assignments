interface Animal {
    public void makeSound();
}

interface Pet {
    void sit();
}

class Dog implements Animal, Pet {
    public void makeSound() {
        System.out.println("DOg Making Sound");
    }

    public void sit() {
        System.out.println("Dog Is Sitting");
    }
}

public class InterfacePR {
    public static void main(String[] args) {
        Dog dog =new Dog();
        dog.makeSound();
        dog.sit();
    }
}
