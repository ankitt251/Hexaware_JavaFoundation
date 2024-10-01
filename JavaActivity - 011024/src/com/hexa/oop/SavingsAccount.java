package com.hexa.oop;

class SavingsAccount extends BankAccount implements InterestCalculator {
    private double interestRate;
    
    
    public SavingsAccount(String accountNumber, double balance, double interestRate) {
        super(accountNumber, balance);
        this.interestRate = interestRate;
    }
    
    @Override
    public void calculateInterest() {
        double interest = getBalance() * interestRate / 100;
        setBalance(getBalance() + interest);
        System.out.println("Interest applied: " + interest);
    }
    
    @Override
    public void applyInterest() {
        calculateInterest();
    }
}

