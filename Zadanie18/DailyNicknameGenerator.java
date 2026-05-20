import java.time.DayOfWeek;
import java.time.LocalDate;

public class DailyNicknameGenerator {

    public String generateNickname(String username) {
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("Username cannot be empty");
        }

        DayOfWeek currentDay = LocalDate.now().getDayOfWeek();

        if (currentDay == DayOfWeek.MONDAY) {
            return username + "_starter";
        }

        if (currentDay == DayOfWeek.FRIDAY) {
            return username + "_weekend_ready";
        }

        if (currentDay == DayOfWeek.SATURDAY || currentDay == DayOfWeek.SUNDAY) {
            return username + "_offline";
        }

        return username + "_regular";
    }
}