package com.hexaware.main;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.hexaware.dao.AssetManagementService;
import com.hexaware.dao.AssetManagementServiceImpl;
import com.hexaware.dao.UserService;
import com.hexaware.dao.UserServiceImpl;
import com.hexaware.entity.Asset;
import com.hexaware.entity.Reservation;
import com.hexaware.entity.User;

public class AssetManagementApp {
 public static void main(String[] args) {
     AssetManagementService assetManagementService = new AssetManagementServiceImpl();
     UserService userService = new UserServiceImpl();
     Scanner scanner = new Scanner(System.in);
     User user = null;

     while (true) {
         System.out.println("1. Register as Admin");
         System.out.println("2. Register as Employee");
         System.out.println("3. Login as Admin");
         System.out.println("4. Login as Employee");
         System.out.println("5. Exit");

         System.out.print("Enter your choice: ");
         int choice = scanner.nextInt();

         switch (choice) {
             case 1:
                 System.out.print("Enter username: ");
                 String username = scanner.next();
                 System.out.print("Enter password: ");
                 String password = scanner.next();
                 User admin = new User();
                 admin.setUsername(username);
                 admin.setPassword(password);
                 admin.setRole("admin");
                 if (userService.registerUser (admin)) {
                     System.out.println("Admin registered successfully");
                 } else {
                     System.out.println("Failed to register admin");
                 }
                 break;
             case 2:
                 System.out.print("Enter username: ");
                 username = scanner.next();
                 System.out.print("Enter password: ");
                 password = scanner.next();
                 User employee = new User();
                 employee.setUsername(username);
                 employee.setPassword(password);
                 employee.setRole("user");
                 if (userService.registerUser (employee)) {
                     System.out.println("Employee registered successfully");
                 } else {
                     System.out.println("Failed to register employee");
                 }
                 break;
             case 3:
                 System.out.print("Enter username: ");
                 username = scanner.next();
                 System.out.print("Enter password: ");
                 password = scanner.next();
                 user = userService.loginUser (username, password);
                 if (user != null && user.getRole().equals("admin")) {
                	 System.out.println("Admin signed in successfully");
                     adminMenu(assetManagementService, scanner);
                 } else {
                     System.out.println("Invalid admin credentials");
                 }
                 break;
             case 4:
                 System.out.print("Enter username: ");
                 username = scanner.next();
                 System.out.print("Enter password: ");
                 password = scanner.next();
                 user = userService.loginUser (username, password);
                 if (user != null && user.getRole().equals("user")) {
                	 System.out.println("user signed in successfully");
                     employeeMenu(assetManagementService, scanner);
                 } else {
                     System.out.println("Invalid employee credentials");
                 }
                 break;
             case 5:
                 System.exit(0);
                 break;
             default:
                 System.out.println("Invalid choice");
         }
     }
 }

