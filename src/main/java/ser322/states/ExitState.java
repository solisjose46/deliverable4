package ser322.states;

import java.util.Scanner;
import ser322.DBManager;

public class ExitState implements StoreState {
    DBManager dbManager;
    Scanner scanner;
    StoreState currentState;

    ExitState() {
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

    private void printFarewell() {
        System.out.println("Good bye");
    }

    @Override
    public void play() {
        printFarewell();
    }
}