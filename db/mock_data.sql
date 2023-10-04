use deliverable4;

INSERT INTO Users (email, password, name, billing_address, payment)
VALUES
    ('customer1@example.com', 'password1', 'Customer 1', '123 Street Ave, City', 'Credit Card'),
    ('customer2@example.com', 'password2', 'Customer 2', '456 Avenue St, Town', 'PayPal'),
    ('seller1@example.com', 'password1', 'Seller 1', '789 Boulevard Rd, Village', 'Bank Transfer'),
    ('seller2@example.com', 'password2', 'Seller 2', '101 Park Lane, Hamlet', 'Credit Card');

INSERT INTO Customers (email, shipping_address)
VALUES
    ('customer1@example.com', '123 Street Ave, City'),
    ('customer2@example.com', '456 Avenue St, Town');

INSERT INTO Sellers (email)
VALUES
    ('seller1@example.com'),
    ('seller2@example.com');

INSERT INTO Products (email, name, description, price, quantity)
VALUES
    ('seller1@example.com', 'Product 1', 'Description 1', 10.99, 100),
    ('seller1@example.com', 'Product 2', 'Description 2', 19.99, 50),
    ('seller2@example.com', 'Product 3', 'Description 3', 15.49, 75),
    ('seller2@example.com', 'Product 4', 'Description 4', 8.99, 120);

INSERT INTO Reviews (email, product_id, description, rating)
VALUES
    ('customer1@example.com', 1, 'Great product!', 5),
    ('customer1@example.com', 3, 'Nice quality.', 4),
    ('customer2@example.com', 2, 'Average product.', 3);