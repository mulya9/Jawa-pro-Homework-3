import db.DBView;
import model.Person;

import java.util.Scanner;

public class Menu {

    DBView dbView = new DBView();

    private Scanner scanner = new Scanner(System.in);


    public void run() {
       printMenu();
       readUserInput();
    }

    private void readUserInput() {

        while (true) {
            int typed = scanner.nextInt();
            if (typed == 1) {
                dbView.getAllPersons();
            }
            if (typed == 2) {
                dbView.addPerson(writeNewPerson());
                System.out.println("User has been added");
            }
            if (typed == 3) {
                System.out.println("Enter age");
                int userAge = scanner.nextInt();
                dbView.getUsersByAge(userAge);
            }
            if (typed == 4) {
                dbView.getAllPersons();
                System.out.println("Enter ID user for remove ");
                int id = scanner.nextInt();
                dbView.removeUserByID(id);
            }
            if (typed == 5) {
                dbView.getAllPersons();
                System.out.println("Enter the user id you want to update");
                int id = scanner.nextInt();
                dbView.updateUserData(id);
            }
            if (typed == 6) {
                System.out.println("Enter ID");
                int id = scanner.nextInt();
                dbView.getUserByID(id);
            }
            if (typed == 7) {
                System.out.println("Enter firstName");
                String firstName = scanner.next();
                dbView.getUserByFirstName(firstName);
            }
            if (typed==8) {
                System.out.println(" Finish ");
                break;
            }
        }


    }
    public Person writeNewPerson() {

        System.out.println("name");
        String name = new Scanner(System.in).nextLine();
        System.out.println("last_name");
        String last_name = new Scanner(System.in).nextLine();
        System.out.println("age");
        int age = scanner.nextInt();
        return new Person(name, last_name, age);
    }

    public void printMenu() {
        System.out.println("1 - All users");
        System.out.println("2 - Add new user");
        System.out.println("3 - All users who are older than the specified age.");
        System.out.println("4 - remove user");
        System.out.println("5 - Update data about the user");
        System.out.println("6 - Get user by ID");
        System.out.println("7 - Get user by firstName");
        System.out.println("8 - finish");
    }

}
