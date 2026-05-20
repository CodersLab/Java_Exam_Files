import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LoyaltyPointsServiceTest {

    private final LoyaltyPointsService loyaltyPointsService = new LoyaltyPointsService();

    @Test
    void shouldCalculatePointsForRegularCustomer() {
        int points = loyaltyPointsService.calculatePoints(new BigDecimal("149.99"), false);

        assertEquals(14, points);
    }

    @Test
    void shouldDoublePointsForVipCustomer() {
        int points = loyaltyPointsService.calculatePoints(new BigDecimal("120.00"), true);

        assertEquals(24, points);
    }

    @Test
    void shouldLimitPointsToMaximumValue() {
        int points = loyaltyPointsService.calculatePoints(new BigDecimal("4000.00"), true);

        assertEquals(500, points);
    }

    @Test
    void shouldThrowExceptionWhenOrderValueIsNegative() {
        assertThrows(IllegalArgumentException.class, () -> {
            loyaltyPointsService.calculatePoints(new BigDecimal("-10.00"), false);
        });
    }
}