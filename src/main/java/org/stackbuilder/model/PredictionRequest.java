package org.stackbuilder.model;

public class PredictionRequest {
    //Represents the request, includes plate, date and time

    private String licensePlateNumber;
    private String date;
    private String time;

    public PredictionRequest(String licensePlateNumber, String date, String time) {
        this.licensePlateNumber = licensePlateNumber;
        this.date = date;
        this.time = time;
    }

    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }

    public void setLicensePlateNumber(String licensePlateNumber) {
        this.licensePlateNumber = licensePlateNumber;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
