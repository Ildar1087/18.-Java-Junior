package HW2;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLOutput;

public class Program {
    public static void main(String[] args) {

        Animal[] animals = {
                new Dog("Stufy",3, "Buldog"),
                new Cat("Tom", 5, 9),
                new Dog("Rex", 9, "Taksa"),
                new Cat("Wallet", 2, 4)
        };
        System.out.println("");
        System.out.println("   ANIMALS");
        System.out.println("");
        for(Animal animal : animals){
            System.out.println(animal);
            try{
                Method makeSoundMethod = animal.getClass().getMethod("makeSound");
                makeSoundMethod.invoke(animal);
            } catch (Exception e) {
                System.out.println("Does not make a sound ");
            }
            System.out.println();
        }
    }
}
