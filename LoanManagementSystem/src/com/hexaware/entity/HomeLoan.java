package com.hexaware.entity;

public class HomeLoan extends Loan {
    private String propertyAddress;
    private double propertyValue;

    public HomeLoan() {}

    public HomeLoan(int loanId, Customer customer, double principalAmount, double interestRate, int loanTerm, String loanStatus, String propertyAddress, double propertyValue,int remainingEmis, int creditScore) {
        super(loanId, customer, principalAmount, interestRate, loanTerm, "HomeLoan", loanStatus, remainingEmis, creditScore);
        this.propertyAddress = propertyAddress;
        this.propertyValue = propertyValue;
    }

    // Getters and Setters
    public String getPropertyAddress() {
        return propertyAddress;
    }

    public void setPropertyAddress(String propertyAddress) {
        this.propertyAddress = propertyAddress;
    }

    public double getPropertyValue() {
        return propertyValue;
    }

    public void setPropertyValue(double propertyValue) {
        this.propertyValue = propertyValue;
    }

    @Override
    public String toString() {
        return "HomeLoan{" +
                "propertyAddress='" + propertyAddress + '\'' +
                ", propertyValue=" + propertyValue +
                "} " + super.toString();
    }
}

