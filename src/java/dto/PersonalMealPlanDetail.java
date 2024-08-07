package dto;

import java.io.Serializable;
import utils.MealType;

/**
 *
 * @author hoang
 */
public class PersonalMealPlanDetail implements Serializable {

    private int id;
    private int planMealId;
    private Meal meal;
    private MealType type;
    private int daysOfTheWeek;

    public PersonalMealPlanDetail(int id, int planMealId, Meal meal, MealType type, int daysOfTheWeek) {
        this.id = id;
        this.planMealId = planMealId;
        this.meal = meal;
        this.type = type;
        this.daysOfTheWeek = daysOfTheWeek;
    }

    public PersonalMealPlanDetail(int planMealId, Meal meal, MealType type, int daysOfTheWeek) {
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
        return "PersonalMealPlanDetail{" + "id=" + id + ", planMealId=" + planMealId + ", meal=" + meal + ", type=" + type + ", daysOfTheWeek=" + daysOfTheWeek + '}';
    }

}
