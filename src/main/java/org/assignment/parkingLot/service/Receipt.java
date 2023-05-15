package org.assignment.parkingLot.service;

import org.assignment.parkingLot.feeModels.FeeModel;
import org.assignment.parkingLot.vehicles.Vehicle;
import org.assignment.parkingLot.serviceImpl.PaymentFactory;
import org.assignment.parkingLot.utitity.FeeModels;

import java.text.DecimalFormat;

public class Receipt {
    private final String id;
    private final String startDateTime;
    private final String endDateTime;

    public int getFee() {
        return fee;
    }

    private final int fee;
    private static FeeModel feeModel;

    @Override
    public String toString() {
        return "Parking Receipt:" + "\n"+
                "Receipt Number: " + id + "\n"+
                "Entry Date-time: " + startDateTime + "\n"+
                "Exit Date-time: " + endDateTime +"\n"+
                "Fees: " + fee +"\n";
    }

    DecimalFormat df = new DecimalFormat("000");

    public Receipt(Vehicle vehicle, String startDateTime, String endDateTime, FeeModels feeModel) {
        this.id = "R-"+df.format(vehicle.getAllocatedSpots().getUnParkingCount());
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.fee = totalFee(vehicle, startDateTime, endDateTime, feeModel);
    }
    public static int totalFee(Vehicle vehicle, String startDateTime, String endDateTime, FeeModels model) {
        FeeModel feeModel = new PaymentFactory().getFeeModel(model);
        return feeModel.calculateTotalFee(vehicle, startDateTime, endDateTime);
    }
}
