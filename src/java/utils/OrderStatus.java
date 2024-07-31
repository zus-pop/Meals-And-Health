package utils;

import java.util.stream.Stream;

/**
 *
 * @author hoang
 */
public enum OrderStatus {
    PENDING(1, "Pending"),
    PROCESSING(2, "Processing"),
    DELIVERING(3, "Delivering"),
    COMPLETED(4, "Completed"),
    CANCELED(5, "Canceled");
    
    private final int id;
    private final String name;
    
    private OrderStatus(int id, String name) {
        this.id = id;
        this.name = name;
    }
    
    public int getId() {
        return this.id;
    }
    
    public String getName() {
        return this.name;
    }
    
    public static OrderStatus getStatus(int id) {
        return Stream.of(OrderStatus.values())
                .filter(status -> status.getId() == id)
                .findFirst()
                .orElse(null);
    }
}
