package dao;

import dto.MealIngredient;
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
public class MealIngredientDAO {

    private static MealIngredientDAO instance;
    private final IngredientDAO ingDao;
    private final UnitDAO unitDao;

    private MealIngredientDAO(IngredientDAO ingDao, UnitDAO unitDao) {
        this.ingDao = ingDao;
        this.unitDao = unitDao;
    }

    public static MealIngredientDAO getInstance() {
        if (instance == null) {
            instance = new MealIngredientDAO(
                    IngredientDAO.getInstance(),
                    UnitDAO.getInstance());
        }
        return instance;
    }

    public List<MealIngredient> findByMealId(int id) {
        List<MealIngredient> ingsOfMeal = new ArrayList<>();
        Connection conn = null;
        try {
            conn = DBConnect.getConnection();
            if (conn != null) {
                String sql = "SELECT * FROM [MealIngredient] "
                        + "WHERE [MealId] = ?";
                PreparedStatement stm = conn.prepareStatement(sql);
                stm.setInt(1, id);
                ResultSet rs = stm.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        ingsOfMeal.add(new MealIngredient(
                                ingDao.findById(rs.getInt("IngredientId")),
                                rs.getString("Quantity"),
                                unitDao.findById(rs.getInt("UnitId"))));
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
        return ingsOfMeal;
    }
}
