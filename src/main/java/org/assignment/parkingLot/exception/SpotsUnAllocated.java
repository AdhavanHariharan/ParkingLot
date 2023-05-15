package org.assignment.parkingLot.exception;

import org.assignment.parkingLot.utitity.VehicleTypes;

public class SpotsUnAllocated extends Exception{
    public SpotsUnAllocated(String errorMessage) {
        super(errorMessage);
    }
}
