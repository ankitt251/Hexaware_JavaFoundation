package com.hexaware.entity;

public class Reservation {
    private int reservationId;
    private int assetId;
    private int userId;
    private String reservationDate;
    private String withdrawalDate;
    private String status;

    // Default and parameterized constructors
    public Reservation() {}

    public Reservation(int reservationId, int assetId, int userId, String reservationDate,String withdrawalDate, String status) {
        this.reservationId = reservationId;
        this.assetId = assetId;
        this.userId = userId;
        this.reservationDate = reservationDate;
        this.withdrawalDate = withdrawalDate;
        this.status = status;
    }

    // Getters and setters
    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
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

    public String getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(String reservationDate) {
        this.reservationDate = reservationDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

	public String getWithdrawalDate() {
		return withdrawalDate;
	}

	public void setWithdrawalDate(String withdrawalDate) {
		this.withdrawalDate = withdrawalDate;
	}
    
}
