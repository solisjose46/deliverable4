package ser322;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;
import ser322.states.StoreState;
import ser322.states.InitState;
import ser322.states.ExitState;

public class Main {

    public static void main(String[] args) {
        String dbUsername = "username1";
        String dbPassword = "password123";
        String dbUrl = "jdbc:mysql://localhost/deliverable4";
        String driver = "com.mysql.cj.jdbc.Driver";

        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
            connection.setAutoCommit(false);
            DBManager dbManager = new DBManager(connection);

            StoreState currentState = new InitState();
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
            e.printStackTrace();
        }
    }
}