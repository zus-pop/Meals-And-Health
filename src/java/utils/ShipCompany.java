package utils;

import java.util.stream.Stream;

/**
 *
 * @author hoang
 */
public enum ShipCompany {
    SHOPPEFOOD(1, "ShoppeFood", "123456789"),
    GRABFOOD(2, "GrabFood", "654321987"),
    FOODPANDA(3, "foodpanda", "987635214");
    
    private final int id;
    private final String name;
    private final String phone;
    
    private ShipCompany(int id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }
    
    public int getId() {
        return this.id;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getPhone() {
        return this.phone;
    }
    
    public static ShipCompany getShipper(int id) {
        return Stream.of(ShipCompany.values())
                .filter(shipper -> shipper.getId() == id)
                .findFirst()
                .orElse(null);
    }
}
