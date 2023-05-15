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

public class StadiumParkingLotTest {
    private ParkingLot parkingLot;
    @BeforeEach
    void createParkingLot(){
        this.parkingLot = new ParkingLot.LotBuilder().setTwoWheelerSpots(1000).setFourWheelerSpots(1500).setFeeModel(FeeModels.STADIUM).createParkingLot();
    }

    @Test
    void shouldAllocateParkingSpotsAndReturnSpotNumbers() throws Exception {
        Ticket ticket1 = parkingLot.parkVehicle("Motorcycle", "29-May-2022 14:04:07");
        Ticket ticket2 = parkingLot.parkVehicle("Car", "29-May-2022 14:44:07");
        assertEquals(1, ticket1.getSpotNumber());
        assertEquals(1, ticket2.getSpotNumber());
    }

    @Test
    void shouldAllocateParkingSpotsForTwoWheelersAndFourWheelersAndReturnErrorMessageForTrucks() throws Exception {
        parkingLot.parkVehicle("Motorcycle", "29-May-2022 14:04:07");
        parkingLot.parkVehicle("Car", "29-May-2022 14:44:07");
        assertThrows(SpotsUnAllocated.class, () -> {
            parkingLot.parkVehicle("truck", "29-May-2022 14:54:07");
        });
    }

    @Test
    void shouldUnAllocateParkingSpotForTwoWheelersAndReturnFees() throws Exception {
        Ticket ticket1 = parkingLot.parkVehicle("Motorcycle", "29-May-2022 01:00:07");
        Ticket ticket2 = parkingLot.parkVehicle("Motorcycle", "29-May-2022 01:00:07");
        assertEquals(1, ticket1.getSpotNumber());
        assertEquals(2, ticket2.getSpotNumber());
        Receipt receipt = parkingLot.unParkVehicle(ticket1,"29-May-2022 04:40:07"); // 3 hours and 40 minutes
        assertEquals(30, receipt.getFee());
        Receipt receipt1 = parkingLot.unParkVehicle(ticket2,"29-May-2022 15:59:07"); // 14 hours and 59 minutes
        assertEquals(390, receipt1.getFee());
    }

    @Test
    void shouldUnAllocateParkingSpotForFourWheelersAndReturnFees() throws Exception {
        Ticket ticket1 = parkingLot.parkVehicle("Electric SUV", "29-May-2022 01:00:07");
        Ticket ticket2 = parkingLot.parkVehicle("SUV", "29-May-2022 01:00:07");
        assertEquals(1, ticket1.getSpotNumber());
        assertEquals(2, ticket2.getSpotNumber());
        Receipt receipt = parkingLot.unParkVehicle(ticket1,"29-May-2022 12:30:07"); // 11 hours and 30 minutes
        assertEquals(180, receipt.getFee());
        Receipt receipt1 = parkingLot.unParkVehicle(ticket2,"29-May-2022 14:05:07"); // 13 hours and 05 minutes
        assertEquals(580, receipt1.getFee());
    }
}
