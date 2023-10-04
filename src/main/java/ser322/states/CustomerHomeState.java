package ser322.states;

import java.util.Scanner;
import ser322.DBManager;

public class CustomerHomeState implements StoreState {
    DBManager dbManager;
    Scanner scanner;
    StoreState currentState;

    CustomerHomeState() {
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

    private void printCustomerHomeMenu() {
        System.out.println("Home");
        System.out.println("------");
        System.out.println("a) See products for sale");
        System.out.println("b) See your reviews");
        System.out.println("c) exit");
    }

    @Override
    public void play() {
        while(true) {
            printCustomerHomeMenu();
            String userInput = scanner.nextLine().toLowerCase();

            if(!userInput.equals("a") && !userInput.equals("b") && !userInput.equals("c")) {
                System.out.println("Invalid option");
                System.out.println("Select a, b or c");
                continue;
            }

            StoreState nextState;

            if(userInput.equals("a")) {
                nextState = new CustomerShoppingState();
            }
            else if(userInput.equals("b")) {
                nextState = new CustomerReviewsState();
            }
            else {
                nextState = new ExitState();
            }

            this.currentState = nextState;
            this.currentState.setDatabase(dbManager);
            this.currentState.setScanner(scanner);

            return;            
        }

    }
}