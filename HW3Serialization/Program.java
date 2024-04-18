package HW3Serialization;


import java.io.*;

public class Program {
    /**
     * Разработайте класс Student с полями String name, int age, transient double GPA (средний балл).
     * Обеспечьте поддержку сериализации для этого класса.
     * Создайте объект класса Student и инициализируйте его данными.
     * Сериализуйте этот объект в файл.
     * Десериализуйте объект обратно в программу из файла.
     * Выведите все поля объекта, включая GPA, и обсудите,
     * почему значение GPA не было сохранено/восстановлено.
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Student st1 = new Student("Александр", 17,5.0);
        Student st2 = new Student("Станислав", 18, 4.9);

        try(FileOutputStream fileOutputStream = new FileOutputStream("3_HW_Serialization.bin");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)){
            objectOutputStream.writeObject(st1);
            System.out.println("Объект st1 сериализован.");
            objectOutputStream.writeObject(st2);
            System.out.println("Объект st2 сериализован.\n");
        }

        try(FileInputStream fileInputStream = new FileInputStream("3_HW_Serialization.bin");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)){
            st1 = (Student) objectInputStream.readObject();
            System.out.println("Объект st1 десериализован.");
            st2 = (Student) objectInputStream.readObject();
            System.out.println("Объект st2 десериализован.\n");
        }

        System.out.println("Объект st1: ");
        System.out.println("Имя: " + st1.getName());
        System.out.println("Возраст: " + st1.getAge());
        System.out.println("Средний балл: " + st1.getGPA());
        System.out.println();

        System.out.println("Объект st2: ");
        System.out.println("Имя: " + st2.getName());
        System.out.println("Возраст: " + st2.getAge());
        System.out.println("Средний балл: " + st2.getGPA());
        System.out.println();

        System.out.println("Данные о среднем балле не были сохранены в файл и не восстановлены, " +
                "так как значение переменной transient double GPA,\nоно не было сериализовано, " +
                "потому что, является transient-свойством и не входит в обычное определение класса. \n" +
                "Именно поэтому, он является временным и у него значение null");






    }
}
