package com.hexaware.mainvehicleprogram;

import com.hexaware.concreteclasses.Car;
import com.hexaware.concreteclasses.Bike;
import com.hexaware.concreteclasses.Truck;
import com.hexaware.abstractclasses.Vehicle;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Vehicles available for rent
        Vehicle car1 = new Car("Toyota Camry", 100);
        Vehicle bike1 = new Bike("Yamaha R1", 50);
        Vehicle truck1 = new Truck("Volvo Truck", 150);

        // Create a user
        User user = new User("John");

        int choice;
        do {
            System.out.println("\n--- Vehicle Rental System ---");
            System.out.println("1. Rent a Car");
            System.out.println("2. Rent a Bike");
            System.out.println("3. Rent a Truck");
            System.out.println("4. Return a Car");
            System.out.println("5. Return a Bike");
            System.out.println("6. Return a Truck");
            System.out.println("7. View Rented Vehicles");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    user.rentVehicle(car1);
                    break;
                case 2:
                    user.rentVehicle(bike1);
                    break;
                case 3:
                    user.rentVehicle(truck1);
                    break;
                case 4:
                    user.returnVehicle(car1);
                    break;
                case 5:
                    user.returnVehicle(bike1);
                    break;
                case 6:
                    user.returnVehicle(truck1);
                    break;
                case 7:
                    user.viewRentedVehicles();
                    break;
                case 8:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 8);

        sc.close();
    }
}
