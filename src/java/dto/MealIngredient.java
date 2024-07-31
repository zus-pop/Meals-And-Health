package dto;

import java.io.Serializable;

/**
 *
 * @author hoang
 */
public class MealIngredient implements Serializable {
    private Ingredient ingredient;
    private String quantity;
    private Unit unit;

    public MealIngredient() {
    }

    public MealIngredient(Ingredient ingredient, String quantity, Unit unit) {
        this.ingredient = ingredient;
        this.quantity = quantity;
        this.unit = unit;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "MealIngredient{" + "ingredient=" + ingredient + ", quantity=" + quantity + ", unit=" + unit + '}';
    }
    
    
}
