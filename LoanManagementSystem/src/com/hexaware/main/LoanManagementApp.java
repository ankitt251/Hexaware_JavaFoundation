package com.hexaware.main;

import com.hexaware.dao.ILoanRepository;
import com.hexaware.dao.LoanRepositoryImpl;
import com.hexaware.exception.InvalidLoanException;
import com.hexaware.util.DBUtil;
import com.hexaware.entity.Customer;
import com.hexaware.entity.Loan;

import java.sql.*;
import java.util.Scanner;

public class LoanManagementApp {

    private static ILoanRepository loanRepository = new LoanRepositoryImpl();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Welcome to Loan Management App");
        System.out.println("1. Register as New Customer");
        System.out.println("2. Continue as Existing Customer");
        System.out.print("Enter your choice: ");
        int initialChoice = scanner.nextInt();
        
        // Handling initial choice
        switch (initialChoice) {
            case 1:
                registerCustomer(scanner);
                break;
            case 2:
                loanManagement(scanner);
                break;
            default:
                System.out.println("Invalid choice! Exiting the application.");
                System.exit(0);
        }
        
        scanner.close();
    }

    private static void loanManagement(Scanner scanner) {
        while (true) {
            System.out.println("1. Apply for Loan");
            System.out.println("2. Check Loan Status");
            System.out.println("3. Calculate EMI");
            System.out.println("4. Loan Repayment");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    applyForLoan(scanner);
                    break;
                case 2:
                    checkLoanStatus(scanner);
                    break;
                case 3:
                    calculateEMI(scanner);
                    break;
                case 4:
                    loanRepayment(scanner);
                    break;
                case 5:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    private static void registerCustomer(Scanner scanner) {
        System.out.print("Enter Customer ID: ");
        int customerId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Email Address: ");
        String email = scanner.nextLine();

        System.out.print("Enter Phone Number: ");
        String phone = scanner.nextLine();

        System.out.print("Enter Address: ");
        String address = scanner.nextLine();

        System.out.print("Enter Credit Score: ");
        int creditScore = scanner.nextInt();

        // Create Customer object
        Customer customer = new Customer(customerId, name, email, phone, address, creditScore);
        saveCustomer(customer);

        // After registration, continue to loan management options
        loanManagement(scanner);
    }


    private static void applyForLoan(Scanner scanner) {
        System.out.print("Enter customer ID: ");
        int customerId = scanner.nextInt();
        System.out.print("Enter principal amount: ");
        double principalAmount = scanner.nextDouble();
        System.out.print("Enter interest rate: ");
        double interestRate = scanner.nextDouble();
        System.out.print("Enter loan term (in months): ");
        int loanTerm = scanner.nextInt();
        System.out.print("Enter loan type (HomeLoan/CarLoan): ");
        String loanType = scanner.next();

        Customer customer = new Customer();
        customer.setCustomerId(customerId);
        
        Loan loan = new Loan();
        loan.setCustomer(customer);
        loan.setPrincipalAmount(principalAmount);
        loan.setInterestRate(interestRate);
        loan.setLoanTerm(loanTerm);
        loan.setLoanType(loanType);
        loan.setLoanStatus("Pending");

        loanRepository.applyLoan(loan);
    }
    
    private static void saveCustomer(Customer customer) {
        Connection connection = DBUtil.getDBConn();
        try {
            String sql = "INSERT INTO customer (customerId, name, emailAddress, phoneNumber, address, creditScore) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, customer.getCustomerId());
            stmt.setString(2, customer.getName());
            stmt.setString(3, customer.getAddress());
            stmt.setString(4, customer.getPhoneNumber());
            stmt.setString(5, customer.getAddress());
            stmt.setInt(6, customer.getCreditScore());

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Customer saved successfully!");
            }
        } catch (SQLException e) {
            System.err.println("Error while saving customer: " + e.getMessage());
            e.printStackTrace();
        } finally {
            DBUtil.closeDBConn(connection);
        }
    }


	/*
	 * private static void checkLoanStatus(Scanner scanner) {
	 * System.out.print("Enter loan ID: "); int loanId = scanner.nextInt(); try {
	 * String status = loanRepository.loanStatus(loanId);
	 * System.out.println("Loan Status: " + status); } catch (InvalidLoanException
	 * e) { System.out.println(e.getMessage()); } }
	 */
    
    
    private static void checkLoanStatus(Scanner scanner) {
        System.out.print("Enter customer ID: ");
        int customerId = scanner.nextInt();
        System.out.print("Enter loan ID: ");
        int loanId = scanner.nextInt();
        
        try {
            // Get customer's credit score
            int creditScore = loanRepository.getCreditScore(customerId);
            
            // Check loan status based on credit score
            String status = loanRepository.loanStatus(loanId, creditScore);
            System.out.println("Loan Status: " + status);
        } catch (InvalidLoanException e) {
            System.out.println(e.getMessage());
        }
    }


    private static void calculateEMI(Scanner scanner) {
        System.out.print("Enter loan ID: ");
        int loanId = scanner.nextInt();
        try {
            double emi = loanRepository.calculateEMI(loanId);
            System.out.println("EMI: " + emi);
        } catch (InvalidLoanException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void loanRepayment(Scanner scanner) {
        System.out.print("Enter loan ID: ");
        int loanId = scanner.nextInt();
        System.out.print("Enter repayment amount: ");
        double amount = scanner.nextDouble();
        try {
            loanRepository.loanRepayment(loanId, amount);
            System.out.println("Loan repayment successful!");
        } catch (InvalidLoanException e) {
            System.out.println(e.getMessage());
        }
    }
}
