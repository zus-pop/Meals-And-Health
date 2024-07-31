package utils;

import dao.WeeklyMealDAO;
import dto.Meal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 *
 * @author hoang
 */
public enum MealType {
    BREAKFAST(1),
    LUNCH(2),
    DINNER(3),
    SNACK(4);

    private final int id;

    private MealType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static MealType getType(int id) {
        return Stream.of(MealType.values())
                .filter(type -> type.getId() == id)
                .findFirst()
                .orElse(null);
    }
}

class foo {

    public static void main(String[] args) {
        WeeklyMealDAO weeklyDao = WeeklyMealDAO.getInstance();
        Map<Days, Map<MealType, List<Meal>>> mealList = weeklyDao.initMealList();
        mealList.forEach((day, meals) -> {
            System.out.println(day);
            for (MealType type : MealType.values()) {
                meals.putIfAbsent(type, new ArrayList<>());
            }
        });
        System.out.println(mealList);
    }
}
