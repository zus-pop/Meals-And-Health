package dao;

import dto.PersonalMealPlanDetail;
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
public class PersonalMealPlanDetailDAO {

    private static PersonalMealPlanDetailDAO instance;
    private final MealDAO mealDao;

    private PersonalMealPlanDetailDAO(MealDAO mealDao) {
        this.mealDao = mealDao;
    }

    public static PersonalMealPlanDetailDAO getInstance() {
        if (instance == null) {
            instance = new PersonalMealPlanDetailDAO(MealDAO.getInstance());
        }
        return instance;
    }
    
    public List<PersonalMealPlanDetail> getMealListById(int id) {
        List<PersonalMealPlanDetail> listDetail = new ArrayList<>();
        Connection conn = null;
        try {
            conn = DBConnect.getConnection();
            if (conn != null) {
                String sql = "SELECT * FROM [Personal Meal Plan Detail] WHERE PlanMealId = ?";
                PreparedStatement stm = conn.prepareStatement(sql);
                stm.setInt(1, id);
                ResultSet rs = stm.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        listDetail.add(new PersonalMealPlanDetail(
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
    
    public int persist(List<PersonalMealPlanDetail> details) {
        int result = 0;
        Connection conn = null;
        PreparedStatement stm;
        try {
            conn = DBConnect.getConnection();
            if (conn != null) {
                conn.setAutoCommit(false);
                for (PersonalMealPlanDetail detail : details) {
                    String sql = "INSERT INTO [Personal Meal Plan Detail] (PlanMealId, MealId, TypeId, DaysOfTheWeek) "
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
    
    public int removeByPersonalMealPlanId(int planMealId) {
        int result = 0;
        Connection conn = null;
        PreparedStatement stm;
        try {
            conn = DBConnect.getConnection();
            if (conn != null) {
                String sql = "DELETE FROM [Personal Meal Plan Detail] WHERE PlanMealId = ?";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, planMealId);
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
