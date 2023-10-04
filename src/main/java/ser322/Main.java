package ser322;

import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static User user = new User();
    private static DBManager dbManager;

    private static boolean printUserTypeMenu() {    
        System.out.println("Customer or Seller? Enter 'customer' or 'seller': ");
        String userInput = scanner.nextLine().trim().toLowerCase();
        return userInput.equals("customer");
    }

    private static void setUser() {
        System.out.println("Enter email: ");
        String email = scanner.nextLine();
        System.out.println("Enter password:");
        String password = scanner.nextLine();
        user.email = email;
        user.password = password;
    }

    // customer menu

    private static void printCustomerHome() {
        System.out.println("HOME")
        System.out.println("-----");
        System.out.println("1. shopping");
        System.out.println("2. view your product reviews");
    }

    private static void printCustomerShopping() {
        System.out.println("Shopping");
        System.out.println("-----");
        // left off here
        dbManager.customerReadProducts();
        System.out.println("1. go Home");
        System.out.println("2. go to product page?")
        System.out.println("3. Add to cart?");
        System.out.println("4. Remove from cart?");
    }








    public static void main(String[] args) {
    }
}