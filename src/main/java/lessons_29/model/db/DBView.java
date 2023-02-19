package lessons_29.model.db;

import lessons_29.model.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;


public class DBView {
    private static final String URL = "jdbc:mysql://localhost:3306/jdbc_exampl";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "denya090883";

    public void getAllPersons() {
        Connection connection = getConnection();
        ArrayList<Person> people = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM persons");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                people.add(new Person(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getInt(4)));
            }
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        people.forEach(person -> System.out.println(person.toString()));
    }

    public void addPerson(Person person) {
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO persons(first_name, last_name,age) VALUES (?, ?, ?) "
            );
            preparedStatement.setString(1, person.getFirst_name());
            preparedStatement.setString(2, person.getLast_name());
            preparedStatement.setInt(3, person.getAge());

            preparedStatement.execute();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void getUsersByAge(int age) {
        Connection connection = getConnection();
        ArrayList<Person> people = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM persons where age");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getInt(4) >= age) {
                    people.add(new Person(resultSet.getInt(1), resultSet.getString(2),
                            resultSet.getString(3), resultSet.getInt(4)));
                }
            }
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        people.forEach(person -> System.out.println(person.toString()));
    }

    public void getUserByID(int id) {
        Connection connection = getConnection();
        Person person = new Person();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM persons where id");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getInt(1) == id) {
                    person = new Person(resultSet.getInt(1), resultSet.getString(2),
                            resultSet.getString(3), resultSet.getInt(4));
                }
            }
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println(person);
    }

    public void getUserByFirstName(String name) {
        Connection connection = getConnection();
        Person person = new Person();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM persons");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getString(2).equals(name)) {
                    person = new Person(resultSet.getInt(1), resultSet.getString(2),
                            resultSet.getString(3), resultSet.getInt(4));
                }
            }
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println(person);
    }


    public void removeUserByID(int id) {
        Connection connection = getConnection();
        try {

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM persons WHERE id = " + id + "");
            preparedStatement.executeUpdate();

            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        getAllPersons();

    }

    public void updateUserData(Integer id) {
        getUserByID(id);
        Scanner scanner = new Scanner(System.in);
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE persons SET first_name = ?, last_name = ?, age = ? WHERE id = ?");

            System.out.println("Enter new name user");
            preparedStatement.setString(1, scanner.next());
            System.out.println("Enter new lastname user");
            preparedStatement.setString(2, scanner.next());
            System.out.println("Enter new age user ");
            preparedStatement.setInt(3, scanner.nextInt());
            preparedStatement.setInt(4, id);
            preparedStatement.execute();
            getAllPersons();

            connection.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

}