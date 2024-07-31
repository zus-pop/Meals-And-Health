package dto;

import utils.MealType;


/**
 *
 * @author hoang
 */
public class WeeklyMealDetail {
    private int id;
    private int planMealId;
    private Meal meal;
    private MealType type;
    private int daysOfTheWeek;

    public WeeklyMealDetail() {
    }

    public WeeklyMealDetail(int planMealId, Meal meal, MealType type, int daysOfTheWeek) {
        this.planMealId = planMealId;
        this.meal = meal;
        this.type = type;
        this.daysOfTheWeek = daysOfTheWeek;
    }
    
    public WeeklyMealDetail(int id, int planMealId, Meal meal, MealType type, int daysOfTheWeek) {
        this.id = id;
        this.planMealId = planMealId;
        this.meal = meal;
        this.type = type;
        this.daysOfTheWeek = daysOfTheWeek;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPlanMealId() {
        return planMealId;
    }

    public void setPlanMealId(int planMealId) {
        this.planMealId = planMealId;
    }

    public Meal getMeal() {
        return meal;
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
    }

    public MealType getType() {
        return type;
    }

    public void setType(MealType type) {
        this.type = type;
    }

    public int getDaysOfTheWeek() {
        return daysOfTheWeek;
    }

    public void setDaysOfTheWeek(int daysOfTheWeek) {
        this.daysOfTheWeek = daysOfTheWeek;
    }

    @Override
    public String toString() {
        return "WeeklyMealDetail{" + "id=" + id + ", planMealId=" + planMealId + ", meal=" + meal + ", type=" + type + ", daysOfTheWeek=" + daysOfTheWeek + '}';
    }
    
    
}
