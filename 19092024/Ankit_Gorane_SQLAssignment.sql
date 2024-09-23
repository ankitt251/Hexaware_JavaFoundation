CREATE DATABASE TechShop;
use TechShop;

CREATE TABLE Customers (
    CustomerID INT AUTO_INCREMENT,
    FirstName VARCHAR(50),
    LastName VARCHAR(50),
    Email VARCHAR(100) UNIQUE,
    Phone VARCHAR(15),
    Address VARCHAR(255),
    PRIMARY KEY (CustomerID)
);

INSERT INTO Customers (FirstName, LastName, Email, Phone, Address) VALUES
('John', 'Doe', 'john.doe@example.com', '555-0100', '123 Elm St'),
('Jane', 'Smith', 'jane.smith@example.com', '555-0101', '456 Oak St'),
('Jim', 'Brown', 'jim.brown@example.com', '555-0102', '789 Pine St'),
('Emily', 'Johnson', 'emily.johnson@example.com', '555-0103', '135 Maple St'),
('Michael', 'Davis', 'michael.davis@example.com', '555-0104', '246 Cedar St'),
('Sarah', 'Wilson', 'sarah.wilson@example.com', '555-0105', '357 Birch St'),
('Chris', 'Lee', 'chris.lee@example.com', '555-0106', '468 Walnut St'),
('Laura', 'Miller', 'laura.miller@example.com', '555-0107', '579 Fir St'),
('Daniel', 'Moore', 'daniel.moore@example.com', '555-0108', '680 Spruce St'),
('Olivia', 'Taylor', 'olivia.taylor@example.com', '555-0109', '791 Willow St');


CREATE TABLE Products (
    ProductID INT AUTO_INCREMENT,
    ProductName VARCHAR(100),
    Description TEXT,
    Price DECIMAL(10, 2),
    StockQuantity INT,
    PRIMARY KEY (ProductID)
);

INSERT INTO Products (ProductName, Description, Price, StockQuantity) VALUES
('Laptop', '15-inch, 8GB RAM, 256GB SSD', 899.99, 50),
('Smartphone', '6-inch display, 64GB storage', 499.99, 100),
('Headphones', 'Noise-cancelling', 199.99, 75),
('Keyboard', 'Mechanical with backlight', 79.99, 200),
('Mouse', 'Wireless optical', 29.99, 150),
('Monitor', '24-inch LED', 149.99, 60),
('Printer', 'All-in-one, color', 129.99, 40),
('External Hard Drive', '1TB USB 3.0', 89.99, 80),
('Webcam', '1080p HD', 59.99, 90),
('USB Flash Drive', '32GB', 19.99, 120);


CREATE TABLE Orders (
    OrderID INT AUTO_INCREMENT,
    CustomerID INT,
    OrderDate DATE,
    TotalAmount DECIMAL(10, 2),
    PRIMARY KEY (OrderID),
    FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID)
);

INSERT INTO Orders (CustomerID, OrderDate, TotalAmount) VALUES
(1, '2024-09-01', 1199.97),
(2, '2024-09-02', 499.99),
(3, '2024-09-03', 299.98),
(4, '2024-09-04', 79.99),
(5, '2024-09-05', 129.99),
(6, '2024-09-06', 189.98),
(7, '2024-09-07', 29.99),
(8, '2024-09-08', 149.99),
(9, '2024-09-09', 89.99),
(10, '2024-09-10', 19.99);


CREATE TABLE OrderDetails (
    OrderDetailID INT AUTO_INCREMENT,
    OrderID INT,
    ProductID INT,
    Quantity INT,
    Price DECIMAL(10, 2),
    PRIMARY KEY (OrderDetailID),
    FOREIGN KEY (OrderID) REFERENCES Orders(OrderID),
    FOREIGN KEY (ProductID) REFERENCES Products(ProductID)
);

INSERT INTO OrderDetails (OrderID, ProductID, Quantity, Price) VALUES
(1, 1, 1, 899.99),
(1, 5, 1, 29.99),
(1, 7, 1, 129.99),
(2, 2, 1, 499.99),
(3, 3, 1, 199.99),
(3, 4, 1, 79.99),
(4, 4, 1, 79.99),
(5, 7, 1, 129.99),
(6, 8, 1, 89.99),
(7, 5, 1, 29.99),
(8, 6, 1, 149.99),
(9, 8, 1, 89.99),
(10, 9, 1, 19.99);


