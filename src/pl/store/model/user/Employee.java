package pl.store.model.user;

public class Employee extends User{

    public static final String USER_TYPE = "EMPLOYEE";
    private int salary;
    private Position position;

    public Employee(int id, String username, String password, String firstName, String lastName, int salary, Position position) {
        super(id, username, password, firstName, lastName);
        this.salary = salary;
        this.position = position;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return super.toString() + " - " + position.name() + " - " + salary + " euro";
    }

    @Override
    public String toCsv() {
        return USER_TYPE + " ; " + getId() + " ; " + getUsername() + " ; " + getPassword()  + " ; " + getFirstName() +
                " ; " + getLastName()  + " ; " + salary  + " ; " + position;
    }
}
