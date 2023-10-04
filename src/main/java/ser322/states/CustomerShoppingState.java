package ser322.states;

import java.sql.SQLException;
import java.util.Scanner;
import ser322.DBManager;
import ser322.entities.Review;

public class CustomerShoppingState implements StoreState {
    DBManager dbManager;
    Scanner scanner;
    StoreState currentState;

    CustomerShoppingState() {
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

    private void printShoppingMenu() throws SQLException {
        System.out.println("Shopping");
        System.out.println("--------");
        System.out.println("\nProducts for sale");
        dbManager.customerReadProducts();
        System.out.println("\nYour shopping cart");
        dbManager.customerReadCart();
        System.out.println("-------------------");
        System.out.println("a) add product to cart");
        System.out.println("b) remove product from cart");
        System.out.println("c) see product detail");
        System.out.println("d) place order");
        System.out.println("e) review product");
        System.out.println("f) go Home");
        System.out.println("g) Exit");
    }

    private void printProductIdPrompt() {
        System.out.println("enter product id: ");
    }

    private void printReviewPromptDescription() {
        System.out.println("enter review description: ");
    }

    private void printReviewPromptRating() {
        System.out.println("enter 1-5 rating: "); 
    }

    private Integer getIntFromString(String userInput) {
        return Integer.parseInt(userInput);
    }

    @Override
    public void play() {
        while(true) {
            try {
                printShoppingMenu();
                String userInput = scanner.nextLine().toLowerCase();


                StoreState nextState;
                String productId = "";
                int id = -1;

                switch(userInput) {
                    case "a":
                        printProductIdPrompt();
                        productId = scanner.nextLine();
                        id = getIntFromString(productId);
                        dbManager.customerUpdateAddCart(id);
                        continue;
                    case "b":
                        printProductIdPrompt();
                        productId = scanner.nextLine();
                        id = getIntFromString(productId);
                        dbManager.customerUpdateRemoveCart(id);
                        continue;
                    case "c":
                        printProductIdPrompt();
                        productId = scanner.nextLine();
                        id = getIntFromString(productId);
                        dbManager.userReadProduct(id);
                        continue;
                    case "d":
                        dbManager.customerCreateOrder();
                        nextState = new CustomerHomeState();
                        break;
                    case "e":
                        Review review = new Review();
                        review.email = dbManager.user.email;

                        printProductIdPrompt();
                        productId = scanner.nextLine();
                        review.productId = getIntFromString(productId);
                        printReviewPromptDescription();
                        review.description = scanner.nextLine();
                        printReviewPromptRating();
                        review.rating = getIntFromString(scanner.nextLine());

                        dbManager.customerCreateReview(review);
                        continue;
                    case "f":
                        nextState = new CustomerHomeState();
                        break;
                    case "g":
                        nextState = new ExitState();
                        break;
                    default:
                        System.out.println("Invalid Input");
                        continue;
                }

                this.currentState = nextState;
                this.currentState.setDatabase(dbManager);
                this.currentState.setScanner(scanner);
                return;     

            }
            catch(Exception e) {
                System.out.println("Invalid input");
            }
        }
    }
}