package dao;

import dto.UserAccount;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import utils.DBConnect;
import utils.Role;

/**
 *
 * @author hoang
 */
public class UserAccountDAO {
    private static UserAccountDAO instance;
    
    private UserAccountDAO() {
        
    }
    
    public static UserAccountDAO getInstance() {
        if (instance == null) {
            instance = new UserAccountDAO();
        }
        return instance;
    }
    
    public UserAccount findByEmailAndPassword(String email, String password) {
        Connection conn = null;
        try {
            conn = DBConnect.getConnection();
            if (conn != null) {
                String sql = "SELECT *\n"
                        + "FROM dbo.UserAccount\n"
                        + "WHERE Email = ? COLLATE Latin1_General_CS_AS AND Password = ? COLLATE Latin1_General_CS_AS AND isActive = 1";
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setString(1, email);
                pst.setString(2, password);
                ResultSet rs = pst.executeQuery();
                if (rs != null && rs.next()) {
                    return new UserAccount(
                            rs.getInt("Id"),
                            rs.getNString("Username"),
                            rs.getString("Password"),
                            rs.getString("PhoneNumber"),
                            rs.getString("Email"),
                            rs.getBoolean("IsActive"),
                            Role.getRole(rs.getInt("RoleId")));
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

    public int persist(String email, String username, String password) {
        int result = 0;
        Connection conn = null;
        try {
            conn = DBConnect.getConnection();
            if (conn != null) {
                String sql = "INSERT INTO dbo.UserAccount (Username, Password, Email, RoleId)\n"
                        + "VALUES (?, ?, ?, ?)";
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setString(1, username);
                pst.setNString(2, password);
                pst.setString(3, email);
                pst.setInt(4, Role.CUSTOMER.getId());
                result = pst.executeUpdate();
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

    public UserAccount findById(int id) {
        Connection conn = null;
        try {
            conn = DBConnect.getConnection();
            if (conn != null) {
                String sql = "SELECT *\n"
                        + "FROM dbo.UserAccount\n"
                        + "WHERE Id = ?";
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setInt(1, id);
                ResultSet rs = pst.executeQuery();
                if (rs != null && rs.next()) {
                    return new UserAccount(
                            rs.getInt("Id"),
                            rs.getNString("Username"),
                            rs.getString("Password"),
                            rs.getString("PhoneNumber"),
                            rs.getString("Email"),
                            rs.getBoolean("IsActive"),
                            Role.getRole(rs.getInt("RoleId")));
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
    
    public UserAccount findByEmail(String email) {
        Connection conn = null;
        try {
            conn = DBConnect.getConnection();
            if (conn != null) {
                String sql = "SELECT *\n"
                        + "FROM dbo.UserAccount\n"
                        + "WHERE Email = ?";
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setString(1, email);
                ResultSet rs = pst.executeQuery();
                if (rs != null && rs.next()) {
                    return new UserAccount(
                            rs.getInt("Id"),
                            rs.getNString("Username"),
                            rs.getString("Password"),
                            rs.getString("PhoneNumber"),
                            rs.getString("Email"),
                            rs.getBoolean("IsActive"),
                            Role.getRole(rs.getInt("RoleId")));
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
    
    public List<UserAccount> getAllUsers(String search) {
        List<UserAccount> users = new ArrayList<>();
        Connection conn = null;
        try {
            conn = DBConnect.getConnection();
            if (conn != null) {
                String sql = "SELECT *\n"
                        + "FROM dbo.UserAccount\n"
                        + "WHERE (Username LIKE ? OR Email = ?) AND IsActive = 1";
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setNString(1, "%" + search + "%");
                pst.setString(2, search);
                ResultSet rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        users.add(new UserAccount(
                                rs.getInt("Id"),
                                rs.getNString("Username"),
                                rs.getString("Password"),
                                rs.getString("PhoneNumber"),
                                rs.getString("Email"),
                                rs.getBoolean("IsActive"),
                                Role.getRole(rs.getInt("RoleId"))));
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
        return users;
    }
    
    public List<UserAccount> getAllUsersForBin() {
        List<UserAccount> users = new ArrayList<>();
        Connection conn = null;
        try {
            conn = DBConnect.getConnection();
            if (conn != null) {
                String sql = "SELECT *\n"
                        + "FROM dbo.UserAccount\n"
                        + "WHERE IsActive = 0";
                PreparedStatement stm = conn.prepareStatement(sql);
                ResultSet rs = stm.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        users.add(new UserAccount(
                                rs.getInt("Id"),
                                rs.getNString("Username"),
                                rs.getString("Password"),
                                rs.getString("PhoneNumber"),
                                rs.getString("Email"),
                                rs.getBoolean("IsActive"),
                                Role.getRole(rs.getInt("RoleId"))));
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
        return users;
    }
    
    public int update(UserAccount user, String name, String phone) {
        int result = 0;
        Connection conn = null;
        try {
            conn = DBConnect.getConnection();
            if (conn != null) {
                String sql = "UPDATE UserAccount\n"
                        + "SET Username = ?, PhoneNumber = ?\n"
                        + "WHERE Id = ?";
                PreparedStatement stm = conn.prepareStatement(sql);
                stm.setNString(1, name);
                stm.setString(2, phone);
                stm.setInt(3, user.getId());
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

    public int removeUserSoft(int id) {
        int result = 0;
        Connection conn = null;
        try {
            conn = DBConnect.getConnection();
            if (conn != null) {
                String sql = "UPDATE dbo.UserAccount\n"
                        + "SET IsActive = 0\n"
                        + "WHERE Id = ?";
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setInt(1, id);
                result = pst.executeUpdate();
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
    
     public int removeUserHard(int id) {
        int result = 0;
        Connection conn = null;
        try {
            conn = DBConnect.getConnection();
            if (conn != null) {
                String sql = "DELETE \n"
                        + "FROM dbo.UserAccount\n"
                        + "WHERE Id = ? ";
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setInt(1, id);
                result = pst.executeUpdate();
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

    public int restoreUser(int id) {
        int result = 0;
        Connection conn = null;
        try {
            conn = DBConnect.getConnection();
            if (conn != null) {
                String sql = "UPDATE dbo.UserAccount\n"
                        + "SET IsActive = 1\n"
                        + "WHERE Id = ?";
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setInt(1, id);
                result = pst.executeUpdate();
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
