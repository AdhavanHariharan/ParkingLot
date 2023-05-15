package org.assignment.parkingLot.vehicles;

import org.assignment.parkingLot.service.Spot;
import org.assignment.parkingLot.utitity.VehicleTypes;

public class FourWheeler implements Vehicle {
    private VehicleTypes type = VehicleTypes.FOURWHEELERS;
    private Spot spot;
    public FourWheeler(int allocatedSpots) {
        setSpot(new Spot(allocatedSpots));
    }
    @Override
    public VehicleTypes getType() {
        return type;
    }
    @Override
    public Spot getAllocatedSpots() {
        return spot;
    }
    public void setSpot(Spot spot) {
        this.spot = spot;
    }

    public String toString() {
        return "{" + "type='" + type + '\'' + ", spot=" + spot + '}';
    }
}
