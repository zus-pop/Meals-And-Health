package utils;

import java.util.stream.Stream;

/**
 *
 * @author hoang
 */
public enum Days {
    MONDAY(2),
    TUESDAY(4),
    WEDNESDAY(8),
    THURSDAY(16),
    FRIDAY(32),
    SATURDAY(64);

    private final int value;
    private Days(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
    
    public static Days get(int num) {
        return Stream.of(Days.values())
                    .filter(day -> day.getValue() == num)
                    .findFirst()
                    .orElse(null);
    }

}

    class Test {
        public static void main(String[] args) {
            String str = "chickenattack";
            System.out.println(str.hashCode());
        }
    }