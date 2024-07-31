package utils;

import java.util.stream.Stream;

/**
 *
 * @author hoang
 */
public enum Role {
    CUSTOMER(1),
    ADMIN(2);
    
    private final int id;
    
    private Role(int id) {
        this.id = id;
    }
    
    public int getId() {
        return this.id;
    }
    
    public static Role getRole(int id) {
        return Stream.of(Role.values())
                .filter(role -> role.getId() == id)
                .findFirst()
                .orElse(null);
    }
}

class Foo {
    public static void main(String[] args) {
        Role role = Role.CUSTOMER;
        System.out.println(role.equals(Role.CUSTOMER));
    }
}
