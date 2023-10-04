package ser322.states;

import java.util.Scanner;
import ser322.DBManager;

public interface StoreState {
    public void setDatabase(DBManager dbManager);
    public void setScanner(Scanner scanner);
    public StoreState getState();
    public void play();
}