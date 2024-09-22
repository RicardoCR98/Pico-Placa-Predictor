package org.stackbuilder.utils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateTimeParser {
    public static LocalDate parseDate(String date) {
        // Parsing date according specific format
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    public static LocalTime parseTime(String time) {
        // Parsing hour according specific format
        return LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm"));
    }
}