 private static void adminMenu(AssetManagementService assetManagementService, Scanner scanner) {
     while (true) {
         System.out.println("1. Add Asset");
         System.out.println("2. Update Asset");
         System.out.println("3. Delete Asset");
         System.out.println("4. Allocate Asset");
         System.out.println("5. Deallocate Asset");
         System.out.println("6. Perform Maintenance");
         System.out.println("7. Reserve Asset");
         System.out.println("8. Withdraw Reservation");
         System.out.println("9. Exit");

         System.out.print("Enter your choice: ");
         int choice = scanner.nextInt();
         scanner.nextLine();
         switch (choice) {
         case 1:
        	 System.out.print("Enter asset name: ");
             String assetName = scanner.nextLine(); // Use nextLine() to capture spaces

             System.out.print("Enter asset type: ");
             String assetType = scanner.nextLine(); // Use nextLine() to capture spaces

             System.out.print("Enter serial number: ");
             String serialNumber = scanner.nextLine(); // Use nextLine() to capture spaces

             System.out.print("Enter purchase date (YYYY-MM-DD): ");
             String purchaseDate = scanner.nextLine(); // Use nextLine() to capture spaces

             System.out.print("Enter location: ");
             String location = scanner.nextLine(); // Use nextLine() to capture spaces

             System.out.print("Enter status: ");
             String status = scanner.nextLine(); // Use nextLine() to capture spaces

             System.out.print("Enter owner ID: "); // Owner will now refer to user ID
             int ownerId = scanner.nextInt();
        	    Asset asset = new Asset();
        	    asset.setName(assetName);
        	    asset.setType(assetType);
        	    asset.setSerialNumber(serialNumber);
        	    asset.setPurchaseDate(purchaseDate);
        	    asset.setLocation(location);
        	    asset.setStatus(status);
        	    asset.setOwnerId(ownerId); // Set user ID as owner
        	    if (assetManagementService.addAsset(asset)) {
        	        System.out.println("Asset added successfully");
        	    } else {
        	        System.out.println("Failed to add asset");
        	    }
        	    break;

        	case 2:
        	    System.out.print("Enter asset ID: ");
        	    int assetId = scanner.nextInt();
        	    scanner.nextLine();
        	    
        	    System.out.print("Enter new asset name: ");
        	    assetName = scanner.nextLine();
        	    System.out.print("Enter new asset type: ");
        	    assetType = scanner.nextLine();
        	    System.out.print("Enter new serial number: ");
        	    serialNumber = scanner.nextLine();
        	    System.out.print("Enter new purchase date: ");
        	    purchaseDate = scanner.nextLine();
        	    System.out.print("Enter new location: ");
        	    location = scanner.nextLine();
        	    System.out.print("Enter new status: ");
        	    status = scanner.nextLine();
        	    System.out.print("Enter new owner ID: "); // Updated to user ID
        	    ownerId = scanner.nextInt();
        	    asset = new Asset();
        	    asset.setAssetId(assetId);
        	    asset.setName(assetName);
        	    asset.setType(assetType);
        	    asset.setSerialNumber(serialNumber);
        	    asset.setPurchaseDate(purchaseDate);
        	    asset.setLocation(location);
        	    asset.setStatus(status);
        	    asset.setOwnerId(ownerId); // User ID as owner
        	    if (assetManagementService.updateAsset(asset)) {
        	        System.out.println("Asset updated successfully");
        	    } else {
        	        System.out.println("Failed to update asset");
        	    }
        	    break;

        	case 3:
        	    System.out.print("Enter asset ID: ");
        	    assetId = scanner.nextInt();
        	    if (assetManagementService.deleteAsset(assetId)) {
        	        System.out.println("Asset deleted successfully");
        	    } else {
        	        System.out.println("Failed to delete asset");
        	    }
        	    break;

        	case 4:
        	    System.out.print("Enter asset ID: ");
        	    assetId = scanner.nextInt();
        	    System.out.print("Enter user ID: "); // Replaced employee ID with user ID
        	    int userId = scanner.nextInt();
        	    System.out.print("Enter allocation date: ");
        	    String allocationDate = scanner.next();
        	    if (assetManagementService.allocateAsset(assetId, userId, allocationDate)) { // Using user ID for allocation
        	        System.out.println("Asset allocated successfully");
        	    } else {
        	        System.out.println("Failed to allocate asset");
        	    }
        	    break;

        	case 5:
        	    System.out.print("Enter asset ID: ");
        	    assetId = scanner.nextInt();
        	    System.out.print("Enter user ID: "); // Replaced employee ID with user ID
        	    userId = scanner.nextInt();
        	    System.out.print("Enter return date: ");
        	    String returnDate = scanner.next();
        	    if (assetManagementService.deallocateAsset(assetId, userId, returnDate)) { // Using user ID for deallocation
        	        System.out.println("Asset deallocated successfully");
        	    } else {
        	        System.out.println("Failed to deallocate asset");
        	    }
        	    break;

        	case 6:
        	    System.out.print("Enter asset ID: ");
        	    assetId = scanner.nextInt();
        	    scanner.nextLine();
        	    
        	    System.out.print("Enter maintenance date: ");
        	    String maintenanceDate = scanner.nextLine();
        	    System.out.print("Enter description: ");
        	    String description = scanner.nextLine();
        	    System.out.print("Enter cost: ");
        	    double cost = scanner.nextDouble();
			    scanner.nextLine();
        	    if (assetManagementService.performMaintenance(assetId, maintenanceDate, description, cost)) {
        	        System.out.println("Maintenance performed successfully");
        	    } else {
        	        System.out.println("Failed to perform maintenance");
        	    }
        	    break;

        	case 7:
        	    System.out.print("Enter asset ID: ");
        	    assetId = scanner.nextInt();
        	    System.out.print("Enter user ID: "); // Replaced employee ID with user ID
        	    userId = scanner.nextInt();
        	    scanner.nextLine();
        	    System.out.print("Enter Reservation Date(YYYY-MM-DD): ");
        	    String reservationDate = scanner.nextLine();
			    System.out.print("Enter Status (Reserved, Cancelled, etc.): ");
			    String status1= scanner.nextLine();
        	    if (assetManagementService.reserveAsset(assetId, userId, reservationDate, status1)) { // Using user ID for reservation
        	        System.out.println("Asset reserved successfully");
        	    } else {
        	        System.out.println("Failed to reserve asset");
        	    }
        	    break;

        	case 8:
        	    System.out.print("Enter asset ID: ");
        	    assetId = scanner.nextInt();
        	    System.out.print("Enter user ID: "); // Replaced employee ID with user ID
        	    userId = scanner.nextInt();
        	    System.out.print("Enter withdrawal date: ");
        	    String withdrawalDate = scanner.next();
        	    if (assetManagementService.withdrawReservation(assetId, userId, withdrawalDate)) { // Using user ID for reservation withdrawal
        	        System.out.println("Reservation withdrawn successfully");
        	    } else {
        	        System.out.println("Failed to withdraw reservation");
        	    }
        	    break;

        	case 9:
        	    return;

        	default:
        	    System.out.println("Invalid choice");
        	}
     }
}


