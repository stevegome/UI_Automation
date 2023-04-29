package Utilities;


import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.Calendar;
import java.util.Locale;


public class CalendarUtils {

    // Get calendar set to current date and time
    Calendar c = Calendar.getInstance();

    public static String getCurrentWeek(String pattern) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern);
        LocalDateTime now = LocalDateTime.now();
        String currentDate = dtf.format(now);
        String dateBefore7days = dtf.format(now.minusDays(6));

        return dateBefore7days + " - " + currentDate;
    }


    public static String getCurrentDate(String pattern) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern);
        LocalDateTime now = LocalDateTime.now();
        String currentDate = dtf.format(now);
        return currentDate;
    }


    public static String beforeDateForProvidedDays(String pattern, int beforeDays) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern);
        LocalDateTime now = LocalDateTime.now();
        String currentDate = dtf.format(now);
        String dateBeforedays = dtf.format(now.minusDays(beforeDays));
        return dateBeforedays;
    }


    public static String getPreviousMonth(String pattern, int noOfMonthToGoBack) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern);
        LocalDateTime now = LocalDateTime.now();
        String previousMonth = dtf.format(now.minusMonths(noOfMonthToGoBack));
        return previousMonth;
    }

    public static String getCurrentMonth(String pattern) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern);
        LocalDateTime now = LocalDateTime.now();
        String currentMonth = dtf.format(now.getMonth());
        return currentMonth;
    }

    public static int getCurrentYear() {
        LocalDateTime now = LocalDateTime.now();
        int currentYear = now.getYear();
        return currentYear;
    }

    public static String getfirstDayOfWeek(String pattern) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern);
        LocalDateTime now = LocalDateTime.now();
        String firstDayOfWeek =
                dtf.format(now.with(TemporalAdjusters.previousOrSame(WeekFields.of(Locale.US).getFirstDayOfWeek())));
        return firstDayOfWeek;
    }

    public static String getLastDayOfWeek(String pattern) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern);
        LocalDateTime now = LocalDateTime.now();
        DayOfWeek firstDayOfWeek = WeekFields.of(Locale.US).getFirstDayOfWeek();
        DayOfWeek lastDayOfWeek = DayOfWeek.of(((firstDayOfWeek.getValue() + 5) % DayOfWeek.values().length) + 1);
        String lastDayOfCurrentWeek = dtf.format(now.with(TemporalAdjusters.nextOrSame(lastDayOfWeek)));
        return lastDayOfCurrentWeek;
    }

    public static String getfirstDayDateOfAnyPreviousWeek(String pattern, int numberOfDays) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern);
        LocalDateTime now = LocalDateTime.now();
        String firstDayOfWeek =
                dtf.format(now.with(TemporalAdjusters.previousOrSame(WeekFields.of(Locale.US).getFirstDayOfWeek())));
        String firstDayOfAnyPreviousWeek = dtf.format(now.minusDays(numberOfDays)
                .with(TemporalAdjusters.previousOrSame(WeekFields.of(Locale.US).getFirstDayOfWeek())));
        return firstDayOfAnyPreviousWeek;
    }

    public static String getlastDayDateOfAnyPreviousWeek(String pattern, int numberOfDays) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern);
        LocalDateTime now = LocalDateTime.now();
        DayOfWeek firstDayOfWeek = WeekFields.of(Locale.US).getFirstDayOfWeek();
        DayOfWeek lastDayOfWeek = DayOfWeek.of(((firstDayOfWeek.getValue() + 5) % DayOfWeek.values().length) + 1);
        String lastDayOfAnyPreviousWeek =
                dtf.format(now.minusDays(numberOfDays).with(TemporalAdjusters.nextOrSame(lastDayOfWeek)));
        return lastDayOfAnyPreviousWeek;
    }


}
