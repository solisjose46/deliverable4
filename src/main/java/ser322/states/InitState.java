package ser322.states;

import java.util.Scanner;
import ser322.DBManager;

public class InitState implements StoreState {
    DBManager dbManager;
    Scanner scanner;
    StoreState currentState;

    InitState() {
        this.currentState = this;
    }

    @Override
    public void setDatabase(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    @Override
    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public StoreState getState() {
        return this.currentState;
    }

    private void printWelcome() {
        System.out.println("Welcome!");
        System.out.println("Cusomer login");
    }

    private void printEmailPrompt() {
        System.out.println("Enter email: ");
    }

    private void printPasswordPrompt() {
        System.out.println("Enter password: ");
    }

    @Override
    public void play() {
        while(true) {
            try {
                printWelcome();

                printEmailPrompt();
                String email = scanner.nextLine();

                printPasswordPrompt();
                String password = scanner.nextLine();

                dbManager.setUser(email, password);

                this.currentState = new CustomerHomeState();
                this.currentState.setDatabase(dbManager);
                this.currentState.setScanner(scanner);

                return;
            }
            catch(SQLException e) {
                System.out.println("Error with email and/or password");
            }
        }

    }
}