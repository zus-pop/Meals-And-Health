package dto;

import java.io.Serializable;

/**
 *
 * @author hoang
 */
public class OrderDetail implements Serializable {
    private Order order;
    private Meal meal;
    private double unitPrice;
    private int quantity;
    private int discount;

    public OrderDetail(Meal meal, double unitPrice, int quantity) {
        this.meal = meal;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    public OrderDetail(Meal meal, double unitPrice, int quantity, int discount) {
        this.meal = meal;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.discount = discount;
    }

    public OrderDetail(Order order, Meal meal, double unitPrice, int quantity) {
        this.order = order;
        this.meal = meal;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Meal getMeal() {
        return meal;
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "OrderDetail{" + "order=" + order + ", meal=" + meal + ", unitPrice=" + unitPrice + ", quantity=" + quantity + ", discount=" + discount + '}';
    }
    
    
}
