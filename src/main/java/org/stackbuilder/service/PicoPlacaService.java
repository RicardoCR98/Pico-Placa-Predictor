package org.stackbuilder.service;

import org.springframework.stereotype.Service;
import org.stackbuilder.exception.InvalidInputException;
import org.stackbuilder.model.PredictionRequest;
import org.stackbuilder.model.PredictionResponse;
import org.stackbuilder.utils.DateTimeParser;
import org.stackbuilder.utils.LicensePlateValidator;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class PicoPlacaService {

    public PredictionResponse canDrive(PredictionRequest request) {
        // Validate plate format, date and time
        if(!LicensePlateValidator.isValid(request.getLicensePlateNumber())){
            throw new InvalidInputException("Invalid Format");
        }
        if (request.getDate() == null || request.getDate().isEmpty()) {
            throw new InvalidInputException("Invalid Format");
        }
        if (request.getTime() == null || request.getTime().isEmpty()) {
            throw new InvalidInputException("Invalid Format");
        }
        // Parse date and time
        LocalDate date = DateTimeParser.parseDate(request.getDate());
        LocalTime time = DateTimeParser.parseTime(request.getTime());

        //Get the day of the week
        DayOfWeek dayOfWeek = date.getDayOfWeek();

        //Get the last digit of the plate
        char lasDigitChar = request.getLicensePlateNumber().charAt(request.getLicensePlateNumber().length() - 1);
        int lastDigit = Character.getNumericValue(lasDigitChar);

        //Validate if the last number is restricted
        boolean isRestricted = isPlateRestricted(lastDigit,dayOfWeek) && isWithinRestrictedHours(time);
        String message = isRestricted
                ? "The vehicle with plate number " + request.getLicensePlateNumber() + " is RESTRICTED from driving on " + dayOfWeek + " at " + time + "."
                : "The vehicle with plate number " + request.getLicensePlateNumber() + " is ALLOWED to drive on " + dayOfWeek + " at " + time + ".";

        // Return the response with the result
        return new PredictionResponse(!isRestricted,message);
    }

    private boolean isWithinRestrictedHours(LocalTime time) {
        //Here apply the rule for hours
        LocalTime morningStart = LocalTime.of(7,0);
        LocalTime morningEnd = LocalTime.of(9,30);
        LocalTime afternoonStart = LocalTime.of(16,0);
        LocalTime afternoonEnd = LocalTime.of(19,30);

        boolean morning=time.isAfter(morningStart.minusSeconds(1))&&time.isBefore(morningEnd.minusSeconds(1));
        boolean afternoon=time.isAfter(afternoonStart.minusSeconds(1))&&time.isBefore(afternoonEnd.minusSeconds(1));

        return morning||afternoon;
    }

    private boolean isPlateRestricted(int lastDigit, DayOfWeek dayOfWeek) {
        //Here apply the rule for days and digit
        return switch (dayOfWeek) {
            case MONDAY -> lastDigit == 1 || lastDigit == 2;
            case TUESDAY -> lastDigit == 3 || lastDigit == 4;
            case WEDNESDAY -> lastDigit == 5 || lastDigit == 6;
            case THURSDAY -> lastDigit == 7 || lastDigit == 8;
            case FRIDAY -> lastDigit == 9 || lastDigit == 0;
            default -> false;
        };
    }


}
