package ser322.states;

import java.util.Scanner;
import ser322.DBManager;
import ser322.entities.Review;

public class CustomerReviewsState implements StoreState {
    DBManager dbManager;
    Scanner scanner;
    StoreState currentState;

    CustomerReviewsState() {
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

    private void printReviewMenu() {
        System.out.println("Reviews");
        System.out.println("--------");
        this.dbManager.customerReadReviews();
        System.out.println("-------------------");
        System.out.println("a) update review");
        System.out.println("b) remove review");
        System.out.println("c) go Home");
        System.out.println("d) exit");
    }

    private void printReviewIdPrompt() {
        System.out.println("Enter review id: ");
    }

    private void printReviewUpdateRating() {
        System.out.println("Enter review rating (1-5): ");
    }

    private void printReviewUpdateDescription() {
        System.out.println("Enter review description: ");
    }

    private Integer getIntFromString(String userInput) {
        return Integer.parseInt(userInput);
    }

    @Override
    public void play() {
        while(true) {
            try {
                printCustomerHomeMenu();
                String userInput = scanner.nextLine().toLowerCase();
                StoreState nextState;
                Review review = new Review();
                review.email = user.email;

                switch(userInput) {
                    case "a":
                        printReviewIdPrompt();
                        review.reviewId = getIntFromString(scanner.nextLine());
                        printReviewUpdateRating();
                        review.rating = getIntFromString(scanner.nextLine());
                        printReviewUpdateDescription();
                        review.description = scanner.nextLine();
                        dbManager.customerUpdateReview(review);
                        continue;
                    case "b":
                        printReviewIdPrompt();
                        review.reviewId = getIntFromString(scanner.nextLine());
                        dbManager.customerDeleteReview(review);
                        continue;
                    case "c":
                        nextState = new CustomerHomeState();
                        break;
                    case "d":
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