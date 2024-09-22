package org.stackbuilder.utils;

public class LicensePlateValidator {

    //Using Regex for validation

    public static boolean isValid(String licensePlate) {
        //Accept Uppercase and LowerCase for 3 characters
        //"-" is mandatory
        //[0-9] 4 times
        return licensePlate.matches("^[a-zA-Z]{3}-\\d{4}$");
    }
}
