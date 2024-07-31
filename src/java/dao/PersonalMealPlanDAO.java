package dao;

import dto.Meal;
import dto.PersonalMealPlan;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import utils.DBConnect;
import utils.Days;
import utils.MealType;

/**
 *
 * @author hoang
 */
public class PersonalMealPlanDAO {

    private static PersonalMealPlanDAO instance;
    private final UserAccountDAO userDao;
    private final PersonalMealPlanDetailDAO personalDetailDao;

    private PersonalMealPlanDAO(
            UserAccountDAO userDao,
            PersonalMealPlanDetailDAO personalDetailDao) {
        this.userDao = userDao;
        this.personalDetailDao = personalDetailDao;
    }

    public static PersonalMealPlanDAO getInstance() {
        if (instance == null) {
            instance = new PersonalMealPlanDAO(
                    UserAccountDAO.getInstance(),
                    PersonalMealPlanDetailDAO.getInstance());
        }
        return instance;
    }
    
    public PersonalMealPlan findById(int id) {
        Connection conn = null;
        try {
            conn = DBConnect.getConnection();
            if (conn != null) {
                String sql = "SELECT * FROM [Personal Meal Plan] WHERE Id = ?";
                PreparedStatement stm = conn.prepareStatement(sql);
                stm.setInt(1, id);
                ResultSet rs = stm.executeQuery();
                if (rs != null && rs.next()) {
                    PersonalMealPlan personalMeal = new PersonalMealPlan(
                            rs.getInt("Id"),
                            rs.getNString("Name"),
                            rs.getNString("Description"),
                            rs.getString("Image"),
                            initMealList());
                    Map<Days, Map<MealType, List<Meal>>> meals = personalMeal.getMeals();
                    personalDetailDao.getMealListById(personalMeal.getId())
                            .forEach(detail -> {
                                if ((detail.getDaysOfTheWeek() & Days.MONDAY.getValue()) > 0) {
                                    Map<MealType, List<Meal>> mondayMap = meals.get(Days.MONDAY);
                                    mondayMap.get(detail.getType()).add(detail.getMeal());
                                }
                                if ((detail.getDaysOfTheWeek() & Days.TUESDAY.getValue()) > 0) {
                                    Map<MealType, List<Meal>> tuesdayMap = meals.get(Days.TUESDAY);
                                    tuesdayMap.get(detail.getType()).add(detail.getMeal());
                                }
                                if ((detail.getDaysOfTheWeek() & Days.WEDNESDAY.getValue()) > 0) {
                                    Map<MealType, List<Meal>> wednesdayMap = meals.get(Days.WEDNESDAY);
                                    wednesdayMap.get(detail.getType()).add(detail.getMeal());
                                }
                                if ((detail.getDaysOfTheWeek() & Days.THURSDAY.getValue()) > 0) {
                                    Map<MealType, List<Meal>> thursdayMap = meals.get(Days.THURSDAY);
                                    thursdayMap.get(detail.getType()).add(detail.getMeal());
                                }
                                if ((detail.getDaysOfTheWeek() & Days.FRIDAY.getValue()) > 0) {
                                    Map<MealType, List<Meal>> fridayMap = meals.get(Days.FRIDAY);
                                    fridayMap.get(detail.getType()).add(detail.getMeal());
                                }
                                if ((detail.getDaysOfTheWeek() & Days.SATURDAY.getValue()) > 0) {
                                    Map<MealType, List<Meal>> saturdayMap = meals.get(Days.SATURDAY);
                                    saturdayMap.get(detail.getType()).add(detail.getMeal());
                                }
                            });
                    return personalMeal;
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

    public List<PersonalMealPlan> findByCustomerId(int customerId) {
        List<PersonalMealPlan> mealPlans = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm;
        ResultSet rs;
        try {
            conn = DBConnect.getConnection();
            if (conn != null) {
                String sql = "SELECT * "
                        + "FROM [Personal Meal Plan] "
                        + "WHERE CustomerId = ?";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, customerId);
                rs = stm.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        PersonalMealPlan mealPlan = new PersonalMealPlan(
                                rs.getInt("Id"),
                                rs.getNString("Name"),
                                rs.getNString("Description"),
                                rs.getString("Image"),
                                initMealList(),
                                userDao.findById(rs.getInt("CustomerId")));
                        Map<Days, Map<MealType, List<Meal>>> meals = mealPlan.getMeals();
                        personalDetailDao.getMealListById(mealPlan.getId())
                                .forEach(detail -> {
                                    if ((detail.getDaysOfTheWeek() & Days.MONDAY.getValue()) > 0) {
                                        Map<MealType, List<Meal>> mondayMap = meals.get(Days.MONDAY);
                                        mondayMap.get(detail.getType()).add(detail.getMeal());
                                    }
                                    if ((detail.getDaysOfTheWeek() & Days.TUESDAY.getValue()) > 0) {
                                        Map<MealType, List<Meal>> tuesdayMap = meals.get(Days.TUESDAY);
                                        tuesdayMap.get(detail.getType()).add(detail.getMeal());
                                    }
                                    if ((detail.getDaysOfTheWeek() & Days.WEDNESDAY.getValue()) > 0) {
                                        Map<MealType, List<Meal>> wednesdayMap = meals.get(Days.WEDNESDAY);
                                        wednesdayMap.get(detail.getType()).add(detail.getMeal());
                                    }
                                    if ((detail.getDaysOfTheWeek() & Days.THURSDAY.getValue()) > 0) {
                                        Map<MealType, List<Meal>> thursdayMap = meals.get(Days.THURSDAY);
                                        thursdayMap.get(detail.getType()).add(detail.getMeal());
                                    }
                                    if ((detail.getDaysOfTheWeek() & Days.FRIDAY.getValue()) > 0) {
                                        Map<MealType, List<Meal>> fridayMap = meals.get(Days.FRIDAY);
                                        fridayMap.get(detail.getType()).add(detail.getMeal());
                                    }
                                    if ((detail.getDaysOfTheWeek() & Days.SATURDAY.getValue()) > 0) {
                                        Map<MealType, List<Meal>> saturdayMap = meals.get(Days.SATURDAY);
                                        saturdayMap.get(detail.getType()).add(detail.getMeal());
                                    }
                                });
                        mealPlans.add(mealPlan);
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
        return mealPlans;
    }
    
    public List<PersonalMealPlan> findByName(String name) {
        List<PersonalMealPlan> personalMeals = new ArrayList<>();
        Connection conn = null;
        try {
            conn = DBConnect.getConnection();
            if (conn != null) {
                String sql = "SELECT * FROM [Personal Meal Plan] "
                        + "WHERE Name LIKE ?";
                PreparedStatement stm = conn.prepareStatement(sql);
                stm.setNString(1, "%" + name + "%");
                ResultSet rs = stm.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        personalMeals.add(new PersonalMealPlan(
                                rs.getInt("Id"), 
                                rs.getNString("Name"), 
                                rs.getNString("Description"), 
                                rs.getString("Image"))
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
        return personalMeals;
    }

    public int persist(String name, String description, String image, int customerId) {
        int result = 0;
        Connection conn = null;
        try {
            conn = DBConnect.getConnection();
            String sql = "INSERT INTO [Personal Meal Plan] (Name, [Description], [Image], CustomerId) VALUES (?, ?, ?, ?)";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setNString(1, name);
            stm.setNString(2, description);
            stm.setString(3, image);
            stm.setInt(4, customerId);
            result = stm.executeUpdate();
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

    public int getCurrentPersonalMealPlanId() {
        int id = -1;
        Connection conn = null;
        try {
            conn = DBConnect.getConnection();
            if (conn != null) {
                String sql = "SELECT IDENT_CURRENT('Personal Meal Plan') AS Id";
                PreparedStatement stm = conn.prepareStatement(sql);
                ResultSet rs = stm.executeQuery();
                if (rs != null & rs.next()) {
                    id = rs.getInt("Id");
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
        return id;
    }
    
    public int update(int personalMealId, String name, String des, String image) {
        int result = 0;
        Connection conn = null;
        try {
            conn = DBConnect.getConnection();
            if (conn != null) {
                String sql = "UPDATE [Personal Meal Plan] "
                        + "SET [Name] = ?, [Description] = ?, [Image] = ? "
                        + "WHERE Id = ?";
                PreparedStatement stm = conn.prepareStatement(sql);
                stm.setNString(1, name);
                stm.setNString(2, des);
                stm.setString(3, image);
                stm.setInt(4, personalMealId);
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
    
    public int update(int personalMealId, String name, String des) {
        int result = 0;
        Connection conn = null;
        try {
            conn = DBConnect.getConnection();
            if (conn != null) {
                String sql = "UPDATE [Personal Meal Plan] "
                        + "SET [Name] = ?, [Description] = ? "
                        + "WHERE Id = ?";
                PreparedStatement stm = conn.prepareStatement(sql);
                stm.setNString(1, name);
                stm.setNString(2, des);
                stm.setInt(3, personalMealId);
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
    
    public int remove(int id) {
        int result = 0;
        Connection conn = null;
        try {
            conn = DBConnect.getConnection();
            if (conn != null) {
                String sql = "DELETE FROM [Personal Meal Plan] "
                        + "WHERE Id = ?";
                PreparedStatement stm = conn.prepareStatement(sql);
                stm.setInt(1, id);
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

    public Map<Days, Map<MealType, List<Meal>>> initMealList() {
        Map<Days, Map<MealType, List<Meal>>> mealList = new EnumMap<>(Days.class);
        mealList.put(Days.MONDAY, new EnumMap<>(MealType.class));
        mealList.put(Days.TUESDAY, new EnumMap<>(MealType.class));
        mealList.put(Days.WEDNESDAY, new EnumMap<>(MealType.class));
        mealList.put(Days.THURSDAY, new EnumMap<>(MealType.class));
        mealList.put(Days.FRIDAY, new EnumMap<>(MealType.class));
        mealList.put(Days.SATURDAY, new EnumMap<>(MealType.class));

        mealList.forEach((day, meals) -> {
            for (MealType type : MealType.values()) {
                meals.putIfAbsent(type, new ArrayList<>());
            }
        });
        return mealList;
    }
}
