package dto;

import java.util.List;
import java.util.Map;
import utils.Days;
import utils.MealType;

/**
 *
 * @author hoang
 */
public class WeeklyMeal {

    private int id;
    private String name;
    private String description;
    private String image;
    private Map<Days, Map<MealType, List<Meal>>> meals;

    public WeeklyMeal() {
    }

    public WeeklyMeal(int id, String name, String description, String image) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
    }

     public WeeklyMeal(
            int id,
            String name,
            String description,
            String image,
            Map<Days, Map<MealType, List<Meal>>> meals) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.meals = meals;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Map<Days, Map<MealType, List<Meal>>> getMeals() {
        return meals;
    }

    public void setMeals(Map<Days, Map<MealType, List<Meal>>> meals) {
        this.meals = meals;
    }

    @Override
    public String toString() {
        return "WeeklyMeal{" + "id=" + id + ", name=" + name + ", description=" + description + ", image=" + image + ", meals=" + meals + '}';
    }
}
