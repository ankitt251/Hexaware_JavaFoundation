package com.hexaware.dao;

import com.hexaware.entity.Asset;
import com.hexaware.util.DBConnection;
import com.hexaware.exceptions.AssetNotFoundException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AssetManagementServiceImpl implements AssetManagementService {

    private Connection connection;

    public AssetManagementServiceImpl() {
        this.connection = DBConnection.getConnection();
    }

    @Override
    public boolean addAsset(Asset asset) {
        String query = "INSERT INTO assets (name, type, serial_number, purchase_date, location, status, owner_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, asset.getName());
            preparedStatement.setString(2, asset.getType());
            preparedStatement.setString(3, asset.getSerialNumber());
            preparedStatement.setDate(4, new java.sql.Date(asset.getPurchaseDate().getTime())); // Convert Date
            preparedStatement.setString(5, asset.getLocation());
            preparedStatement.setString(6, asset.getStatus());
            preparedStatement.setInt(7, asset.getOwnerId());

            int rowsInserted = preparedStatement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public boolean updateAsset(Asset asset) {
        String query = "UPDATE assets SET name=?, type=?, serial_number=?, purchase_date=?, location=?, status=?, owner_id=? WHERE asset_id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, asset.getName());
            preparedStatement.setString(2, asset.getType());
            preparedStatement.setString(3, asset.getSerialNumber());
            preparedStatement.setDate(4, new java.sql.Date(asset.getPurchaseDate().getTime())); // Convert Date
            preparedStatement.setString(5, asset.getLocation());
            preparedStatement.setString(6, asset.getStatus());
            preparedStatement.setInt(7, asset.getOwnerId());
            preparedStatement.setInt(8, asset.getAssetId());

            int rowsUpdated = preparedStatement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public boolean deleteAsset(int assetId) throws AssetNotFoundException {
        String query = "DELETE FROM assets WHERE assetId=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, assetId);

            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted > 0) {
                return true;
            } else {
                throw new AssetNotFoundException("Asset with ID " + assetId + " not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean allocateAsset(int assetId, int employeeId, String allocationDate) {
        String query = "INSERT INTO asset_allocations (asset_id, employee_id, allocation_date) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, assetId);
            preparedStatement.setInt(2, employeeId);
            preparedStatement.setString(3, allocationDate);

            int rowsInserted = preparedStatement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public boolean deallocateAsset(int assetId, int employeeId, String returnDate) {
        String query = "UPDATE asset_allocations SET returnDate=? WHERE assetId=? AND employeeId=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, returnDate);
            preparedStatement.setInt(2, assetId);
            preparedStatement.setInt(3, employeeId);

            int rowsUpdated = preparedStatement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean performMaintenance(int assetId, String maintenanceDate, String description, double cost) {
        String query = "INSERT INTO maintenance_records (asset_id, maintenance_date, description, cost) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, assetId);
            preparedStatement.setString(2, maintenanceDate);
            preparedStatement.setString(3, description);
            preparedStatement.setDouble(4, cost);

            int rowsInserted = preparedStatement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public boolean reserveAsset(int assetId, int employeeId, String reservationDate, String startDate, String endDate) {
        String query = "INSERT INTO reservations (asset_id, employee_id, reservation_date, start_date, end_date) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, assetId);
            preparedStatement.setInt(2, employeeId);
            preparedStatement.setString(3, reservationDate);
            preparedStatement.setString(4, startDate);
            preparedStatement.setString(5, endDate);

            int rowsInserted = preparedStatement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public boolean withdrawReservation(int reservationId) {
        String query = "DELETE FROM reservations WHERE reservation_id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, reservationId);

            int rowsDeleted = preparedStatement.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
