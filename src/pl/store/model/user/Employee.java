package pl.store.model.user;

public class Employee extends User{

    public static final String TYPE = "EMPLOYEE";
    private int salary;
    private Position position;

    public Employee(String username, String password, String firstName, String lastName, int salary, Position position) {
        super(username, password, firstName, lastName);
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
        return TYPE + " ; " + getUsername() + " ; " + getPassword()  + " ; " + getFirstName() +
                " ; " + getLastName()  + " ; " + salary  + " ; " + position.name();
    }
}
