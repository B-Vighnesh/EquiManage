package dao;

import model.Equipments;
import util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EquipmentDAO {

    public void addEquipment(Equipments equipment) {
        String sql = "INSERT INTO equipments (name, type) VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, equipment.getName());
            stmt.setString(2, equipment.getType());


            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Equipment added successfully.");
            } else {
                System.out.println("Failed to add equipment.");
            }

        } catch (SQLException e) {
            System.out.println("Error while adding equipment: " + e.getMessage());
        }
    }

    public void viewEquipments() {
        List<Equipments> equipmentList = new ArrayList<>();
        String sql = "SELECT * FROM equipments";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                equipmentList.add(new Equipments(
                        rs.getInt("equipment_id"),
                        rs.getString("name"),
                        rs.getString("type"),
                        rs.getBoolean("isAvailable")

                ));
            }

            if (equipmentList.isEmpty()) {
                System.out.println("Nothing to display.");
            } else {
                for (Equipments equipment : equipmentList) {
                    System.out.println(equipment);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error while fetching equipments: " + e.getMessage());
        }
    }

    public void removeEquipment(int equipmentId) {
        String sql = "DELETE FROM equipments WHERE equipment_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, equipmentId);

            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Equipment removed successfully.");
            } else {
                System.out.println("No equipment found with ID: " + equipmentId);
            }

        } catch (SQLException e) {
            System.out.println("Error while removing equipment: " + e.getMessage());
        }
    }

    public void viewAvailableEquipments() {
        List<Equipments> equipmentList = new ArrayList<>();
        String sql = "SELECT * FROM equipments where isAvailable=true";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                equipmentList.add(new Equipments(
                        rs.getInt("equipment_id"),
                        rs.getString("name"),
                        rs.getString("type"),
                        rs.getBoolean("isAvailable")

                ));
            }

            if (equipmentList.isEmpty()) {
                System.out.println("Nothing to display.");
            } else {
                for (Equipments equipment : equipmentList) {
                    System.out.println(equipment);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error while fetching equipments: " + e.getMessage());
        }
    }

    public void viewIssuedEquipments() {
        List<Equipments> equipmentList = new ArrayList<>();
        String sql = "SELECT * FROM equipments where isAvailable=false";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                equipmentList.add(new Equipments(
                        rs.getInt("equipment_id"),
                        rs.getString("name"),
                        rs.getString("type"),
                        rs.getBoolean("isAvailable")

                ));
            }

            if (equipmentList.isEmpty()) {
                System.out.println("Nothing to display.");
            } else {
                for (Equipments equipment : equipmentList) {
                    System.out.println(equipment);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error while fetching equipments: " + e.getMessage());
        }
    }


    public Boolean isAvailable(int equipmentId) {
        boolean isAvailable = false;
        String sql = "Select isAvailable from equipments where equipment_id=" + equipmentId;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                isAvailable = rs.getBoolean("isavailable");
            }
            return isAvailable;

        } catch (SQLException e) {
            System.out.println("Error while fetching equipments: " + e.getMessage());
        }
        return isAvailable;
    }

    public void rentEquipment(int issueBuyerId, int issueEquipmentId, String token) {
        if (isAvailable(issueEquipmentId)) {
            String sql = "INSERT INTO IssueList (buyer_id, equipment_id, token) VALUES (?, ?, ?)";

            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setInt(1, issueBuyerId);
                stmt.setInt(2, issueEquipmentId);
                stmt.setString(3, token);

                int rowsInserted = stmt.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("Equipment rented successfully.");
                } else {
                    System.out.println("Failed to rent equipment.");
                }

            } catch (SQLException e) {
                System.out.println("Error while renting equipment: " + e.getMessage());
            }
        } else {
            System.out.println("Item is not available");
        }
    }

    public int isValidToken( int returnEquipmentId, String token) {
        String sql = "select issue_id from issuelist where return_date is null and equipment_id=? and token=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, returnEquipmentId);
            stmt.setString(2, token);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                return 0;
            }
        } catch (SQLException e) {
            System.out.println("Error while returning equipment: " + e.getMessage());
            return 0;
        }
    }

    public void returnEquipment(int returnBuyerId, int returnEquipmentId, String token) {
        int issueId = isValidToken(returnEquipmentId, token);

        if (issueId != 0) {
            String sql = "UPDATE issuelist SET return_date = NOW() WHERE issue_id = ?";
            String updateEquipment = "UPDATE equipments SET isAvailable = TRUE WHERE equipment_id = ?";

            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql);
                 PreparedStatement stmtEquip = conn.prepareStatement(updateEquipment)) {

                stmt.setInt(1, issueId);
                int rowsUpdated = stmt.executeUpdate();

                if (rowsUpdated > 0) {
                    stmtEquip.setInt(1, returnEquipmentId);
                    stmtEquip.executeUpdate();
                    System.out.println("Equipment returned successfully.");
                } else {
                    System.out.println("Failed to return equipment.");
                }

            } catch (SQLException e) {
                System.out.println("Error while returning equipment: " + e.getMessage());
            }
        } else {
            System.out.println("Invalid token or equipment ID.");
        }
    }

    public void getReport() {
        String sql = "SELECT issue_id, buyer_id, equipment_id, token, issue_date, return_date FROM Issuelist";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet resultSet = stmt.executeQuery()) {

            System.out.println("-----------------------------------------------------------------------------------");
            System.out.println("| Issue ID | Buyer ID | Equipment ID | Token  | Issue Date       | Return Date    |");
            System.out.println("-----------------------------------------------------------------------------------");

            while (resultSet.next()) {
                int issueId = resultSet.getInt("issue_id");
                int buyerId = resultSet.getInt("buyer_id");
                int equipmentId = resultSet.getInt("equipment_id");
                String token = resultSet.getString("token");
                String issueDate = resultSet.getTimestamp("issue_date").toString();
                String returnDate = resultSet.getTimestamp("return_date") != null ? resultSet.getTimestamp("return_date").toString() : "Not Returned     ";

                System.out.printf("| %-8d | %-8d | %-12d | %-6s | %-18s | %-15s |\n",
                        issueId, buyerId, equipmentId, token, issueDate, returnDate);
            }

            System.out.println("-----------------------------------------------------------");

        } catch (SQLException e) {
            System.out.println("Error while fetching report: " + e.getMessage());
        }
    }
    public void getMyReport(int buyerId) {
        String sql = "SELECT i.issue_id, e.equipment_id, e.name AS equipment_name, e.type, i.token, i.issue_date, i.return_date " +
                "FROM buyers b " +
                "INNER JOIN issuelist i ON b.buyer_id = i.buyer_id " +
                "INNER JOIN equipments e ON i.equipment_id = e.equipment_id " +
                "WHERE b.buyer_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, buyerId);
            ResultSet resultSet = stmt.executeQuery();

            System.out.println("-------------------------------------------------------------------------------------------------------------");
            System.out.println("| Issue ID | Equipment ID | Equipment Name     | Type         | Token  | Issue Date       | Return Date    |");
            System.out.println("-------------------------------------------------------------------------------------------------------------");

            boolean hasRecords = false;
            while (resultSet.next()) {
                hasRecords = true;
                int issueId = resultSet.getInt("issue_id");
                int equipmentId = resultSet.getInt("equipment_id");
                String equipmentName = resultSet.getString("equipment_name");
                String equipmentType = resultSet.getString("type");
                String token = resultSet.getString("token");
                String issueDate = resultSet.getTimestamp("issue_date").toString();
                String returnDate = resultSet.getTimestamp("return_date") != null ? resultSet.getTimestamp("return_date").toString() : "Not Returned";

                System.out.printf("| %-8d | %-12d | %-18s | %-12s | %-6s | %-18s | %-15s |\n",
                        issueId, equipmentId, equipmentName, equipmentType, token, issueDate, returnDate);
            }

            if (!hasRecords) {
                System.out.println("| No records found  |");
            }
            System.out.println("-------------------------------------------------------------------------------------------------------------");


        } catch (SQLException e) {
            System.out.println("Error while fetching report: " + e.getMessage());
        }
    }

    public void viewEquipmentIssuedToBuyer(int id) {
        String sql = "SELECT i.issue_id, e.equipment_id, e.name AS equipment_name, e.type, i.token, i.issue_date " +
                "FROM buyers b " +
                "INNER JOIN issuelist i ON b.buyer_id = i.buyer_id " +
                "INNER JOIN equipments e ON i.equipment_id = e.equipment_id " +
                "WHERE b.buyer_id = ? and e.isavailable=false";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet resultSet = stmt.executeQuery();

            System.out.println("-----------------------------------------------------------------------------------------");
            System.out.println("| Issue ID | Equipment ID | Equipment Name     | Type         | Token  | Issue Date         |");
            System.out.println("-----------------------------------------------------------------------------------------");

            boolean hasRecords = false;
            while (resultSet.next()) {
                hasRecords = true;
                int issueId = resultSet.getInt("issue_id");
                int equipmentId = resultSet.getInt("equipment_id");
                String equipmentName = resultSet.getString("equipment_name");
                String equipmentType = resultSet.getString("type");
                String token = resultSet.getString("token");
                String issueDate = resultSet.getTimestamp("issue_date").toString();

                System.out.printf("| %-8d | %-12d | %-18s | %-12s | %-6s | %-18s \n",
                        issueId, equipmentId, equipmentName, equipmentType, token, issueDate);
            }

            if (!hasRecords) {
                System.out.println("| No records found   |");
            }

            System.out.println("-----------------------------------------------------------------------------------------");

        } catch (SQLException e) {
            System.out.println("Error while fetching report: " + e.getMessage());
        }
    }
}

