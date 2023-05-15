package org.assignment.parkingLot.feeModels;

import org.assignment.parkingLot.vehicles.Vehicle;
import org.assignment.parkingLot.utitity.Config;
import org.assignment.parkingLot.utitity.Utils;
import org.assignment.parkingLot.utitity.VehicleTypes;

import java.util.Map;

public class Mall implements FeeModel {
    Map<VehicleTypes, Integer> baseCharges;
    public Mall() {
        this.baseCharges = Config.mallBaseChargesConstruct();
    }

    @Override
    public int calculateTotalFee(Vehicle vehicle, String startDateTime, String endDateTime) {
        int fee = 0;
        Map<String, Integer> timeDiff = Utils.calculateTimeDiff(startDateTime, endDateTime);
        fee = fee + (timeDiff.get("days") !=0 ? baseCharges.get(vehicle.getType()) * (timeDiff.get("day") * 24) : 0);
        fee = fee + (timeDiff.get("hours") !=0 ? baseCharges.get(vehicle.getType()) * timeDiff.get("hours"): 0);
        fee = fee + (timeDiff.get("minutes") >=0 ? baseCharges.get(vehicle.getType()) : 0);
        return fee;
    }
}
