package com.hexaware.dao;

import java.sql.SQLException;
import java.util.List;

import com.hexaware.entity.Asset;
import com.hexaware.entity.Reservation;

public interface AssetManagementService {
 boolean addAsset(Asset asset);
 boolean updateAsset(Asset asset);
 boolean deleteAsset(int assetId);
 boolean allocateAsset(int assetId, int userId, String allocationDate);
 boolean deallocateAsset(int assetId, int userId, String returnDate);
 boolean reserveAsset(int assetId, int userId,String reservationDate, String status);
 boolean performMaintenance(int assetId, String maintenanceDate, String description, double cost);
 boolean withdrawReservation(int assetId, int userId, String withdrawalDate);
 List<Asset> getAssets() throws SQLException;
 List<Reservation> getReservations() throws SQLException;
}
