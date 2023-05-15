package org.assignment.parkingLot.vehicles;

import org.assignment.parkingLot.service.Spot;
import org.assignment.parkingLot.utitity.VehicleTypes;

public class TwoWheeler implements Vehicle {
    private VehicleTypes type = VehicleTypes.TWOWHEELERS;
    private Spot spot;
    public TwoWheeler(int allocatedSpots) {
        setSpot(new Spot(allocatedSpots));
    }
    public Spot getAllocatedSpots() {
        return spot;
    }
    public void setSpot(Spot spot) {
        this.spot = spot;
    }
    @Override
    public VehicleTypes getType() {
        return type;
    }

    @Override
    public String toString() {
        return "{" + "type='" + type + '\'' + ", spot=" + spot + '}';
    }
}
