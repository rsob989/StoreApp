package pl.store.app;

import pl.store.app.menus.AddMenu;
import pl.store.app.menus.ClientMenu;
import pl.store.app.menus.EmployeeMenu;
import pl.store.app.menus.StartMenu;
import pl.store.data.DataManager;
import pl.store.data.FileManager;
import pl.store.exceptions.NoSuchAnOptionException;
import pl.store.exceptions.ReadDataException;
import pl.store.logic.ContactWithUser;
import pl.store.logic.Operations;
import pl.store.model.ShopProducts;
import pl.store.model.ShopUsers;
import pl.store.model.user.Client;
import pl.store.model.user.Employee;
import pl.store.model.user.User;

public class AppController {


    ContactWithUser cwu = new ContactWithUser();
    DataManager dm = new DataManager(cwu);
    FileManager fm;
    ShopProducts shopProducts;
    ShopUsers shopUsers;
    Operations operations;

    public AppController() {
        fm = dm.chooseDataType();
        try {
            shopProducts = fm.loadProducts();
            shopUsers = fm.loadUsers();
            cwu.printer("Data loaded successfully");
        } catch (ReadDataException e){
            cwu.printer(e.getMessage());
            shopProducts = new ShopProducts();
            shopUsers = new ShopUsers();
            cwu.printer("New database created");
        } finally {
            operations = new Operations(shopUsers, shopProducts, cwu);
        }
    }

    public void controlLoop() {
        StartMenu startMenu = choice(StartMenu.class);
        switch(startMenu){
            case CREATE_CLIENT:
                operations.createClient();
                controlLoop();
                break;
            case CREATE_EMPLOYEE:
                operations.createEmployee();
                controlLoop();
                break;
            case LOGIN:
                userMenu();
                break;
            case CLOSE:
                exit();
                break;
        }
    }

    private void userMenu() {
        User user = operations.login();
        if(user instanceof Client)
            clientMenu((Client)user);
        else if(user instanceof Employee)
            employeeMenu((Employee)user);
    }

    private void clientMenu(Client client){
        ClientMenu clientMenu = choice(ClientMenu.class);
        switch(clientMenu){
            case BUY:
                operations.buyProduct(client);
                clientMenu(client);
                break;
            case BROWSE:
                operations.showProducts();
                clientMenu(client);
                break;
            case CLOSE:
                exit();
                break;
        }
    }

    private void employeeMenu(Employee employee){
        EmployeeMenu employeeMenu = choice(EmployeeMenu.class);
        switch(employeeMenu){
            case ADD:
                addMenu(employee);
                break;
            case BROWSE:
                operations.showProducts();
                employeeMenu(employee);
                break;
            case DELETE:
                operations.deleteProduct();
                employeeMenu(employee);
                break;
            case CLOSE:
                exit();
                break;
        }
    }

    private void addMenu(Employee employee){
        AddMenu addMenu = choice(AddMenu.class);
        switch(addMenu){
            case COMPUTER_GAMES:
                operations.createComputerGames();
                employeeMenu(employee);
                break;
            case BOOKS:
                operations.createBooks();
                employeeMenu(employee);
                break;
            case BACK:
                employeeMenu(employee);
                break;
        }
    }

    private void exit(){
        fm.save(shopUsers, shopProducts);
        cwu.closeScanner();
    }

    private <E extends Enum<E>> E choice(Class<E> type){
        E menuChoice = null;
        boolean isOk = false;
        do {
            cwu.printer("Choose an option: ");
            for (E m : type.getEnumConstants()) {
                cwu.printer(m.toString());
            }
            int choice = cwu.getInt();
            try{
                menuChoice = type.getEnumConstants()[choice];
                isOk = true;
            } catch(NoSuchAnOptionException e){
                cwu.printer(e.getMessage());
            } catch (IndexOutOfBoundsException e){
                cwu.printer("There is no such an option: " + choice);
            }
        } while(!isOk);
        return menuChoice;
    }
}
