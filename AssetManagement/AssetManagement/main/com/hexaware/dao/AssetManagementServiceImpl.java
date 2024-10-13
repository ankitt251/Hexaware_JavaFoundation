package com.hexaware.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.hexaware.entity.Asset;
import com.hexaware.entity.Reservation;
import com.hexaware.util.DBConnection;

public class AssetManagementServiceImpl implements AssetManagementService {
 private Connection connection;

 public AssetManagementServiceImpl() {
     connection = DBConnection.getConnection();
 }

 @Override
 public boolean addAsset(Asset asset) {
     try {
         PreparedStatement statement = connection.prepareStatement("INSERT INTO assets (name, type, serial_number, purchase_date, location, status, owner_id) VALUES (?, ?, ?, ?, ?, ?, ?)");
         statement.setString(1, asset.getName());
         statement.setString(2, asset.getType());
         statement.setString(3, asset.getSerialNumber());
         statement.setString(4, asset.getPurchaseDate());
         statement.setString(5, asset.getLocation());
         statement.setString(6, asset.getStatus());
         statement.setInt(7, asset.getOwnerId());
         statement.executeUpdate();
         return true;
     } catch (SQLException e) {
         System.out.println("Error adding asset: " + e.getMessage());
         return false;
     }
 }

 @Override
 public boolean updateAsset(Asset asset) {
     try {
         PreparedStatement statement = connection.prepareStatement("UPDATE assets SET name = ?, type = ?, serial_number = ?, purchase_date = ?, location = ?, status = ?, owner_id = ? WHERE asset_id = ?");
         statement.setString(1, asset.getName());
         statement.setString(2, asset.getType());
         statement.setString(3, asset.getSerialNumber());
         statement.setString(4, asset.getPurchaseDate());
         statement.setString(5, asset.getLocation());
         statement.setString(6, asset.getStatus());
         statement.setInt(7, asset.getOwnerId());
         statement.setInt(8, asset.getAssetId());
         statement.executeUpdate();
         return true;
     } catch (SQLException e) {
         System.out.println("Error updating asset: " + e.getMessage());
         return false;
     }
 }

 @Override
 public boolean deleteAsset(int assetId) {
     try {
         PreparedStatement statement = connection.prepareStatement("DELETE FROM assets WHERE asset_id = ?");
         statement.setInt(1, assetId);
         statement.executeUpdate();
         return true;
     } catch (SQLException e) {
         System.out.println("Error deleting asset: " + e.getMessage());
         return false;
     }
 }

 @Override
 public boolean allocateAsset(int assetId, int userId, String allocationDate) {
     try {
         PreparedStatement statement = connection.prepareStatement("INSERT INTO asset_allocations (asset_id, user_id, allocation_date) VALUES (?, ?, ?)");
         statement.setInt(1, assetId);
         statement.setInt(2, userId);
         statement.setString(3, allocationDate);
         statement.executeUpdate();
         return true;
     } catch (SQLException e) {
         System.out.println("Error allocating asset: " + e.getMessage());
         return false;
     }
 }

 @Override
 public boolean deallocateAsset(int assetId, int userId, String returnDate) {
     try {
         PreparedStatement statement = connection.prepareStatement("UPDATE asset_allocations SET return_date = ? WHERE asset_id = ? AND user_id = ?");
         statement.setString (1, returnDate);
         statement.setInt(2, assetId);
         statement.setInt(3, userId);
         statement.executeUpdate();
         return true;
     } catch (SQLException e) {
         System.out.println("Error deallocating asset: " + e.getMessage());
         return false;
     }
 }

 @Override
 public boolean performMaintenance(int assetId, String maintenanceDate, String description, double cost) {
     try {
         PreparedStatement statement = connection.prepareStatement("INSERT INTO maintenance_records (asset_id, maintenance_date, description, cost) VALUES (?, ?, ?, ?)");
         statement.setInt(1, assetId);
         statement.setString(2, maintenanceDate);
         statement.setString(3, description);
         statement.setDouble(4, cost);
         statement.executeUpdate();
         return true;
     } catch (SQLException e) {
         System.out.println("Error performing maintenance: " + e.getMessage());
         return false;
     }
 }

 @Override
 public boolean reserveAsset(int assetId, int userId,String reservationDate, String status) {
     try {
         PreparedStatement statement = connection.prepareStatement("INSERT INTO reservations (asset_id, user_id, reservation_date, status) VALUES (?, ?, ?, ?)");
         statement.setInt(1, assetId);
         statement.setInt(2, userId);
         statement.setString(3, reservationDate);
		 statement.setString(4, status);
         statement.executeUpdate();
         return true;
     } catch (SQLException e) {
         System.out.println("Error reserving asset: " + e.getMessage());
         return false;
     }
 }

 @Override
 public boolean withdrawReservation(int assetId, int userId, String withdrawalDate) {
     try {
         PreparedStatement statement = connection.prepareStatement("UPDATE reservations SET withdrawal_date = ? WHERE asset_id = ? AND user_id = ?");
         statement.setString(1, withdrawalDate);
         statement.setInt(2, assetId);
         statement.setInt(3, userId);
         statement.executeUpdate();
         return true;
     } catch (SQLException e) {
         System.out.println("Error withdrawing reservation: " + e.getMessage());
         return false;
     }
 }
 
 @Override
 public List<Asset> getAssets() throws SQLException {
     List<Asset> assets = new ArrayList<>();
     PreparedStatement statement = connection.prepareStatement("SELECT * FROM assets");
     ResultSet resultSet = statement.executeQuery();
     while (resultSet.next()) {
         Asset asset = new Asset();
         asset.setAssetId(resultSet.getInt("asset_id"));
         asset.setName(resultSet.getString("name"));
         asset.setType(resultSet.getString("type"));
         asset.setSerialNumber(resultSet.getString("serial_number"));
         asset.setPurchaseDate(resultSet.getString("purchase_date"));
         asset.setLocation(resultSet.getString("location"));
         asset.setStatus(resultSet.getString("status"));
         asset.setOwnerId(resultSet.getInt("owner_id"));
         assets.add(asset);
     }
     return assets;
 }

 @Override
 public List<Reservation> getReservations() throws SQLException {
     List<Reservation> reservations = new ArrayList<>();
     PreparedStatement statement = connection.prepareStatement("SELECT * FROM reservations");
     ResultSet resultSet = statement.executeQuery();
     while (resultSet.next()) {
         Reservation reservation = new Reservation();
         reservation.setAssetId(resultSet.getInt("asset_id"));
         reservation.setUserId(resultSet.getInt("user_id"));
         reservation.setReservationDate(resultSet.getString("reservation_date"));
         reservation.setWithdrawalDate(resultSet.getString("withdrawal_date"));
         reservations.add(reservation);
     }
     return reservations;
 }
}
