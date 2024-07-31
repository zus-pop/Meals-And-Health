package dao;

import dto.Unit;
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
public class UnitDAO {
    private static UnitDAO instance;

    private UnitDAO() {

    }

    public static UnitDAO getInstance() {
        if (instance == null) {
            instance = new UnitDAO();
        }
        return instance;
    }
    
    public Unit findById(int id) {
        Connection conn = null;
        try {
            conn = DBConnect.getConnection();
            if (conn != null) {
                String sql = "SELECT * FROM [Unit] WHERE Id = ?";
                PreparedStatement stm = conn.prepareStatement(sql);
                stm.setInt(1, id);
                ResultSet rs = stm.executeQuery();
                if (rs != null && rs.next()) {
                    return new Unit(
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
    
    public List<Unit> findAll() {
        Connection conn = null;
        List<Unit> units = new ArrayList<>();
        try {
            conn = DBConnect.getConnection();
            if (conn != null) {
                String sql = "SELECT * FROM [Unit]";
                PreparedStatement stm = conn.prepareStatement(sql);
                ResultSet rs = stm.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        units.add(new Unit(
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
        return units;
    }
}
