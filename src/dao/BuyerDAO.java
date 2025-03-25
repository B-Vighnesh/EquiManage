package dao;

import model.Buyer;
import util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BuyerDAO {

    public void registerBuyer(Buyer buyer) {
        String sql = "INSERT INTO buyers (name, email, password ,role ) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, buyer.getName());
            stmt.setString(2, buyer.getEmail());
            stmt.setString(3, buyer.getPassword());
            stmt.setString(4,"buyer");
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Buyer loginBuyer(String idORemail, String password) {
        String sql="";
        if(idORemail.contains("@")) {
            sql = "SELECT * FROM buyers WHERE email = ? AND password = ?";
        }
        else{
            sql = "SELECT * FROM buyers WHERE buyer_id = ? AND password = ?";
        }
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            if(idORemail.contains("@")) {
                stmt.setString(1, idORemail);
            }
            else{
                stmt.setInt(1, Integer.parseInt(idORemail));
            }
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Buyer(rs.getInt("buyer_id"), rs.getString("name"), rs.getString("email"), rs.getString("password"),"BUYER");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}
