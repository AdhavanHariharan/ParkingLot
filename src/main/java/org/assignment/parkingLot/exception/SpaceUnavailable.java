package org.assignment.parkingLot.exception;

public class SpaceUnavailable extends Exception{
    public SpaceUnavailable(String errorMessage) {
        super(errorMessage);
    }
}
