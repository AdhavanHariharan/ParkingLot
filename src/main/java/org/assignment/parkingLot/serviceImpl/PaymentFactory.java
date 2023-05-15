package org.assignment.parkingLot.serviceImpl;

import org.assignment.parkingLot.feeModels.Airport;
import org.assignment.parkingLot.feeModels.FeeModel;
import org.assignment.parkingLot.feeModels.Mall;
import org.assignment.parkingLot.feeModels.Stadium;
import org.assignment.parkingLot.utitity.FeeModels;

public class PaymentFactory {
    public FeeModel getFeeModel(FeeModels feeModel) {
        if(feeModel.equals(FeeModels.MALL)) {
           return new Mall();
        } else if (feeModel.equals(FeeModels.STADIUM)) {
            return new Stadium();
        } else if (feeModel.equals(FeeModels.AIRPORT)) {
            return new Airport();
        }
        return null;
    }
}
