package org.stackbuilder.model;

public class PredictionResponse {
    //Represents the response, indicates if the vehicle can drive
    private boolean canDrive;
    private String message;

    public PredictionResponse(boolean canDrive, String message) {
        this.canDrive = canDrive;
        this.message = message;
    }

    public boolean isCanDrive() {
        return canDrive;
    }

    public void setCanDrive(boolean canDrive) {
        this.canDrive = canDrive;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
