package dao;

import dto.Ingredient;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import utils.DBConnect;

/**
 *
 * @author hoang
 */
public class IngredientDAO {

    private static IngredientDAO instance;

    private IngredientDAO() {

    }

    public static IngredientDAO getInstance() {
        if (instance == null) {
            instance = new IngredientDAO();
        }
        return instance;
    }

    public Ingredient findById(int id) {
        Connection conn = null;
        try {
            conn = DBConnect.getConnection();
            if (conn != null) {
                String sql = "SELECT * FROM [Ingredient] WHERE Id = ?";
                PreparedStatement stm = conn.prepareStatement(sql);
                stm.setInt(1, id);
                ResultSet rs = stm.executeQuery();
                if (rs != null && rs.next()) {
                    return new Ingredient(
                            rs.getInt("Id"),
                            rs.getNString("Name"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public List<Ingredient> findAll() {
        Connection conn = null;
        List<Ingredient> ings = new ArrayList<>();
        try {
            conn = DBConnect.getConnection();
            if (conn != null) {
                String sql = "SELECT * FROM [Ingredient]";
                PreparedStatement stm = conn.prepareStatement(sql);
                ResultSet rs = stm.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        ings.add(new Ingredient(
                                rs.getInt("Id"),
                                rs.getNString("Name")));
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
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ings;
    }
}
