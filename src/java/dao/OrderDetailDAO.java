package dao;

import dto.OrderDetail;
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
public class OrderDetailDAO {
    private static OrderDetailDAO instance;
    private MealDAO mealDao;
    
    private OrderDetailDAO(MealDAO mealDao) {
        this.mealDao = mealDao;
    }
    
    public static OrderDetailDAO getInstance() {
        if (instance == null) {
            instance = new OrderDetailDAO(MealDAO.getInstance());
        }
        
        return instance;
    }
    
    
    public List<OrderDetail> findByOrderId(int orderId) {
        List<OrderDetail> orderDetails = new ArrayList<>();
        Connection conn = null;
        
        try {
            conn = DBConnect.getConnection();
            if (conn != null) {
                String sql = "SELECT * FROM OrderDetail "
                        + "WHERE OrderId = ?";
                PreparedStatement stm = conn.prepareStatement(sql);
                stm.setInt(1, orderId);
                ResultSet rs = stm.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        orderDetails.add(new OrderDetail(
                                mealDao.findById(rs.getInt("MealId")), 
                                rs.getDouble("UnitPrice"), 
                                rs.getInt("Quantity")));
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
        
        return orderDetails;
    }
}
