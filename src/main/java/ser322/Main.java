package ser322;

import java.sql.Connection;
import java.util.Scanner;
import ser322.states.StoreState;
import ser322.states.InitState;
import ser322.states.ExitState;

public class Main {

    public static void main(String[] args) {
        String dbUsername = "username";
        String dbPassword = "password123";
        String dbUrl = "jdbc:mysql://localhost/deliverable4";
        String driver = "com.mysql.cj.jdbc.Driver";

        try {
            class.forName(driver);
            Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
            connection.setAutoCommit(false);
            DBManager dbManager = new dbManager(connection);

            StoreState currentState = InitState();
            Scanner scanner = new Scanner(System.in);
            
            currentState.setDatabase(dbManager);
            currentState.setScanner(scanner);
            
            while(!(currentState instanceof ExitState)) {
                currentState.play();
                currentState = currentState.getState();
            }

            currentState.play();
        }
        catch(Exception e) {
            System.out.println("Program exception");
        }
    }
}