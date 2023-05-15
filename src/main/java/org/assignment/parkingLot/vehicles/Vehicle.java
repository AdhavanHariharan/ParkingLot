package org.assignment.parkingLot.vehicles;

import org.assignment.parkingLot.service.Spot;
import org.assignment.parkingLot.utitity.VehicleTypes;

public interface Vehicle {
    public VehicleTypes getType();
    Spot getAllocatedSpots();
}
