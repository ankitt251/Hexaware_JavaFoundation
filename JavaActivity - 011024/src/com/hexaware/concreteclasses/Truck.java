package com.hexaware.concreteclasses;

import com.hexaware.abstractclasses.Vehicle;
import java.util.Scanner;

public class Truck extends Vehicle {
	Scanner sc = new Scanner(System.in);
    public Truck(String name, double rentalPrice) {
        super(name, rentalPrice);
    }
    
    public void rentVehicle() {
        if (!isRented()) {
            System.out.print("Enter number of days to rent the Truck: ");
            int days = sc.nextInt();
            double totalCost = days * getRentalPrice();

            setRented(true);
            System.out.println(getName() + " (Truck) has been rented for " + days + " day(s) at Rs." + getRentalPrice() + " per day.");
            System.out.println("Total Rental Cost: Rs." + totalCost);
        } else {
            System.out.println(getName() + " (Truck) is already rented.");
        }
    }


    public void returnVehicle() {
        if (isRented()) {
            setRented(false);
            System.out.println(getName() + " (Truck) has been returned.");
        } else {
            System.out.println(getName() + " (Truck) is not currently rented.");
        }
    }
}
