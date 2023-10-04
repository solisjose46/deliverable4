package ser322.states;

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

    private void printUserTypePrompt() {
        System.out.println("Welcome!");
        System.out.println("Customer or Seller?");
        System.out.println("Enter 'customer' or 'seller': ");
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
                printUserTypePrompt();
                String userType =  scanner.nextLine().toLowerCase();

                if(!userType.equals("customer") && !userType.equals("seller")) {
                    System.out.println("Invalid choice");
                    continue;
                }

                printEmailPrompt();
                String email = scanner.nextLine();

                printPasswordPrompt();
                String password = scanner.nextLine();

                dbManager.setUser(email, password);

                if(userType.equals("customer")) {
                    this.currentState = new CustomerHomeState();
                }
                else {
                    this.currentState = new SellerHomeState();
                }

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