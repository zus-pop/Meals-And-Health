package dao;

import dto.Meal;
import dto.Order;
import dto.UserAccount;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import utils.Address;
import utils.DBConnect;
import utils.OrderStatus;
import utils.ShipCompany;
import utils.Util;

/**
 *
 * @author hoang
 */
public class OrderDAO {

    private static OrderDAO instance;
    private final OrderDetailDAO orderDetailDao;
    private final UserAccountDAO userDao;

    private OrderDAO(UserAccountDAO userDao, OrderDetailDAO orderDetailDao) {
        this.userDao = userDao;
        this.orderDetailDao = orderDetailDao;
    }

    public static OrderDAO getInstance() {
        if (instance == null) {
            instance = new OrderDAO(
                    UserAccountDAO.getInstance(),
                    OrderDetailDAO.getInstance());
        }
        return instance;
    }

    public List<Order> findAll() {
        Connection conn = null;
        List<Order> orders = new ArrayList<>();
        try {
            conn = DBConnect.getConnection();
            if (conn != null) {
                String sql = "SELECT * FROM [Order]";
                PreparedStatement stm = conn.prepareStatement(sql);
                ResultSet rs = stm.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        orders.add(new Order(
                                rs.getInt("Id"),
                                userDao.findById(rs.getInt("CustomerId")),
                                rs.getDate("OrderDate"),
                                ShipCompany.getShipper(rs.getInt("ShipVia")),
                                new Address(rs.getNString("Street"), rs.getNString("Ward"), rs.getNString("District"), rs.getNString("City")),
                                rs.getDouble("Total"),
                                OrderStatus.getStatus(rs.getInt("StatusId")),
                                orderDetailDao.findByOrderId(rs.getInt("Id")))
                        );
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
        return orders;
    }

    public Order findById(int id) {
        Connection conn = null;
        try {
            conn = DBConnect.getConnection();
            if (conn != null) {
                String sql = "SELECT * FROM [Order] WHERE Id = ?";
                PreparedStatement stm = conn.prepareStatement(sql);
                stm.setInt(1, id);
                ResultSet rs = stm.executeQuery();
                if (rs != null && rs.next()) {
                    return new Order(
                            rs.getInt("Id"),
                            userDao.findById(rs.getInt("CustomerId")),
                            rs.getDate("OrderDate"),
                            ShipCompany.getShipper(rs.getInt("ShipVia")),
                            new Address(rs.getNString("Street"), rs.getNString("Ward"), rs.getNString("District"), rs.getNString("City")),
                            rs.getDouble("Total"),
                            OrderStatus.getStatus(rs.getInt("StatusId")),
                            orderDetailDao.findByOrderId(rs.getInt("Id"))
                    );
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

    public List<Order> findByCustomerId(int customerId) {
        Connection conn = null;
        List<Order> orders = new ArrayList<>();
        try {
            conn = DBConnect.getConnection();
            if (conn != null) {
                String sql = "SELECT * FROM [Order] WHERE CustomerId = ? ORDER BY OrderDate DESC";
                PreparedStatement stm = conn.prepareStatement(sql);
                stm.setInt(1, customerId);
                ResultSet rs = stm.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        orders.add(new Order(
                                rs.getInt("Id"),
                                userDao.findById(rs.getInt("CustomerId")),
                                rs.getDate("OrderDate"),
                                ShipCompany.getShipper(rs.getInt("ShipVia")),
                                new Address(rs.getNString("Street"), rs.getNString("Ward"), rs.getNString("District"), rs.getNString("City")),
                                rs.getDouble("Total"),
                                OrderStatus.getStatus(rs.getInt("StatusId")),
                                orderDetailDao.findByOrderId(rs.getInt("Id")))
                        );
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
        return orders;
    }

    public int placeOrder(UserAccount customer, int shipperId, Address deliveryAddress, Map<Meal, Integer> cart) {
        int result = 0;
        Connection conn = null;
        try {
            conn = DBConnect.getConnection();
            if (conn != null) {
                conn.setAutoCommit(false);
                String sql = "INSERT INTO [Order] (CustomerId, OrderDate, ShipVia, Street, Ward, District, City, Total, StatusId) "
                        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement stm = conn.prepareStatement(sql);
                stm.setInt(1, customer.getId());
                stm.setDate(2, new Date(System.currentTimeMillis()));
                stm.setInt(3, shipperId);
                stm.setNString(4, deliveryAddress.getStreet());
                stm.setNString(5, deliveryAddress.getWard());
                stm.setNString(6, deliveryAddress.getDistrict());
                stm.setNString(7, deliveryAddress.getCity());
                stm.setDouble(8, Util.getTotalCost(cart));
                stm.setInt(9, OrderStatus.PENDING.getId());
                result = stm.executeUpdate();
                if (result >= 1) {
                    sql = "SELECT TOP 1 Id FROM [Order] ORDER BY Id DESC";
                    stm = conn.prepareStatement(sql);
                    ResultSet rs = stm.executeQuery();
                    System.out.println("here");
                    if (rs != null && rs.next()) {
                        int orderId = rs.getInt("Id");
                        for (Entry<Meal, Integer> entry : cart.entrySet()) {
                            sql = "INSERT INTO OrderDetail (OrderId, MealId, UnitPrice, Quantity) "
                                    + "VALUES (?, ?, ?, ?)";
                            stm = conn.prepareStatement(sql);
                            stm.setInt(1, orderId);
                            stm.setInt(2, entry.getKey().getId());
                            stm.setDouble(3, entry.getKey().getPrice());
                            stm.setInt(4, entry.getValue());
                            result = stm.executeUpdate();
                        }
                        conn.commit();
                    }
                }
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

    public int updateStatus(int id, int status) {
        int result = 0;
        Connection conn = null;
        try {
            conn = DBConnect.getConnection();
            if (conn != null) {
                String sql = "UPDATE [Order]\n"
                        + "SET StatusId = ?\n"
                        + "WHERE Id = ?";
                PreparedStatement stm = conn.prepareStatement(sql);
                stm.setInt(1, OrderStatus.getStatus(status).getId());
                stm.setInt(2, id);
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

    public List<Order> findByAddress(String city, String district, String ward) {
        Connection conn = null;
        List<Order> orders = new ArrayList<>();
        try {
            conn = DBConnect.getConnection();
            if (conn != null) {
                String sql = "SELECT *\n"
                        + "FROM dbo.[Order]\n"
                        + "WHERE (City LIKE ? AND District LIKE ? AND Ward LIKE ?)";
                PreparedStatement stm = conn.prepareStatement(sql);
                stm.setNString(1, "%" + city + "%");
                stm.setNString(2, "%" + district + "%");
                stm.setNString(3, "%" + ward + "%");
                ResultSet rs = stm.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        orders.add(new Order(
                                rs.getInt("Id"),
                                userDao.findById(rs.getInt("CustomerId")),
                                rs.getDate("OrderDate"),
                                ShipCompany.getShipper(rs.getInt("ShipVia")),
                                new Address(rs.getNString("Street"), rs.getNString("Ward"), rs.getNString("District"), rs.getNString("City")),
                                rs.getDouble("Total"),
                                OrderStatus.getStatus(rs.getInt("StatusId")),
                                orderDetailDao.findByOrderId(rs.getInt("Id")))
                        );
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
        return orders;
    }

    public List<Order> findByDate(Date startDate, Date endDate) {
        Connection conn = null;
        List<Order> orders = new ArrayList<>();
        try {
            conn = DBConnect.getConnection();
            if (conn != null) {
                String sql = "SELECT *\n"
                        + "FROM dbo.[Order] o \n"
                        + "INNER JOIN dbo.UserAccount u \n"
                        + "ON o.CustomerId=u.Id\n"
                        + "WHERE o.OrderDate BETWEEN ? AND ? \n";
                PreparedStatement stm = conn.prepareStatement(sql);
                stm.setDate(1, startDate);
                stm.setDate(2, endDate);
                ResultSet rs = stm.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        orders.add(new Order(
                                rs.getInt("Id"),
                                userDao.findById(rs.getInt("CustomerId")),
                                rs.getDate("OrderDate"),
                                ShipCompany.getShipper(rs.getInt("ShipVia")),
                                new Address(rs.getNString("Street"), rs.getNString("Ward"), rs.getNString("District"), rs.getNString("City")),
                                rs.getDouble("Total"),
                                OrderStatus.getStatus(rs.getInt("StatusId")),
                                orderDetailDao.findByOrderId(rs.getInt("Id")))
                        );
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
        return orders;
    }

    public List<Order> findByDateContact(Date startDate, Date endDate, String contact) {
        Connection conn = null;
        List<Order> orders = new ArrayList<>();
        try {
            conn = DBConnect.getConnection();
            if (conn != null) {
                String sql = "SELECT *\n"
                        + "FROM dbo.[Order] o \n"
                        + "INNER JOIN dbo.UserAccount u \n"
                        + "ON o.CustomerId=u.Id\n"
                        + "WHERE o.OrderDate BETWEEN ? AND ? \n"
                        + "AND (u.Email = ? OR u.PhoneNumber = ?)";
                PreparedStatement stm = conn.prepareStatement(sql);
                stm.setDate(1, startDate);
                stm.setDate(2, endDate);
                stm.setString(3, contact);
                stm.setString(4, contact);
                ResultSet rs = stm.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        orders.add(new Order(
                                rs.getInt("Id"),
                                userDao.findById(rs.getInt("CustomerId")),
                                rs.getDate("OrderDate"),
                                ShipCompany.getShipper(rs.getInt("ShipVia")),
                                new Address(rs.getNString("Street"), rs.getNString("Ward"), rs.getNString("District"), rs.getNString("City")),
                                rs.getDouble("Total"),
                                OrderStatus.getStatus(rs.getInt("StatusId")),
                                orderDetailDao.findByOrderId(rs.getInt("Id")))
                        );
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
        return orders;
    }
}
