package HW2;

public class Dog extends Animal {

    private String breed;

    public Dog(String name, int age, String breed) {
        super(name, age);
        this.breed = breed;
    }


    @Override
    public void makeSound() {
        System.out.println("Gaf-Gaf!");
    }

    @Override
    public String toString() {
        return "Dog{" +
                "breed='" + breed + '\'' +
                '}';
    }
}
