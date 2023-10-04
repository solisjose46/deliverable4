package ser322;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import ser322.entities.Product;
import ser322.entities.Review;
import ser322.entities.User;

public class DBManager {
    private Connection connection;
    private User user;

    public DBManager(Connection connection) {
        this.connection = connection;


    }

    public void setUser(String email, String password) throws SQLException {
        String query = "SELECT password FROM Users where email = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, email);

        String realPassword = "";
        if(resultSet.next()) {
            realPassword = resultSet.getString("password");
            
        }

        if(!realPassword.equals(password)) {
            throw new SQLException("Bad password");
        }

        System.out.println("login successful");
    }

    public void userReadReviews(Integer productId) throws SQLException {
        // Calculate and print average rating for the product
        String query = "SELECT AVG(rating) AS averageRating FROM Reviews WHERE product_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query)
        preparedStatement.setInt(1, productId);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            Double averageRating = resultSet.getDouble("averageRating");
            System.out.println("Average Rating: " + averageRating);
        }

        // Print all reviews for the product
        query = "SELECT U.name, R.description, R.rating FROM Reviews R " +
                "INNER JOIN Customers C ON R.email = C.email " +
                "INNER JOIN Users U ON R.email = U.email " +
                "WHERE R.product_id = ?";

        preparedStatement = connection.prepareStatement(query)
        preparedStatement.setInt(1, productId);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            String name = resultSet.getString("name");
            String description = resultSet.getString("description");
            int rating = resultSet.getInt("rating");
            System.out.println("Name: " + name + ", Description: " + description + ", Rating: " + rating);
        }
    }

    public void userReadProduct(Integer productId) throws SQLException {
        // Print product details with user's email
        String query = "SELECT P.product_id, P.name, P.description, P.price FROM Products P " +
                "WHERE P.product_id = ? AND P.email = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(query)
        preparedStatement.setInt(1, productId);
        preparedStatement.setString(2, user.username);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            int productID = resultSet.getInt("product_id");
            String name = resultSet.getString("name");
            String description = resultSet.getString("description");
            double price = resultSet.getDouble("price");
            System.out.println("Product ID: " + productID + ", Name: " + name +
                    ", Description: " + description + ", Price: " + price);
        }

        // Call userReadReviews to print reviews for the product
        userReadReviews(productId);
    }

    public void sellerCreateProduct(Product product) throws SQLException {
        // Add product to Product table with user's email
        String query = "INSERT INTO Products (email, name, description, price, quantity) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query)
        preparedStatement.setString(1, user.username);
        preparedStatement.setString(2, product.name);
        preparedStatement.setString(3, product.description);
        preparedStatement.setDouble(4, product.price);
        preparedStatement.setInt(5, product.quantity);
        int rowsAffected = preparedStatement.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println(product.name + " successfully added!");
        }
    }

    public void sellerReadProducts() throws SQLException {
        // Print all products with user's email
        String query = "SELECT product_id, name, description, price, quantity FROM Products WHERE email = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query)
        preparedStatement.setString(1, user.username);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int productId = resultSet.getInt("product_id");
            String name = resultSet.getString("name");
            String description = resultSet.getString("description");
            double price = resultSet.getDouble("price");
            int quantity = resultSet.getInt("quantity");
            System.out.println("Product ID: " + productId + ", Name: " + name +
                    ", Description: " + description + ", Price: " + price + ", Quantity: " + quantity);
        }
    }

    public boolean validateSellerProduct(Integer productId) throws SQLException {
        // Check if user's email is associated with the product
        String query = "SELECT email FROM Products WHERE product_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, productId);
        ResultSet resultSet = preparedStatement.executeQuery();
        String email = "";
        if (resultSet.next()) {
            email = resultSet.getInt("count");
        }

        return user.email.equals(email);
    }

    public void sellerUpdateProductDescription(Integer productId, String description) throws SQLException {
        // Validate if the user is associated with the product
        if (!validateSellerProduct(productId)) {
            System.out.println("Item error: You do not have permission to update this product.");
            return;
        }

        // Update the product's description
        String query = "UPDATE Products SET description = ? WHERE product_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query)
        preparedStatement.setString(1, description);
        preparedStatement.setInt(2, productId);
        int rowsAffected = preparedStatement.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println(productId + " description successfully updated!");
        }
    }

    public void sellerUpdateProductQuantity(Integer productId, Integer quantity) throws SQLException {
        // Validate if the user is associated with the product
        if (!validateSellerProduct(productId)) {
            System.out.println("Item error: You do not have permission to update this product.");
            return;
        }

        // Validate that the quantity is greater than 0
        if (quantity <= 0) {
            System.out.println("Invalid quantity. Quantity must be greater than 0.");
            return;
        }

        // Update the product's quantity
        String query = "UPDATE Products SET quantity = ? WHERE product_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query)
        preparedStatement.setInt(1, quantity);
        preparedStatement.setInt(2, productId);
        int rowsAffected = preparedStatement.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println(productId + " quantity successfully updated!");
        }
    }

    public void sellerDeleteProduct(Integer productId) throws SQLException {
        // Validate if the user is associated with the product
        if (!validateSellerProduct(productId)) {
            System.out.println("Item error: You do not have permission to delete this product.");
            return;
        }

        // Delete the product from the table
        String query = "DELETE FROM Products WHERE product_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, productId);
        int rowsAffected = preparedStatement.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println(productId + " successfully removed!");
        }
    }

    public void customerReadProducts() throws SQLException {
        // Print all Products: product id, product name, price
        String query = "SELECT product_id, name, price FROM Products";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int productId = resultSet.getInt("product_id");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                System.out.println("Product ID: " + productId + ", Name: " + name + ", Price: " + price);
            }
        }
    }

    public void customerCreateReview(Review review) throws SQLException {
        // Add review to Review table
        String query = "INSERT INTO Reviews (email, product_id, description, rating) VALUES (?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query)
        preparedStatement.setString(1, user.username);
        preparedStatement.setInt(2, review.productId);
        preparedStatement.setString(3, review.description);
        preparedStatement.setInt(4, review.rating);
        int rowsAffected = preparedStatement.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Review successfully posted!");
        }
    }

    public boolean validateCustomerReview(Integer reviewId) throws SQLException {
        // Check if user's email is associated with the review
        String query = "SELECT email FROM Reviews WHERE review_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, reviewId);
        ResultSet resultSet = preparedStatement.executeQuery();
        String realEmail = "";
        if (resultSet.next()) {
            realEmail = resultSet.getString("count");
        }

        return realEmail.equals(user.email);
    }

    public void customerUpdateReview(Integer reviewId, Review review) throws SQLException {
        // Validate if the user is associated with the review
        if (!validateCustomerReview(reviewId)) {
            System.out.println("Review error: You do not have permission to update this review.");
            return;
        }

        // Update the review description and rating
        String query = "UPDATE Reviews SET description = ?, rating = ? WHERE review_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query)
        preparedStatement.setString(1, review.description);
        preparedStatement.setInt(2, review.rating);
        preparedStatement.setInt(3, reviewId);
        int rowsAffected = preparedStatement.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Review successfully updated!");
        }
    }

    public void customerDeleteReview(Integer reviewId) throws SQLException {
        // Validate if the user is associated with the review
        if (!validateCustomerReview(reviewId)) {
            System.out.println("Review error: You do not have permission to delete this review.");
            return;
        }

        // Delete the review from the table
        String query = "DELETE FROM Reviews WHERE review_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query)
        preparedStatement.setInt(1, reviewId);
        int rowsAffected = preparedStatement.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Review successfully removed!");
        }
    }

    public void customerReadCart() throws SQLException {
        // Check if there are any rows in the cart with the user's email
        String cartCheckQuery = "SELECT COUNT(*) AS count FROM Carts WHERE email = ?";
        PreparedStatement cartCheckStatement = connection.prepareStatement(cartCheckQuery);
        cartCheckStatement.setString(1, user.username);
        ResultSet cartCheckResult = cartCheckStatement.executeQuery();
        if (!cartCheckResult.next() || cartCheckResult.getInt("count") == 0) {
            System.out.println("Cart is empty!");
            return;
        }

        // Print all products in the cart with user's email: product id, product name, quantity, price
        String cartQuery = "SELECT C.product_id, P.name, C.quantity, P.price FROM Carts C " +
                "INNER JOIN Products P ON C.product_id = P.product_id " +
                "WHERE C.email = ?";
        PreparedStatement cartStatement = connection.prepareStatement(cartQuery);
        cartStatement.setString(1, user.username);
        ResultSet resultSet = cartStatement.executeQuery();
        double total = 0;
        while (resultSet.next()) {
            int productId = resultSet.getInt("product_id");
            String name = resultSet.getString("name");
            int quantity = resultSet.getInt("quantity");
            double price = resultSet.getDouble("price");
            System.out.println("Product ID: " + productId + ", Name: " + name +
                    ", Quantity: " + quantity + ", Price: " + price);
            total += quantity * price;
        }
        System.out.println("Total: " + total);
    }

    public void customerUpdateAddCart(Integer productId) throws SQLException {
        // Check if the product and user's email are not in the Cart table
        String checkQuery = "SELECT COUNT(*) AS count FROM Carts WHERE email = ? AND product_id = ?";
        PreparedStatement checkStatement = connection.prepareStatement(checkQuery)
        checkStatement.setString(1, user.username);
        checkStatement.setInt(2, productId);
        ResultSet checkResult = checkStatement.executeQuery();
        if (checkResult.next() && checkResult.getInt("count") == 0) {
            // Add the product to the Cart table with quantity one
            String insertQuery = "INSERT INTO Carts (email, product_id, quantity) VALUES (?, ?, 1)";
            PreparedStatement insertStatement = connection.prepareStatement(insertQuery)
            insertStatement.setString(1, user.username);
            insertStatement.setInt(2, productId);
            int rowsAffected = insertStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Added product to cart!");
            }
        } else {
            // Update quantity by one
            String updateQuery = "UPDATE Carts SET quantity = quantity + 1 WHERE email = ? AND product_id = ?";
            PreparedStatement updateStatement = connection.prepareStatement(updateQuery)
            updateStatement.setString(1, user.username);
            updateStatement.setInt(2, productId);
            int rowsAffected = updateStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Added product to cart!");
            }
        }
    }

    public void customerUpdateRemoveCart(Integer productId) throws SQLException {
        // Using user's email and product id, reduce cart quantity by one
        String updateQuery = "UPDATE Carts SET quantity = GREATEST(0, quantity - 1) WHERE email = ? AND product_id = ?";
        PreparedStatement updateStatement = connection.prepareStatement(updateQuery)
        updateStatement.setString(1, user.username);
        updateStatement.setInt(2, productId);
        int rowsAffected = updateStatement.executeUpdate();
        if (rowsAffected > 0) {
            // If quantity is zero, remove row
            String deleteQuery = "DELETE FROM Carts WHERE email = ? AND product_id = ? AND quantity = 0";
            PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery)
            deleteStatement.setString(1, user.username);
            deleteStatement.setInt(2, productId);
            deleteStatement.executeUpdate();
        }
    }

    public ArrayList<Integer> getCartProducts() throws SQLException {
        // Using user's email, get product ids and return as ArrayList
        ArrayList<Integer> productIds = new ArrayList<>();
        String query = "SELECT product_id FROM Carts WHERE email = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query)
        preparedStatement.setString(1, user.username);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int productId = resultSet.getInt("product_id");
            productIds.add(productId);
        }
        return productIds;
    }

    public void customerDeleteCart() throws SQLException {
        // Remove all products associated with the user from the Cart table
        String query = "DELETE FROM Carts WHERE email = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query)
        preparedStatement.setString(1, user.username);
        preparedStatement.executeUpdate();
    }

    public void customerCreateOrder() throws SQLException {
        // Get product IDs from the cart
        ArrayList<Integer> productIds = getCartProducts();

        // If the cart is empty, print a message and return
        if (productIds.isEmpty()) {
            System.out.println("Cart is empty!");
            return;
        }

        // Delete all products from the cart
        customerDeleteCart();

        // Initialize timestamp (current date and time)
        java.util.Date date = new java.util.Date();
        java.sql.Timestamp timestamp = new java.sql.Timestamp(date.getTime());

        // Add product IDs to the Order table with the same timestamp
        String query = "INSERT INTO Orders (email, product_id, quantity, transaction_date) VALUES (?, ?, 1, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query)
        preparedStatement.setString(1, user.username);
        preparedStatement.setTimestamp(3, timestamp);
        for (Integer productId : productIds) {
            preparedStatement.setInt(2, productId);
            preparedStatement.executeUpdate();
        }
        System.out.println("Order successfully created!");
    }
}