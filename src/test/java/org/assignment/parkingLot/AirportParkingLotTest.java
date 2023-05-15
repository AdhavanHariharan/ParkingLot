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

public class AirportParkingLotTest {
    private ParkingLot parkingLot;
    @BeforeEach
    void createParkingLot(){
        this.parkingLot = new ParkingLot.LotBuilder().setTwoWheelerSpots(200).setFourWheelerSpots(500).setFeeModel(FeeModels.AIRPORT).createParkingLot();
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
            parkingLot.parkVehicle("bus", "29-May-2022 14:54:07");
        });
    }

    @Test
    void shouldUnAllocateParkingSpotForTwoWheelersAndReturnFees() throws Exception {
        Ticket ticket1 = parkingLot.parkVehicle("Motorcycle", "29-May-2022 01:00:07");
        Ticket ticket2 = parkingLot.parkVehicle("Motorcycle", "29-May-2022 01:00:07");
        Ticket ticket3 = parkingLot.parkVehicle("Motorcycle", "29-May-2022 01:00:07");
        assertEquals(1, ticket1.getSpotNumber());
        assertEquals(2, ticket2.getSpotNumber());
        assertEquals(3, ticket3.getSpotNumber());
        Receipt receipt = parkingLot.unParkVehicle(ticket1,"29-May-2022 01:59:07"); // 55 minutes
        assertEquals(0, receipt.getFee());
        Receipt receipt1 = parkingLot.unParkVehicle(ticket2,"29-May-2022 15:59:07"); // 14 hours and 59 minutes
        assertEquals(60, receipt1.getFee());
        Receipt receipt2 = parkingLot.unParkVehicle(ticket3,"30-May-2022 13:00:07"); // 1 day and 12 hours
        assertEquals(160, receipt2.getFee());
    }

    @Test
    void shouldUnAllocateParkingSpotForFourWheelersAndReturnFees() throws Exception {
        Ticket ticket1 = parkingLot.parkVehicle("Car", "29-May-2022 01:00:07");
        Ticket ticket2 = parkingLot.parkVehicle("SUV", "29-May-2022 01:00:07");
        Ticket ticket3 = parkingLot.parkVehicle("Car", "29-Dec-2022 01:00:07");
        assertEquals(1, ticket1.getSpotNumber());
        assertEquals(2, ticket2.getSpotNumber());
        assertEquals(3, ticket3.getSpotNumber());
        Receipt receipt = parkingLot.unParkVehicle(ticket1,"29-May-2022 01:50:07"); // 50 minutes
        assertEquals(60, receipt.getFee());
        Receipt receipt1 = parkingLot.unParkVehicle(ticket2,"29-May-2022 24:59:07"); // 23 hours and 59 minutes
        assertEquals(80, receipt1.getFee());
        Receipt receipt2 = parkingLot.unParkVehicle(ticket3,"01-Jan-2023 02:00:07"); // 3 days and 1 hour
        assertEquals(400, receipt2.getFee());
    }
}
