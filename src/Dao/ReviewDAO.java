/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

/**
 *
 * @author Nitro
 */
import java.sql.*;
import java.util.*;
import Model.Review;

public class ReviewDAO {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/restaurant";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "qwerty1234";

    public static List<Review> getRecentReviews(int limit) {
        List<Review> reviews = new ArrayList<>();
        String query = "SELECT customer_name, review_text FROM reviews ORDER BY id DESC LIMIT ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, limit);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                reviews.add(new Review(rs.getString("customer_name"), rs.getString("review_text")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reviews;
    }
}

