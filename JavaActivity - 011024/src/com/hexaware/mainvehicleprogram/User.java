package com.hexaware.mainvehicleprogram;

import com.hexaware.abstractclasses.Vehicle;
import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private List<Vehicle> rentedVehicles;

    // Constructor
    public User(String name) {
        this.name = name;
        this.rentedVehicles = new ArrayList<>();
    }

    // Rent a vehicle
    public void rentVehicle(Vehicle vehicle) {
        vehicle.rentVehicle();
        if (vehicle.isRented()) {
            rentedVehicles.add(vehicle);
        }
    }

    // Return a vehicle
    public void returnVehicle(Vehicle vehicle) {
        vehicle.returnVehicle();
        if (!vehicle.isRented()) {
            rentedVehicles.remove(vehicle);
        }
    }

    // View rented vehicles
    public void viewRentedVehicles() {
        if (rentedVehicles.isEmpty()) {
            System.out.println(name + " has no rented vehicles.");
        } else {
            System.out.println(name + "'s rented vehicles:");
            for (Vehicle v : rentedVehicles) {
                System.out.println(v.getName() + " (" + v.getClass().getSimpleName() + ")");
            }
        }
    }
}
