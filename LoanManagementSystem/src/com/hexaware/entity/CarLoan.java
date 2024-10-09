package com.hexaware.entity;

public class CarLoan extends Loan {
    private String carModel;
    private double carValue;

    public CarLoan() {}

	/*
	 * public CarLoan(int loanId, Customer customer, double principalAmount, double
	 * interestRate, int loanTerm, String loanStatus, String carModel, double
	 * carValue) { super(loanId, customer, principalAmount, interestRate, loanTerm,
	 * "CarLoan", loanStatus); this.carModel = carModel; this.carValue = carValue; }
	 */
    
	

    // Getters and Setters
    public String getCarModel() {
        return carModel;
    }

    public CarLoan(int loanId, Customer customer, double principalAmount, double interestRate, int loanTerm,
			String loanType, String loanStatus, int remainingEmis, int creditScore) {
		super(loanId, customer, principalAmount, interestRate, loanTerm, loanType, loanStatus, remainingEmis, creditScore);
		// TODO Auto-generated constructor stub
	}

	public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public double getCarValue() {
        return carValue;
    }

    public void setCarValue(double carValue) {
        this.carValue = carValue;
    }

    @Override
    public String toString() {
        return "CarLoan{" +
                "carModel='" + carModel + '\'' +
                ", carValue=" + carValue +
                "} " + super.toString();
    }
}

