package org.assignment.parkingLot.feeModels;

import org.assignment.parkingLot.vehicles.Vehicle;
import org.assignment.parkingLot.utitity.Config;
import org.assignment.parkingLot.utitity.Utils;
import org.assignment.parkingLot.utitity.VehicleTypes;

import java.util.Map;

public class Stadium implements FeeModel {
    Map <VehicleTypes, Map<Integer, Integer>> baseCharges;

    public Stadium() {
        this.baseCharges = Config.stadiumBaseChargesConstruct();
    }
    @Override

    public int calculateTotalFee(Vehicle vehicle, String startDateTime, String endDateTime) {
        int fee = 0;
        Map<String, Integer> timeDiff = Utils.calculateTimeDiff(startDateTime, endDateTime);
        int totalHours = timeDiff.get("hours") + (timeDiff.get("days") * 24);

        if(totalHours == 0 && timeDiff.get("minutes") > 0) {
            fee = fee + baseCharges.get(vehicle.getType()).get(4);
        } else if (totalHours > 0 && totalHours  <  4) {
            fee = fee + baseCharges.get(vehicle.getType()).get(4);
        } else if (totalHours >= 4 && totalHours  <  12) {
            fee = fee + baseCharges.get(vehicle.getType()).get(4) + baseCharges.get(vehicle.getType()).get(8);
        } else if (totalHours >= 12){
            fee = fee + (baseCharges.get(vehicle.getType()).get(4) + baseCharges.get(vehicle.getType()).get(8) +
                            (totalHours - 12) * baseCharges.get(vehicle.getType()).get(12) +
                            (timeDiff.get("minutes") > 0 ? baseCharges.get(vehicle.getType()).get(12) : 0));
        }
        return fee;
    }
}