package test;

import com.hexaware.dao.AssetManagementService;
import com.hexaware.dao.AssetManagementServiceImpl;
import com.hexaware.entity.Asset;
import com.hexaware.entity.Reservation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class AssetManagementServiceTest {

    private AssetManagementService assetManagementService;

    @BeforeEach
    public void setup() {
        assetManagementService = new AssetManagementServiceImpl();
    }

    @Test
    public void testAddAssets() {
        Asset asset1 = new Asset(2, "High-Speed Printer", "Printers", "SN54321", "2023-06-15", "Warehouse", "is use", 1);
        Asset asset2 = new Asset(3, "Laptop", "Electronics", "SN123XYZ", "2023-06-10", "Office", "Available", 1);

        boolean result1 = assetManagementService.addAsset(asset1);
        boolean result2 = assetManagementService.addAsset(asset2);

        assertTrue(result1);
        assertTrue(result2);
    }

    @Test
    public void testUpdateAssets() {
        Asset asset1 = new Asset(2, "Updated High-Speed Printer", "Printers", "SN54321", "2023-06-15", "Warehouse", "is use", 1);
        Asset asset2 = new Asset(3, "Updated Laptop", "Electronics", "SN123XYZ", "2023-06-10", "Office", "Available", 2);

        boolean result1 = assetManagementService.updateAsset(asset1);
        boolean result2 = assetManagementService.updateAsset(asset2);

        assertTrue(result1);
        assertTrue(result2);
    }

    @Test
    public void testDeleteAsset() {
        int assetId = 1;
        boolean result = assetManagementService.deleteAsset(assetId);
        assertTrue(result);
    }

    @Test
    public void testGetAssets() throws SQLException {
        List<Asset> assets = assetManagementService.getAssets();
        assertFalse(assets.isEmpty());
    }

    @Test
    public void testReserveAssets() {
        boolean result1 = assetManagementService.reserveAsset(2, 2, "2024-10-10", "Reserved");
        boolean result2 = assetManagementService.reserveAsset(3, 2, "2024-10-10", "Reserved");

        assertTrue(result1);
        assertTrue(result2);
    }

    @Test
    public void testWithdrawReservation() {
        int assetId = 1;
        int userId = 1;
        String withdrawalDate = "2022-01-02";

        boolean result = assetManagementService.withdrawReservation(assetId, userId, withdrawalDate);
        assertTrue(result);
    }

    @Test
    public void testGetReservations() throws SQLException {
        List<Reservation> reservations = assetManagementService.getReservations();
        assertFalse(reservations.isEmpty());
    }
}