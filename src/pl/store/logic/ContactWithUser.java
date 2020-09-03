package pl.store.logic;

import pl.store.exceptions.NoSuchAnOptionException;
import pl.store.model.product.Books;
import pl.store.model.product.ComputerGames;
import pl.store.model.user.Address;
import pl.store.model.user.Client;
import pl.store.model.user.Employee;
import pl.store.model.user.Position;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ContactWithUser {

    Scanner scanner = new Scanner(System.in);
    public int userId = 0;
    public int productId = 0;


    public Employee createNewEmployee(){
        printer("Username: ");
        String username = scanner.nextLine();
        printer("Password: ");
        String password = scanner.nextLine();
        printer("First name: ");
        String firstName = scanner.nextLine();
        printer("Last name: ");
        String lastName = scanner.nextLine();
        printer("Salary: ");
        int salary = getInt();
        Position position = positionChoice();
        return new Employee(userId, username, password, firstName, lastName, salary, position);
    }

    private Position positionChoice() {
        Position positionChoice = null;
        boolean isOk = false;
        do {
            printer("Choose an option: ");
            for (Position l : Position.values()) {
                printer(l.toString());
            }
            int choice = getInt();
            try{
                positionChoice = Position.optionFromInt(choice);
                isOk = true;
            } catch(NoSuchAnOptionException e){
                printer(e.getMessage());
            }
        } while(!isOk);
        return positionChoice;
    }

    public Client createNewClient(){
        printer("Username: ");
        String username = scanner.nextLine();
        printer("Password: ");
        String password = scanner.nextLine();
        printer("First name: ");
        String firstName = scanner.nextLine();
        printer("Last name: ");
        String lastName = scanner.nextLine();
        Address address = createNewAddress();
        printer("Age: ");
        int age = getInt();
        return new Client(userId, username, password, firstName, lastName, address, age);
    }

    private Address createNewAddress(){
        printer("Country: ");
        String country = scanner.nextLine();
        printer("Postal code: ");
        String postalCode = scanner.nextLine();
        printer("Street name: ");
        String streetName = scanner.nextLine();
        printer("House number: ");
        int houseNumber = getInt();
        printer("Flat number: ");
        int flatNumber = getInt();
        return new Address(country, postalCode, streetName, houseNumber, flatNumber);
    }

    public int getInt(){
        int a = 0;
        boolean isOk = false;
        do {
            try {
                a = scanner.nextInt();
                isOk = true;
            } catch (InputMismatchException e) {
                printer("Insert a number!");
            } finally {
                scanner.nextLine();
            }
        } while (!isOk);
        return a;
    }

    public double getDouble(){
        double a = 0;
        boolean isOk = false;
        do {
            try {
                a = scanner.nextDouble();
                isOk = true;
            } catch (InputMismatchException e) {
                printer("Insert a number!");
            } finally {
                scanner.nextLine();
            }
        } while (!isOk);
        return a;
    }

    public String getString(){
        return scanner.nextLine();
    }

    public void closeScanner(){
        scanner.close();
    }

    public ComputerGames createComputerGames(){
        printer("Name: ");
        String name = scanner.nextLine();
        printer("Price without tax: ");
        double priceWithoutTax = getDouble();
        printer("Minimum user age: ");
        int minimumAge = getInt();
        printer("Platform: ");
        String platform = scanner.nextLine();
        return new ComputerGames(productId, name, priceWithoutTax, minimumAge, platform);
    }

    public Books createBooks(){
        printer("Name: ");
        String name = scanner.nextLine();
        printer("Price without tax: ");
        double priceWithoutTax = getDouble();
        printer("Number of pages: ");
        int pages = getInt();
        printer("Type of the book: ");
        String type = scanner.nextLine();
        return new Books(productId, name, priceWithoutTax, pages, type);
    }

    public void printer(String text){
        System.out.println(text);
    }

}
