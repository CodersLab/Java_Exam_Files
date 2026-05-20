import java.math.BigDecimal;
import java.math.RoundingMode;

public class LoyaltyPointsService {

    public int calculatePoints(BigDecimal orderValue, boolean vipCustomer) {
        if (orderValue == null) {
            throw new IllegalArgumentException("Order value cannot be null");
        }

        if (orderValue.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Order value cannot be negative");
        }

        int points = orderValue
                .divide(new BigDecimal("10"), 0, RoundingMode.HALF_UP)
                .intValue();

        if (vipCustomer) {
            points = points * 2;
        }

        return Math.min(points, 500);
    }
}