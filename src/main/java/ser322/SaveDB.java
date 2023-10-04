package ser322;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import ser322.entities.Product;
import ser322.entities.Review;
import ser322.entities.User;

public class DBManager {
    private Connection connection;
    private User user;

    public DBManager(Connection connection, String email, String password) throws SQLException {
        this.connection = connection;
        // validate user with email and password
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void userReadReviews(Integer productId) throws SQLException {
        // calculate and print average rating
        // print all reviews for a product: (user) name, (review) description, rating
    }

    public void userReadProduct(Integer productId) throws SQLException {
        // print product details with user's email: product id, product name, description, price
        // userReadReviews(productId)
    }

    public void sellerCreateProduct(Product product) throws SQLException {
        // add product to Product table with user's email
        // Print success message: <product name> successfully added!
    }

    public void sellerReadProducts() throws SQLException {
        // print all products with user's email: product id, product name, description, price, quantity
    }

    public boolean validateSellerProduct(Integer productId) {
        // return true if user's email is associated with product
        // else return false
    }

    public void sellerUpdateProductDescription(Integer productId, String description) throws SQLException {
        // validateSellerProduct: if false print item error message
        // update product's description
        // print: <product name> description successfully updated!
    }

    public void sellerUpdateProductQuatituy(Integer productId, Integer quantity) throws SQLException {
        // validateSellerProduct: if false print item error message
        // validate quantity: quantity must be greater than 0
        // update product's quantity
        // print: <product name> description successfully updated!
    }

    public void sellerDeleteProduct(Integer productId) throws SQLException {
        // validateSellerProduct: if false print item error message and return
        // delete product from table
        // print: <product name> successfully removed!
    }

    public void customerReadProducts() throws SQLException {
        // print all Products: product id, product name, price
    }

    public void customerCreateReview(Review review) throws SQLException {
        // add review to Review table
        // print: review successfully posted!
    }

    public void validateCustomerReview(Integer reviewId) {
        // return true if user's email is associated with review
        // else return false
    }

    public void customerUpdateReview(Integer reviewId, Review review) throws SQLException {
        // validateCustomerReview: if false print review error and return
        // update review description, rating with reviewId
        // print: review successfully updated!
    }

    public void customerDeleteReview(Integer reviewId) throws SQLException {
        // validateCustomerReview: if false print review error and return
        // delete review from Review table with reviewId
        // print: review successfully removed!
    }

    public void customerReadCart() throws SQLException {
        // If no rows in cart with user's email, print: cart is empty! and return
        // print all product's in Cart table with user's email: product id, product name, quantity, price
        // calculate and print total
    }

    public void customerUpdateAddCart(Integer productId) throws SQLException {
        // If product and user's email not in Cart table add to Cart table with quantity one
        // else update quantity by one
        // print: Added <product name> to cart!
    }

    public void customerUpdateRemoveCart(Integer productId) throws SQLException {
        // Using user's email and product id, reduce cart quanity by one
        // If quantity is zero, remove row
    }

    public ArrayList<Integer> getCartProducts() throws SQLException {
        // using user's email, get product ids and return as arraylist
    }

    public void customerDeleteCart() throws SQLException {
        // remove all products associated with user from Cart table
    }

    public void customerCreateOrder() throws SQLException {
        // getCartProducts()
        // customerDeleteCart()
        // initialize timestamp
        // add product ids to Order table with same timestamp
    }
}