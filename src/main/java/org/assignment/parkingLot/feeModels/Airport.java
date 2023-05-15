package org.assignment.parkingLot.feeModels;

import org.assignment.parkingLot.vehicles.Vehicle;
import org.assignment.parkingLot.utitity.Config;
import org.assignment.parkingLot.utitity.Utils;
import org.assignment.parkingLot.utitity.VehicleTypes;

import java.util.Map;

public class Airport implements FeeModel {
    Map<VehicleTypes, Map<Integer, Integer>> baseCharges;

     public Airport() {
        this.baseCharges = Config.airportBaseChargesConstruct();
    }

    @Override
    public int calculateTotalFee(Vehicle vehicle, String startDateTime, String endDateTime) {
        int fee = 0;
        int totalHours;
        Map<String, Integer> timeDiff = Utils.calculateTimeDiff(startDateTime, endDateTime);
        totalHours = timeDiff.get("hours") + (timeDiff.get("days") * 24);
        System.out.println("totalHours "+ totalHours);
        if(vehicle.getType().equals(VehicleTypes.TWOWHEELERS)) {
            if(totalHours < 1) {
                fee = 0;
            } else if (totalHours >= 1 && totalHours < 8) {
                fee = fee + baseCharges.get(vehicle.getType()).get(8);
            } else if (totalHours >= 8 && totalHours < 24) {
                fee = fee + baseCharges.get(vehicle.getType()).get(16);
            } else {
                fee = fee + baseCharges.get(vehicle.getType()).get(24) * timeDiff.get("days");
                if (timeDiff.get("hours") >= 0) {
                    fee = fee + baseCharges.get(vehicle.getType()).get(24);
                }
            }
        } else {
            if(totalHours < 12) {
                fee = fee + baseCharges.get(vehicle.getType()).get(12);
            } else if (totalHours >=12 && totalHours < 24) {
                fee = fee + baseCharges.get(vehicle.getType()).get(23);
            } else {
                fee = fee + baseCharges.get(vehicle.getType()).get(24) * timeDiff.get("days");
                if (timeDiff.get("hours") > 0) {
                    fee = fee + baseCharges.get(vehicle.getType()).get(24);
                }
            }
        }
        return fee;
    }
}
