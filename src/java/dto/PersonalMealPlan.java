package dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import utils.Days;
import utils.MealType;

/**
 *
 * @author hoang
 */
public class PersonalMealPlan implements Serializable {

    private int id;
    private String name;
    private String description;
    private String image;
    private Map<Days, Map<MealType, List<Meal>>> meals;
    private UserAccount customer;
    
    public PersonalMealPlan(int id, String name, String description, String image) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
    }
    
    public PersonalMealPlan(int id, String name, String description, String image, UserAccount customer) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.customer = customer;
    }

    public PersonalMealPlan(int id, String name, String description, String image, Map<Days, Map<MealType, List<Meal>>> meals) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.meals = meals;
    }

    public PersonalMealPlan(int id, String name, String description, String image, Map<Days, Map<MealType, List<Meal>>> meals, UserAccount customer) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.meals = meals;
        this.customer = customer;
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

    public UserAccount getCustomer() {
        return customer;
    }

    public void setCustomer(UserAccount customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "PersonalMealPlan{" + "id=" + id + ", name=" + name + ", description=" + description + ", image=" + image + ", meals=" + meals + ", customer=" + customer + '}';
    }

    
}
