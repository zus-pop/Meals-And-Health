package dao;

import dto.WeeklyMealDetail;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import utils.DBConnect;
import utils.MealType;

/**
 *
 * @author hoang
 */
public class WeeklyMealDetailDAO {

    private static WeeklyMealDetailDAO instance;
    private final MealDAO mealDao;

    private WeeklyMealDetailDAO(MealDAO mealDao) {
        this.mealDao = mealDao;
    }

    public static WeeklyMealDetailDAO getInstance() {
        if (instance == null) {
            instance = new WeeklyMealDetailDAO(MealDAO.getInstance());
        }
        return instance;
    }

    public List<WeeklyMealDetail> getMealListById(int id) {
        List<WeeklyMealDetail> listDetail = new ArrayList<>();
        Connection conn = null;
        try {
            conn = DBConnect.getConnection();
            if (conn != null) {
                String sql = "SELECT * FROM [Weekly Meal Detail] WHERE PlanMealId = ?";
                PreparedStatement stm = conn.prepareStatement(sql);
                stm.setInt(1, id);
                ResultSet rs = stm.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        listDetail.add(new WeeklyMealDetail(
                                rs.getInt("Id"),
                                rs.getInt("PlanMealId"),
                                mealDao.findById(rs.getInt("MealId")),
                                MealType.getType(rs.getInt("TypeId")),
                                rs.getInt("DaysOfTheWeek")));
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
        return listDetail;
    }

    public int persist(List<WeeklyMealDetail> details) {
        int result = 0;
        Connection conn = null;
        PreparedStatement stm;
        try {
            conn = DBConnect.getConnection();
            if (conn != null) {
                conn.setAutoCommit(false);
                for (WeeklyMealDetail detail : details) {
                    String sql = "INSERT INTO [Weekly Meal Detail] (PlanMealId, MealId, TypeId, DaysOfTheWeek) "
                            + "VALUES (?, ?, ?, ?)";
                    stm = conn.prepareStatement(sql);
                    stm.setInt(1, detail.getPlanMealId());
                    stm.setInt(2, detail.getMeal().getId());
                    stm.setInt(3, detail.getType().getId());
                    stm.setInt(4, detail.getDaysOfTheWeek());
                    result = stm.executeUpdate();
                }
                conn.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.setAutoCommit(true);
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return result;
    }
    
    public int removeByWeeklyId(int weeklyId) {
        int result = 0;
        Connection conn = null;
        PreparedStatement stm;
        try {
            conn = DBConnect.getConnection();
            if (conn != null) {
                String sql = "DELETE FROM [Weekly Meal Detail] WHERE PlanMealId = ?";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, weeklyId);
                result = stm.executeUpdate();
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
        return result;
    }
    
    
}
