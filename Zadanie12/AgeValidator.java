public class AgeValidator {

    public void validateAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Age cannot be negative");
        }

        if (age < 18) {
            throw new IllegalArgumentException("User must be at least 18 years old");
        }
    }
}