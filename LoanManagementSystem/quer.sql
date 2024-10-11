CREATE DATABASE dbuserp2;

create database loan_management;

use loan_management;

CREATE TABLE Customer (
    customerId INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255),
    email VARCHAR(255),
    phoneNumber VARCHAR(15),
    address VARCHAR(255),
    creditScore INT
);

CREATE TABLE Loan (
    loanId INT PRIMARY KEY AUTO_INCREMENT,
    customerId INT,
    principalAmount DOUBLE,
    interestRate DOUBLE,
    loanTerm INT,
    loanType VARCHAR(20),
    loanStatus VARCHAR(20),
    FOREIGN KEY (customerId) REFERENCES Customer(customerId)
);

CREATE TABLE HomeLoan (
    loanId INT PRIMARY KEY,
    propertyAddress VARCHAR(255),
    propertyValue DOUBLE,
    FOREIGN KEY (loanId) REFERENCES Loan(loanId)
);

CREATE TABLE CarLoan (
    loanId INT PRIMARY KEY,
    carModel VARCHAR(255),
    carValue DOUBLE,
    FOREIGN KEY (loanId) REFERENCES Loan(loanId)
);


