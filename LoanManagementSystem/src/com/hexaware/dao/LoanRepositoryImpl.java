package com.hexaware.dao;

import com.hexaware.entity.Loan;
import com.hexaware.exception.InvalidLoanException;
import com.hexaware.util.DBUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LoanRepositoryImpl implements ILoanRepository {

    @Override
    public void applyLoan(Loan loan) {
        Connection connection = DBUtil.getDBConn();
        try {
            PreparedStatement stmt = connection.prepareStatement(
                "INSERT INTO Loan (customerId, principalAmount, interestRate, loanTerm, loanType, loanStatus) VALUES (?, ?, ?, ?, ?, ?)"
            );
            stmt.setInt(1, loan.getCustomer().getCustomerId());
            stmt.setDouble(2, loan.getPrincipalAmount());
            stmt.setDouble(3, loan.getInterestRate());
            stmt.setInt(4, loan.getLoanTerm());
            stmt.setString(5, loan.getLoanType());
            stmt.setString(6, "Pending");

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Loan applied successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeDBConn(connection);
        }
    }

    @Override
    public double calculateInterest(int loanId) throws InvalidLoanException {
        Loan loan = getLoanById(loanId);
        double interest = (loan.getPrincipalAmount() * loan.getInterestRate() * loan.getLoanTerm()) / 100;
        return interest;
    }
    
    @Override
    public double calculateInterest(double principalAmount, double interestRate, int loanTerm) {
        return (principalAmount * interestRate * loanTerm) / 12;
    }

    //Only Loan Status
	/*
	 * @Override public String loanStatus(int loanId) throws InvalidLoanException {
	 * Connection connection = DBUtil.getDBConn(); String loanStatus = null;
	 * 
	 * try { PreparedStatement stmt = connection.prepareStatement(
	 * "SELECT loanStatus FROM loan WHERE loanId = ?"); stmt.setInt(1, loanId);
	 * 
	 * ResultSet rs = stmt.executeQuery();
	 * 
	 * if (rs.next()) { loanStatus = rs.getString("loanStatus"); } else { throw new
	 * InvalidLoanException("Loan with ID " + loanId + " not found."); } } catch
	 * (SQLException e) { e.printStackTrace(); throw new
	 * InvalidLoanException("Error retrieving loan status: " + e.getMessage()); }
	 * finally { DBUtil.closeDBConn(connection); }
	 * 
	 * return loanStatus; }
	 */

    
    @Override
    public int getCreditScore(int customerId) throws InvalidLoanException {
        Connection connection = DBUtil.getDBConn();
        int creditScore = 0;

        try {
            PreparedStatement stmt = connection.prepareStatement(
                "SELECT creditScore FROM customer WHERE customerId = ?");
            stmt.setInt(1, customerId);
            
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                creditScore = rs.getInt("creditScore");
            } else {
                throw new InvalidLoanException("Customer with ID " + customerId + " not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new InvalidLoanException("Error retrieving credit score: " + e.getMessage());
        } finally {
            DBUtil.closeDBConn(connection);
        }

        return creditScore;
    }

    @Override
    public String loanStatus(int loanId, int creditScore) throws InvalidLoanException {
        Connection connection = DBUtil.getDBConn();
        String loanStatus = null;

        try {
            PreparedStatement stmt = connection.prepareStatement(
                "SELECT loanStatus FROM loan WHERE loanId = ?");
            stmt.setInt(1, loanId);
            
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                loanStatus = rs.getString("loanStatus");
            } else {
                throw new InvalidLoanException("Loan with ID " + loanId + " not found.");
            }
            
            // Check credit score and update loan status if necessary
            if (creditScore > 650) {
                loanStatus = "Approved";
                // Update loan status in the database (optional)
                PreparedStatement updateStmt = connection.prepareStatement(
                    "UPDATE loan SET loanStatus = ? WHERE loanId = ?");
                updateStmt.setString(1, loanStatus);
                updateStmt.setInt(2, loanId);
                updateStmt.executeUpdate();
            } else {
                loanStatus = "Rejected";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new InvalidLoanException("Error retrieving loan status: " + e.getMessage());
        } finally {
            DBUtil.closeDBConn(connection);
        }

        return loanStatus;
    }

    
    @Override
    public double calculateEMI(int loanId) throws InvalidLoanException {
        Loan loan = getLoanById(loanId);
        double principalAmount = loan.getPrincipalAmount();
        double interestRate = loan.getInterestRate() / 12 / 100; // Monthly interest rate
        int loanTenure = loan.getLoanTerm(); // In months

        // EMI formula: EMI = [P * R * (1 + R)^N] / [(1 + R)^N - 1]
        double emi = (principalAmount * interestRate * Math.pow(1 + interestRate, loanTenure)) /
                (Math.pow(1 + interestRate, loanTenure) - 1);
        return emi;
    }
    
    @Override
    public double calculateEMI(double principalAmount, double interestRate, int loanTerm) {
        interestRate = interestRate / 12 / 100; // Monthly interest rate
        // EMI formula: EMI = [P * R * (1 + R)^N] / [(1 + R)^N - 1]
        double emi = (principalAmount * interestRate * Math.pow(1 + interestRate, loanTerm)) /
                (Math.pow(1 + interestRate, loanTerm) - 1);
        return emi;
    }

    @Override
    public void loanRepayment(int loanId, double amount) throws InvalidLoanException {
        Loan loan = getLoanById(loanId);
        double emi = calculateEMI(loanId);

        if (amount < emi) {
            System.out.println("Payment rejected. The amount is less than the EMI.");
        } else {
            int noOfEmisPaid = (int) (amount / emi);
            loan.setRemainingEmis(loan.getRemainingEmis() - noOfEmisPaid);
            System.out.println("Payment successful. " + noOfEmisPaid + " EMIs paid.");
        }
    }

    @Override
    public List<Loan> getAllLoans() {
        Connection connection = DBUtil.getDBConn();
        List<Loan> loanList = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM loan");
            while (rs.next()) {
                Loan loan = new Loan();
                loan.setLoanId(rs.getInt("loanId"));
                loan.setPrincipalAmount(rs.getDouble("principalAmount"));
                loan.setInterestRate(rs.getDouble("interestRate"));
                loan.setLoanTerm(rs.getInt("loanTerm"));
                loan.setLoanType(rs.getString("loanType"));
                loan.setLoanStatus(rs.getString("loanStatus"));
                loanList.add(loan);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeDBConn(connection);
        }
        return loanList;
    }

    @Override
    public Loan getLoanById(int loanId) throws InvalidLoanException {
        Connection connection = DBUtil.getDBConn();
        Loan loan = null;
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM loan WHERE loanId = ?");
            stmt.setInt(1, loanId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                loan = new Loan();
                loan.setLoanId(rs.getInt("loanId"));
                loan.setPrincipalAmount(rs.getDouble("principalAmount"));
                loan.setInterestRate(rs.getDouble("interestRate"));
                loan.setLoanTerm(rs.getInt("loanTerm"));
                loan.setLoanType(rs.getString("loanType"));
                loan.setLoanStatus(rs.getString("loanStatus"));
            } else {
                throw new InvalidLoanException("Loan with ID " + loanId + " not found");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeDBConn(connection);
        }
        return loan;
        
 
    }
}

