package dto;

import java.io.Serializable;

/**
 *
 * @author hoang
 */
public class Meal implements Serializable {
    private int id;
    private String name;
    private String description;
    private String image;
    private double price;
    private boolean available;

    public Meal() {
    }

    public Meal(int id, String name, String description, String image, double price, boolean available) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.price = price;
        this.available = available;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "Meal{" + "id=" + id + ", name=" + name + ", description=" + description + ", image=" + image + ", price=" + price + ", available=" + available + '}';
    }
    
    
}
