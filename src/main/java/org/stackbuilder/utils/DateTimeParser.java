package org.stackbuilder.utils;

import org.stackbuilder.exception.InvalidInputException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTimeParser {
    public static LocalDate parseDate(String date) {
        try {
            // Parsing date according to specific format
            return LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        } catch (DateTimeParseException e) {
            throw new InvalidInputException("Invalid Format");
        }
    }

    public static LocalTime parseTime(String time) {
        try {
            // Parsing hour according to specific format
            return LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm"));
        } catch (DateTimeParseException e) {
            throw new InvalidInputException("Invalid Format");
        }
    }
}
