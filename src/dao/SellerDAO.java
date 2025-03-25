package dao;

import model.Seller;
import util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SellerDAO {

//    public void registerSeller(Seller seller) {
//        String sql = "INSERT INTO sellers (name, email, password) VALUES (?, ?, ?)";
//        try (Connection conn = DatabaseConnection.getConnection();
//             PreparedStatement stmt = conn.prepareStatement(sql)) {
//
//            stmt.setString(1, seller.getName());
//            stmt.setString(2, seller.getEmail());
//            stmt.setString(3, seller.getPassword());
//            stmt.executeUpdate();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    public Seller loginSeller(String idORemail, String password) {
        String sql="";
        if(idORemail.contains("@")) {
            sql = "SELECT * FROM sellers WHERE email = ? AND password = ?";
        }
        else{
            sql = "SELECT * FROM sellers WHERE id = ? AND password = ?";
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
                return new Seller(rs.getInt("id"), rs.getString("name"), rs.getString("email"), rs.getString("password"),"SELLER");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