CREATE TABLE Inventory (
    ProductID INT,
    QuantityInStock INT,
    PRIMARY KEY (ProductID),
    FOREIGN KEY (ProductID) REFERENCES Products(ProductID)
);

INSERT INTO Inventory (ProductID, QuantityInStock) VALUES
(1, 50),  -- Laptop
(2, 100), -- Smartphone
(3, 75),  -- Headphones
(4, 200), -- Keyboard
(5, 150), -- Mouse
(6, 60),  -- Monitor
(7, 40),  -- Printer
(8, 80),  -- External Hard Drive
(9, 90),  -- Webcam
(10, 120);-- USB Flash Drive

#TASK 2

#Q1
SELECT FIRSTNAME, LASTNAME, EMAIL FROM Customers;

#Q2 
SELECT ORDERID, ORDERDATE, (SELECT FIRSTNAME
FROM CUSTOMERS
WHERE CUSTOMERS.CUSTOMERID = ORDERS.CUSTOMERID)  AS CustomerName
FROM ORDERS;

#Q3
INSERT INTO CUSTOMERS VALUES (11, "HEXA", "WARE",  'hexaware@gmail.com', 1234567889, "Pune, Maharashtre");

#Q4
SET SQL_SAFE_UPDATES = 0;
UPDATE PRODUCTS SET PRICE = PRICE * 1.10;

#Q5
DELETE FROM ORDERDETAILS WHERE ORDERID = 2;
DELETE FROM ORDERS WHERE ORDERID = 2;

#Q6
INSERT INTO ORDERS VALUES(11,11, NOW(), 1200);

#Q7
UPDATE CUSTOMERS SET FIRSTNAME = 'ANKIT'
WHERE CUSTOMERID = ?;

#Q8
UPDATE Orders o
SET total_cost = (
    SELECT SUM(od.price * od.quantity)
    FROM OrderDetails od
    WHERE od.order_id = o.order_id
)
WHERE EXISTS (
    SELECT 1
    FROM OrderDetails od
    WHERE od.order_id = o.order_id
);


#Q9
DELETE FROM OrderDetails
WHERE order_id IN (
    SELECT order_id
    FROM Orders
    WHERE customer_id = 4
);

DELETE FROM Orders
WHERE customer_id = 4; 


#Q10
INSERT INTO PRODUCTS VALUES(11, "TABLET", "15INCH WITH 6GB RAM", 15950, 50);

#Q11
UPDATE Orders
SET status = 'Shipped'
WHERE order_id = 101;

#Q12
UPDATE Customers c
SET c.order_count = (
    SELECT COUNT(o.order_id)
    FROM Orders o
    WHERE o.customer_id = c.customer_id
);


#TASK 3

#Q1
SELECT o.order_id, o.order_date, o.total_amount, c.customer_name, c.email
FROM Orders o
INNER JOIN Customers c
ON o.customer_id = c.customer_id;

#Q2
SELECT p.product_name, SUM(od.quantity * od.unit_price) AS total_revenue
FROM OrderDetails od
INNER JOIN Products p
ON od.product_id = p.product_id
WHERE p.category = 'Electronics'
GROUP BY p.product_name
ORDER BY total_revenue DESC;


#Q3
SELECT c.customer_name, c.contact_info
FROM Customers c
WHERE EXISTS (
    SELECT 1
    FROM Orders o
    WHERE o.customer_id = c.customer_id
);
#Subquery


#Q4
SELECT p.product_name, SUM(od.quantity) AS total_quantity_ordered
FROM OrderDetails od
INNER JOIN Products p
ON od.product_id = p.product_id
WHERE p.category = 'Electronics'
GROUP BY p.product_name
ORDER BY total_quantity_ordered DESC
LIMIT 1;

#Q5
SELECT g.gadget_name, c.category_name
FROM gadgets g
JOIN categories c ON g.category_id = c.category_id;


#Q6
SELECT c.customer_name, AVG(o.total_amount) AS average_order_value
FROM Customers c
INNER JOIN Orders o
ON c.customer_id = o.customer_id
GROUP BY c.customer_name
ORDER BY average_order_value DESC;

#Q7
SELECT 
    o.order_id, 
    c.customer_id, 
    c.customer_name, 
    SUM(oi.quantity * p.price) AS total_revenue
FROM 
    Orders o
JOIN 
    Customers c ON o.customer_id = c.customer_id
