package org.assignment.parkingLot;

import org.assignment.parkingLot.exception.SpaceUnavailable;
import org.assignment.parkingLot.exception.SpotsUnAllocated;
import org.assignment.parkingLot.service.Receipt;
import org.assignment.parkingLot.service.Ticket;
import org.assignment.parkingLot.serviceImpl.ParkingLot;
import org.assignment.parkingLot.utitity.FeeModels;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MallParkingLotTest2 {
    private ParkingLot parkingLot;
    @BeforeEach
    void createParkingLot(){
        this.parkingLot = new ParkingLot.LotBuilder().setTwoWheelerSpots(100).setFourWheelerSpots(80).setTrucksSpots(10).setFeeModel(FeeModels.MALL).createParkingLot();
    }

    @Test
    void shouldAllocateParkingSpotsAndReturnSpotNumbers() throws Exception {
        Ticket ticket1 = parkingLot.parkVehicle("motorcycle", "29-May-2022 14:04:07");
        Ticket ticket2 = parkingLot.parkVehicle("car", "29-May-2022 14:44:07");
        Ticket ticket3 = parkingLot.parkVehicle("truck", "29-May-2022 14:44:07");

        assertEquals(1, ticket1.getSpotNumber());
        assertEquals(1, ticket2.getSpotNumber());
        assertEquals(1, ticket3.getSpotNumber());
    }

    @Test
    void shouldUnAllocateParkingSpotsAndReturnFees() throws Exception {
        Ticket ticket1 = parkingLot.parkVehicle("motorcycle", "29-May-2022 14:04:07");
        Ticket ticket2 = parkingLot.parkVehicle("car", "29-May-2022 14:44:07");
        Ticket ticket3 = parkingLot.parkVehicle("truck", "29-May-2022 14:00:00");
        Receipt receipt1 = parkingLot.unParkVehicle(ticket1,"29-May-2022 17:44:07"); // 3 hours and 30 minutes
        Receipt receipt2 = parkingLot.unParkVehicle(ticket2,"29-May-2022 20:45:07"); // 6 hours and 1 minute
        Receipt receipt3 = parkingLot.unParkVehicle(ticket3,"29-May-2022 15:59:00"); // 1 hour and 59 minutes

        assertEquals(40, receipt1.getFee());
        assertEquals(140, receipt2.getFee());
        assertEquals(100, receipt3.getFee());
    }

}
