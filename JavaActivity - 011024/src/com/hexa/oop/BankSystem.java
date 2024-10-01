package com.hexa.oop;

public class BankSystem {
    public static void main(String[] args) {
        
        SavingsAccount savingsAccount = new SavingsAccount("SavAcc123456", 1000, 3.5);
        savingsAccount.deposit(500);
        savingsAccount.withdraw(200);
        savingsAccount.applyInterest();
        System.out.println("Savings Account Balance: " + savingsAccount.getBalance());

        CurrentAccount currentAccount = new CurrentAccount("CurrAcc123456", 5000, 2.0);
        currentAccount.deposit(1000);
        currentAccount.withdraw(300);
        currentAccount.applyInterest();
        System.out.println("Current Account Balance: " + currentAccount.getBalance());
    }
}

