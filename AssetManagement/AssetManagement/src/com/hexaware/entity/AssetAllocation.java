package com.hexaware.entity;

public class AssetAllocation {
    private int allocationId;
    private int assetId;
    private int userId;
    private String allocationDate;
    private String returnDate;

    // Default and parameterized constructors
    public AssetAllocation() {}

    public AssetAllocation(int allocationId, int assetId, int userId, String allocationDate, String returnDate) {
        this.allocationId = allocationId;
        this.assetId = assetId;
        this.userId = userId;
        this.allocationDate = allocationDate;
        this.returnDate = returnDate;
    }

    // Getters and setters
    public int getAllocationId() {
        return allocationId;
    }

    public void setAllocationId(int allocationId) {
        this.allocationId = allocationId;
    }

    public int getAssetId() {
        return assetId;
    }

    public void setAssetId(int assetId) {
        this.assetId = assetId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getAllocationDate() {
        return allocationDate;
    }

    public void setAllocationDate(String allocationDate) {
        this.allocationDate = allocationDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }
}
