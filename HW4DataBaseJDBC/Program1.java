package HW4DataBaseJDBC;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

public class Program1 {
    private final static Random random = new Random();
    /**Задача 1

     * Используя SQL, создайте таблицу students с полями id (ключ), name, и age.
     * Реализация подключения к базе данных через JDBC:
     * Напишите Java-код для подключения к базе данных (например, MySQL или PostgreSQL).
     * Реализуйте вставку, чтение, обновление и удаление данных в таблице Students
     * с использованием провайдера JDBC.  */

    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://students.db:3306/";
        String user = "root";
        String password = "password";
        // Подключаемся к базе данных через блок try
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            // Создаем базу данных
            createDatabase(connection);
            System.out.println("Database created successfully");
            // Используем базу данных
            useDatabase(connection);
            System.out.println("Use database successfully!");
            // Создаем таблицу
            createTable(connection);
            System.out.println("Created database successfully.");
            // Добавляем данные в таблицу
            int count = random.nextInt(5, 11);
            for (int i = 0; i < count; i++)
                insertData(connection, Student.create());
            System.out.println("Insert data successfully");
            // Читаем данные из таблицы
            Collection<Student> students = readData(connection);
            for (var student : students)
                System.out.println(student);
            System.out.println("Read data successfully");
            // Обновляем базу данных
            for (var student : students) {
                student.updateName();
                student.updateAge();
                updateData(connection, student);
            }
            System.out.println("Update data successfully");
            // Читаем данные
            students = readData(connection);
            for (var student : students)
                System.out.println(student);
            System.out.println("Read data successfully");
            // Удаление данных
            for (var student : students)
                deleteData(connection, student.getId());
            System.out.println("Delete data successfully");
            // закрываем соединение
            connection.close();
            System.out.println("Database connection close successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

// region Вспомогательные методы


        private static void createDatabase (Connection connection) throws SQLException {
            String createDatabaseSQL = "CREATE DATABASE IF NOT EXISTS studetnsDB; ";
            try (PreparedStatement statement = connection.prepareStatement(createDatabaseSQL)) {
                statement.execute();
            }
        }
        private static void useDatabase(Connection connection) throws SQLException {
            String useDatebaseSQL = "USE studentsDB;";
            try(PreparedStatement statement = connection.prepareStatement(useDatebaseSQL)){
                statement.execute();
            }
        }

        private static void createTable(Connection connection) throws SQLException {
            String createTableSQL = "CREATE TABLE IF NOT EXISTS students (id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(255), age INT);";
            try(PreparedStatement statement = connection.prepareStatement(createTableSQL)){
                statement.execute();
            }
        }

        /** Добавляем данные в таблицу Students
         * @param connection Соединение с БД
         * @param student Студент
         * @throws SQLException Исключение при выполнении запроса
         */
        private static  void insertData(Connection connection, Student student) throws SQLException {
            String insertDataSQL = "INSERT INTO students(name, age) VALUES(?, ?);";
            try(PreparedStatement statement = connection.prepareStatement(insertDataSQL)){
                statement.setString(1, student.getName());
                statement.setInt(2, student.getAge());
                statement.executeUpdate();
            }
        }

        /**
         * Метод чтения данных из таблицы students
         */
        private static Collection<Student> readData(Connection connection) throws SQLException {
            ArrayList<Student> studentsList = new ArrayList<>();
            String readDataSQL = "SELECT * FROM students;";
            try (PreparedStatement statement = connection.prepareStatement(readDataSQL)) {
                ResultSet resultSet = statement.executeQuery();
                while(resultSet.next()){
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    int age = resultSet.getInt("age");
                    studentsList.add(new Student(id, name, age));
                }
                return studentsList;
            }
        }

        /**
         * Метод для обновления данных в таблице students по идентификатору
         */
        public static  void updateData(Connection connection, Student student) throws SQLException {
            String updateDataSQL = "UPDATE students SET name=?, age=?, WHERE id=?;";
            try(PreparedStatement statement = connection.prepareStatement(updateDataSQL)){
                statement.setString(1, student.getName());
                statement.setInt(2, student.getAge());
                statement.setInt(3, student.getId());
                statement.executeUpdate();
            }
        }

        /**
         * Метод для удаления записей из таблицы Students по идентификатору
         */
        private static void deleteData(Connection connection, int id) throws SQLException {
            String deleteDataSQL = "DELETE FROM students WHERE id=?;";
            try(PreparedStatement statement = connection.prepareStatement(deleteDataSQL)){
                statement.setLong(1, id);
                statement.executeUpdate();
            }

        }

// endregion



}
