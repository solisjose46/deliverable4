package ser322.states;

import java.util.Scanner;
import ser322.entities.User;

public interface StoreState {
    public void setDatabase(DBManager dbManager);
    public void setScanner(Scanner scanner);
    public StoreState getState();
    public void play();
}