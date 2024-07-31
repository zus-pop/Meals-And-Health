package utils;

import dto.Meal;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 *
 * @author hoang
 */
public class Util {
    public static String decode(String encode) {
        if (encode == null || encode.isEmpty()) {
            return encode;
        }
        return new String(encode.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
    }
    
    public static double getTotalCost(Map<Meal, Integer> cart) {
        return cart.entrySet()
                .stream()
                .map(entry -> entry.getKey().getPrice() * entry.getValue())
                .reduce(0d, (acc, cur) -> acc + cur);
    }
    
    public static String getDeliverAddress(String city, String district, String ward, String street) {
        List<String> address = Arrays.asList(street, ward, district, city);
        return address.stream()
                .filter(part -> part != null)
                .reduce("", (acc, cur) -> acc + ", " + cur)
                .substring(2);
    }
    
    public static void main(String[] args) {
        System.out.println(getDeliverAddress("Da Nang", "Hoang Sa", null, "vinhome"));
        System.out.println(decode(null));
    }
}