 private static void employeeMenu(AssetManagementService assetManagementService, Scanner scanner) {
	    while (true) {
	        System.out.println("1. View Assets");
	        System.out.println("2. Request Asset");
	        System.out.println("3. View Reservations");
	        System.out.println("4. Exit");

	        System.out.print("Enter your choice: ");
	        int choice = scanner.nextInt();

	        switch (choice) {
	            case 1:
	                viewAssets(assetManagementService, scanner);
	                break;
	            case 2:
	                requestAsset(assetManagementService, scanner);
	                break;
	            case 3:
	                viewReservations(assetManagementService, scanner);
	                break;
	            case 4:
	                return;
	            default:
	                System.out.println("Invalid choice");
	        }
	    }
	}

	private static void viewAssets(AssetManagementService assetManagementService, Scanner scanner) {
	    try {
	        List<Asset> assets = assetManagementService.getAssets();
	        if (assets.isEmpty()) {
	            System.out.println("No assets available");
	        } else {
	            System.out.println("Available assets:");
	            for (Asset asset : assets) {
	                System.out.println("Asset ID: " + asset.getAssetId());
	                System.out.println("Asset Name: " + asset.getName());
	                System.out.println("Asset Type: " + asset.getType());
	                System.out.println("Serial Number: " + asset.getSerialNumber());
	                System.out.println("Purchase Date: " + asset.getPurchaseDate());
	                System.out.println("Location: " + asset.getLocation());
	                System.out.println("Status: " + asset.getStatus());
	                System.out.println("Owner ID: " + asset.getOwnerId()); // Replaced Employee ID with Owner ID (user ID)
	                System.out.println();
	            }
	        }
	    } catch (SQLException e) {
	        System.out.println("Error viewing assets: " + e.getMessage());
	    }
	}

	private static void requestAsset(AssetManagementService assetManagementService, Scanner scanner) {
	    System.out.print("Enter asset ID: ");
	    int assetId = scanner.nextInt();
	    System.out.print("Enter user ID: "); // Changed from employee ID to user ID
	    int userId = scanner.nextInt();
	    scanner.nextLine();
	    
	    System.out.print("Enter reservation date: ");
	    String reservationDate = scanner.nextLine();
	    System.out.print("Enter Status (Reserved, Cancelled, etc.): ");
	    String status1= scanner.nextLine();
	    if (assetManagementService.reserveAsset(assetId, userId,reservationDate, status1)) { // Reservation uses user ID
	        System.out.println("Asset reserved successfully");
	    } else {
	        System.out.println("Failed to reserve asset");
	    }
	}

	private static void viewReservations(AssetManagementService assetManagementService, Scanner scanner) {
	    try {
	        List<Reservation> reservations = assetManagementService.getReservations();
	        if (reservations.isEmpty()) {
	            System.out.println("No reservations available");
	        } else {
	            System.out.println("Reservations:");
	            for (Reservation reservation : reservations) {
	                System.out.println("Asset ID: " + reservation.getAssetId());
	                System.out.println("User ID: " + reservation.getUserId()); // Changed to user ID
	                System.out.println("Reservation Date: " + reservation.getReservationDate());
	                System.out.println("Withdrawal Date: " + reservation.getWithdrawalDate());
	                System.out.println();
	            }
	        }
	    } catch (SQLException e) {
	        System.out.println("Error viewing reservations: " + e.getMessage());
	    }
	}
}