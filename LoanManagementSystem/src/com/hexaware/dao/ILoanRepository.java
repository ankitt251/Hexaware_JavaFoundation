package com.hexaware.dao;

import com.hexaware.entity.Loan;
import com.hexaware.exception.InvalidLoanException;

import java.util.List;

public interface ILoanRepository {
    void applyLoan(Loan loan);  // Confirmation from user needed before storing
    
    double calculateInterest(int loanId) throws InvalidLoanException;  // Retrieves loan and calculates interest
    double calculateInterest(double principalAmount, double interestRate, int loanTerm);  // Overloaded version for loan creation
    
    int getCreditScore(int customerId) throws InvalidLoanException;
    String loanStatus(int loanId, int creditScore) throws InvalidLoanException;  // Checks credit score and updates status
    
    double calculateEMI(int loanId) throws InvalidLoanException;  // Retrieves loan and calculates EMI
    double calculateEMI(double principalAmount, double interestRate, int loanTerm);  // Overloaded version for loan creation
    
    void loanRepayment(int loanId, double amount) throws InvalidLoanException;  // Calculate EMIs from amount, reject if less than single EMI
    
    List<Loan> getAllLoans();  // Retrieves and prints all loan details
    
    Loan getLoanById(int loanId) throws InvalidLoanException;  // Retrieves and prints loan details
}
