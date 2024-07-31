package dao;

import dto.Meal;
import dto.WeeklyMeal;
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
public class WeeklyMealDAO {

    private static WeeklyMealDAO instance;
    private final WeeklyMealDetailDAO weeklyDetailDao;

    private WeeklyMealDAO(WeeklyMealDetailDAO weeklyDetailDao) {
        this.weeklyDetailDao = weeklyDetailDao;
    }

    public static WeeklyMealDAO getInstance() {
        if (instance == null) {
            instance = new WeeklyMealDAO(WeeklyMealDetailDAO.getInstance());
        }
        return instance;
    }

    public List<WeeklyMeal> findAll() {
        List<WeeklyMeal> weeklyMeals = new ArrayList<>();
        Connection conn = null;
        try {
            conn = DBConnect.getConnection();
            if (conn != null) {
                String sql = "SELECT * FROM [Weekly Meal]";
                PreparedStatement stm = conn.prepareStatement(sql);
                ResultSet rs = stm.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        WeeklyMeal weeklyMeal = new WeeklyMeal(
                                rs.getInt("Id"),
                                rs.getNString("Name"),
                                rs.getNString("Description"),
                                rs.getString("Image"),
                                initMealList());
                        Map<Days, Map<MealType, List<Meal>>> meals = weeklyMeal.getMeals();
                        weeklyDetailDao.getMealListById(weeklyMeal.getId())
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
                        weeklyMeals.add(weeklyMeal);
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

        return weeklyMeals;
    }

    public WeeklyMeal findById(int id) {
        Connection conn = null;
        try {
            conn = DBConnect.getConnection();
            if (conn != null) {
                String sql = "SELECT * FROM [Weekly Meal] WHERE Id = ?";
                PreparedStatement stm = conn.prepareStatement(sql);
                stm.setInt(1, id);
                ResultSet rs = stm.executeQuery();
                if (rs != null && rs.next()) {
                    WeeklyMeal weeklyMeal = new WeeklyMeal(
                            rs.getInt("Id"),
                            rs.getNString("Name"),
                            rs.getNString("Description"),
                            rs.getString("Image"),
                            initMealList());
                    Map<Days, Map<MealType, List<Meal>>> meals = weeklyMeal.getMeals();
                    weeklyDetailDao.getMealListById(weeklyMeal.getId())
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
                    return weeklyMeal;
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

    public List<WeeklyMeal> findByName(String name) {
        List<WeeklyMeal> weeklys = new ArrayList<>();
        Connection conn = null;
        try {
            conn = DBConnect.getConnection();
            if (conn != null) {
                String sql = "SELECT * FROM [Weekly Meal] "
                        + "WHERE Name LIKE ?";
                PreparedStatement stm = conn.prepareStatement(sql);
                stm.setNString(1, "%" + name + "%");
                ResultSet rs = stm.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        weeklys.add(new WeeklyMeal(
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
        return weeklys;
    }
    
    public List<WeeklyMeal> findBySuggestedCookie(String cookie) {
        List<WeeklyMeal> weeklys = new ArrayList<>();
        Connection conn = null;
        try {
            conn = DBConnect.getConnection();
            if (conn != null) {
                String sql = "SELECT TOP 3 * FROM [Weekly Meal] "
                        + "WHERE Name LIKE ? OR [Description] LIKE ?";
                PreparedStatement stm = conn.prepareStatement(sql);
                stm.setNString(1, "%" + cookie + "%");
                stm.setNString(2, "%" + cookie + "%");
                ResultSet rs = stm.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        weeklys.add(new WeeklyMeal(
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
        return weeklys;
    }

    public int persist(String name, String description, String image) {
        int result = 0;
        Connection conn = null;
        try {
            conn = DBConnect.getConnection();
            String sql = "INSERT INTO [Weekly Meal] (Name, [Description], [Image]) VALUES (?, ?, ?)";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setNString(1, name);
            stm.setNString(2, description);
            stm.setString(3, image);
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

    public int update(int weeklyId, String name, String des, String image) {
        int result = 0;
        Connection conn = null;
        try {
            conn = DBConnect.getConnection();
            if (conn != null) {
                String sql = "UPDATE [Weekly Meal] "
                        + "SET [Name] = ?, [Description] = ?, [Image] = ? "
                        + "WHERE Id = ?";
                PreparedStatement stm = conn.prepareStatement(sql);
                stm.setNString(1, name);
                stm.setNString(2, des);
                stm.setString(3, image);
                stm.setInt(4, weeklyId);
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

    public int update(int weeklyId, String name, String des) {
        int result = 0;
        Connection conn = null;
        try {
            conn = DBConnect.getConnection();
            if (conn != null) {
                String sql = "UPDATE [Weekly Meal] "
                        + "SET [Name] = ?, [Description] = ? "
                        + "WHERE Id = ?";
                PreparedStatement stm = conn.prepareStatement(sql);
                stm.setNString(1, name);
                stm.setNString(2, des);
                stm.setInt(3, weeklyId);
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

    public int getCurrentWeeklyId() {
        int id = -1;
        Connection conn = null;
        try {
            conn = DBConnect.getConnection();
            if (conn != null) {
                String sql = "SELECT IDENT_CURRENT('Weekly Meal') AS Id";
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

    public int remove(int id) {
        int result = 0;
        Connection conn = null;
        try {
            conn = DBConnect.getConnection();
            if (conn != null) {
                String sql = "DELETE FROM [Weekly Meal] "
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
