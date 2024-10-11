create database loan_management;
use loan_management;

CREATE TABLE customer (
    customerId INT PRIMARY KEY AUTO_INCREMENT,  -- Customer ID
    name VARCHAR(255) NOT NULL,                 -- Customer Name
    emailAddress VARCHAR(255) NOT NULL UNIQUE,  -- Email Address
    phoneNumber VARCHAR(20),                     -- Phone Number
    address VARCHAR(255),                        -- Address
    creditScore INT CHECK (creditScore >= 300 AND creditScore <= 850)  -- Credit Score
);

CREATE TABLE loan (
    loanId INT PRIMARY KEY AUTO_INCREMENT,           -- Loan ID
    customerId INT,                                  -- Reference to Customer
    principalAmount DOUBLE NOT NULL,                 -- Principal Amount
    interestRate DOUBLE NOT NULL,                    -- Interest Rate
    loanTerm INT NOT NULL,                           -- Loan Term in months
    loanType ENUM('CarLoan', 'HomeLoan') NOT NULL,  -- Loan Type
    loanStatus ENUM('Pending', 'Approved') NOT NULL, -- Loan Status
    FOREIGN KEY (customerId) REFERENCES customer(customerId) ON UPDATE CASCADE ON DELETE CASCADE -- Reference with cascading updates/deletes
);

