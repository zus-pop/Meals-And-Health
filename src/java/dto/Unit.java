package dto;

import java.io.Serializable;

/**
 *
 * @author hoang
 */
public class Unit implements Serializable {
    private int id;
    private String name;

    public Unit() {
    }

    public Unit(int id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public String toString() {
        return "Unit{" + "id=" + id + ", name=" + name + '}';
    }
    
    
}
