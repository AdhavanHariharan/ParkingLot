package org.assignment.parkingLot.feeModels;

import org.assignment.parkingLot.vehicles.Vehicle;

public interface FeeModel {
    public int calculateTotalFee(Vehicle vehicle, String startDateTime, String endDateTime);
}