JOIN 
    OrderItems oi ON o.order_id = oi.order_id
JOIN 
    Products p ON oi.product_id = p.product_id
GROUP BY 
    o.order_id, c.customer_id, c.customer_name
ORDER BY 
    total_revenue DESC
LIMIT 1;

#Q8
SELECT 
    p.product_name, 
    COUNT(oi.order_id) AS total_orders
FROM 
    Products p
JOIN 
    OrderItems oi ON p.product_id = oi.product_id
WHERE 
    p.category = 'Electronic Gadgets'
GROUP BY 
    p.product_name
ORDER BY 
    total_orders DESC;


#Q9
SELECT 
    c.customer_id, 
    c.customer_name
FROM 
    Customers c
JOIN 
    Orders o ON c.customer_id = o.customer_id
JOIN 
    OrderItems oi ON o.order_id = oi.order_id
JOIN 
    Products p ON oi.product_id = p.product_id
WHERE 
    p.product_name = 'Laptop';
    
    
#Q10
SELECT 
    SUM(oi.quantity * p.price) AS total_revenue
FROM 
    Orders o
JOIN 
    OrderItems oi ON o.order_id = oi.order_id
JOIN 
    Products p ON oi.product_id = p.product_id
WHERE 
    o.order_date BETWEEN '2024-09-05' AND '2024-12-05';  
    
    
    #Task 4
    
    #Q1
SELECT 
    c.customer_id, 
    c.customer_name
FROM 
    Customers c
LEFT JOIN 
    Orders o ON c.customer_id = o.customer_id
WHERE 
    o.order_id IS NULL;

#Q2

SELECT 
    COUNT(product_id) AS total_products
FROM 
    Products
WHERE 
    available_for_sale = 1;  
    
#Q3

SELECT 
    SUM(oi.quantity * p.price) AS total_revenue
FROM 
    Orders o
JOIN 
    OrderItems oi ON o.order_id = oi.order_id
JOIN 
    Products p ON oi.product_id = p.product_id;

#Q4

SELECT 
    AVG(oi.quantity) AS average_quantity_ordered
FROM 
    Products p
JOIN 
    OrderItems oi ON p.product_id = oi.product_id
WHERE 
    p.category = 'Electronics';  
    
#Q5

SELECT 
    SUM(oi.quantity * p.price) AS total_revenue
FROM 
    Orders o
JOIN 
    OrderItems oi ON o.order_id = oi.order_id
JOIN 
    Products p ON oi.product_id = p.product_id
WHERE 
    o.customer_id = 2;  

#Q6

SELECT 
    c.customer_name, 
    COUNT(o.order_id) AS total_orders
FROM 
    Customers c
JOIN 
    Orders o ON c.customer_id = o.customer_id
GROUP BY 
    c.customer_name
ORDER BY 
    total_orders DESC
LIMIT 1;

#Q7
SELECT 
    p.category, 
    SUM(oi.quantity) AS total_quantity_ordered
FROM 
    Products p
JOIN 
    OrderItems oi ON p.product_id = oi.product_id
GROUP BY 
    p.category
ORDER BY 
    total_quantity_ordered DESC
LIMIT 1;

#Q8
SELECT 
    c.customer_name, 
    SUM(oi.quantity * p.price) AS total_spending
FROM 
    Customers c
JOIN 
    Orders o ON c.customer_id = o.customer_id
JOIN 
    OrderItems oi ON o.order_id = oi.order_id
JOIN 
    Products p ON oi.product_id = p.product_id
WHERE 
    p.category = 'Electronic Gadgets'
GROUP BY 
    c.customer_name
ORDER BY 
    total_spending DESC
LIMIT 1;

#Q9

SELECT 
    AVG(total_revenue_per_order) AS average_order_value
FROM (
    SELECT 
        o.order_id, 
        SUM(oi.quantity * p.price) AS total_revenue_per_order
    FROM 
        Orders o
    JOIN 
        OrderItems oi ON o.order_id = oi.order_id
    JOIN 
        Products p ON oi.product_id = p.product_id
    GROUP BY 
        o.order_id
) AS order_revenues;

#Q10

SELECT 
    c.customer_name, 
    COUNT(o.order_id) AS total_orders
FROM 
    Customers c
LEFT JOIN 
    Orders o ON c.customer_id = o.customer_id
GROUP BY 
    c.customer_name
ORDER BY 
    total_orders DESC;


    





