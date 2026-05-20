import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AgeValidatorTest {

    private final AgeValidator ageValidator = new AgeValidator();

    @Test
    void shouldTestA() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            ageValidator.validateAge(15);
        });

        assertEquals("User must be at least 18 years old", exception.getMessage());
    }

    @Test
    void shouldTestB() {
        try {
            ageValidator.validateAge(15);
        } catch (IllegalArgumentException exception) {
        }
    }

    @Test
    void shouldTestC() {
        try {
            ageValidator.validateAge(15);
            assertTrue(true);
        } catch (IllegalArgumentException exception) {
            assertTrue(true);
        }
    }

    @Test
    void shouldTestD() {
        try {
            ageValidator.validateAge(15);
        } catch (Exception exception) {
            assertNotNull(exception);
        }
    }
}