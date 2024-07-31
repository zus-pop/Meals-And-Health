package dao;

import dto.Meal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.DBConnect;

/**
 *
 * @author hoang
 */
public class MealDAO {

    private static MealDAO instance;

    private MealDAO() {
    }

    public static MealDAO getInstance() {
        if (instance == null) {
            instance = new MealDAO();
        }
        return instance;
    }

    public List<Meal> findAll() {
        List<Meal> meals = new ArrayList<>();
        Connection conn = null;
        try {
            conn = DBConnect.getConnection();
            if (conn != null) {
                String sql = "SELECT * FROM Meal ORDER BY Name";
                Statement stm = conn.createStatement();
                ResultSet rs = stm.executeQuery(sql);
                if (rs != null) {
                    while (rs.next()) {
                        meals.add(
                                new Meal(
                                        rs.getInt("Id"),
                                        rs.getNString("Name"),
                                        rs.getNString("Description"),
                                        rs.getString("Image"),
                                        rs.getDouble("Price"),
                                        rs.getBoolean("IsAvailable")));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return meals;
    }

    public Meal findById(int id) {
        Connection conn = null;
        try {
            conn = DBConnect.getConnection();
            if (conn != null) {
                String sql = "SELECT TOP 6 * FROM Meal WHERE Id = ?";
                PreparedStatement stm = conn.prepareStatement(sql);
                stm.setInt(1, id);
                ResultSet rs = stm.executeQuery();
                if (rs != null && rs.next()) {
                    return new Meal(
                            rs.getInt("Id"),
                            rs.getNString("Name"),
                            rs.getNString("Description"),
                            rs.getString("Image"),
                            rs.getDouble("Price"),
                            rs.getBoolean("IsAvailable"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public List<Meal> findBySuggestedByCookie(String search) {
        List<Meal> meals = new ArrayList<>();
        Connection conn = null;
        try {
            conn = DBConnect.getConnection();
            if (conn != null) {
                String sql = "SELECT TOP 6 * FROM Meal WHERE Name LIKE ?";
                PreparedStatement stm = conn.prepareStatement(sql);
                stm.setNString(1, "%" + search + "%");
                ResultSet rs = stm.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        meals.add(
                                new Meal(
                                        rs.getInt("Id"),
                                        rs.getNString("Name"),
                                        rs.getNString("Description"),
                                        rs.getString("Image"),
                                        rs.getDouble("Price"),
                                        rs.getBoolean("IsAvailable")));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return meals;
    }
    
    public List<Meal> findByName(String name) {
        List<Meal> meals = new ArrayList<>();
        Connection conn = null;
        try {
            conn = DBConnect.getConnection();
            if (conn != null) {
                String sql = "SELECT *\n"
                        + "FROM dbo.Meal\n"
                        + "WHERE Name LIKE ?";
                PreparedStatement stm = conn.prepareStatement(sql);
                stm.setNString(1, "%" + name + "%");
                ResultSet rs = stm.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        meals.add(
                                new Meal(
                                        rs.getInt("Id"),
                                        rs.getNString("Name"),
                                        rs.getNString("Description"),
                                        rs.getString("Image"),
                                        rs.getDouble("Price"),
                                        rs.getBoolean("IsAvailable")));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return meals;
    }
}
