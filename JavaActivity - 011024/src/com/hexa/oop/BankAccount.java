package com.hexa.oop;

abstract class BankAccount {
    
    private String accountNumber;
    private double balance;
    
    
    public BankAccount(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }
    
    
    public String getAccountNumber() {
        return accountNumber;
    }
    
    
    public double getBalance() {
        return balance;
    }
    
    
    public void setBalance(double balance) {
        this.balance = balance;
    }
    
    
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: " + amount);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }
    
    
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn: " + amount);
        } else {
            System.out.println("Insufficient balance or invalid amount.");
        }
    }
    
    
    public abstract void calculateInterest();
}

