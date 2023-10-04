create database deliverable4;
use deliverable4;

CREATE USER 'username1'@'localhost' IDENTIFIED BY 'password123';
GRANT ALL PRIVILEGES ON deliverable4.* TO 'username1'@'localhost';
FLUSH PRIVILEGES;

create table Users (
    email varchar(255),
    password varchar(255),
    name varchar(255),
    billing_address varchar(255),
    payment varchar(255),
    primary key (email)
);

create table Sellers (
    seller_id int auto_increment,
    email varchar(255),
    primary key (seller_id, email),
    foreign key (email) references Users(email)
);

create table Customers (
    customer_id int auto_increment,
    email varchar(255),
    shipping_address varchar(255),
    primary key (customer_id, email),
    foreign key (email) references Users(email)
);

create table Products (
    product_id int auto_increment,
    email varchar(255),
    name varchar(255),
    description varchar(255),
    price decimal(10,2),
    quantity int,
    primary key (product_id),
    foreign key (email) references Sellers(email)
);

create table Carts (
    cart_id int auto_increment,
    email varchar(255) not null,
    product_id int not null,
    quantity int not null,
    primary key (cart_id),
    foreign key (email) references Customers(email),
    foreign key (product_id) references Products(product_id)
);

create table Orders (
    order_id int auto_increment,
    email varchar(255) not null,
    product_id int not null,
    quantity int,
    transaction_date datetime,
    primary key (order_id),
    foreign key (email) references Customers(email),
    foreign key (product_id) references Products(product_id)
);

create table Reviews (
    review_id int auto_increment,
    email varchar(255) not null,
    product_id int not null,
    description varchar(255),
    rating int not null,
    primary key (review_id),
    foreign key (email) references Customers(email),
    foreign key (product_id) references Products(product_id)
);
