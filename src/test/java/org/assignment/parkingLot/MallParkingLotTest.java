package org.assignment.parkingLot;

import org.assignment.parkingLot.exception.SpaceUnavailable;
import org.assignment.parkingLot.exception.SpotsUnAllocated;
import org.assignment.parkingLot.service.Receipt;
import org.assignment.parkingLot.service.Ticket;
import org.assignment.parkingLot.serviceImpl.ParkingLot;
import org.assignment.parkingLot.utitity.FeeModels;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MallParkingLotTest {
    private ParkingLot parkingLot;
    @BeforeEach
    void createParkingLot(){
        this.parkingLot = new ParkingLot.LotBuilder().setTwoWheelerSpots(2).setFeeModel(FeeModels.MALL).createParkingLot();
    }

    @Test
    void shouldAllocateParkingSpotsAndReturnSpotNumbers() throws Exception {
        Ticket ticket1 = parkingLot.parkVehicle("Motorcycle", "29-May-2022 14:04:07");
        Ticket ticket2 = parkingLot.parkVehicle("Scooter", "29-May-2022 14:44:07");
        assertEquals(1, ticket1.getSpotNumber());
        assertEquals(2, ticket2.getSpotNumber());
    }

    @Test
    void shouldAllocateParkingSpotsForTwoVehicleAndReturnErrorMessageForThird() throws Exception {
        parkingLot.parkVehicle("Motorcycle", "29-May-2022 14:04:07");
        parkingLot.parkVehicle("Scooter", "29-May-2022 14:44:07");
        Exception exception = assertThrows(SpaceUnavailable.class, () -> {
            parkingLot.parkVehicle("Scooter", "29-May-2022 14:54:07");
        });
        assertTrue(exception.getMessage().contains("No space"));
    }

    @Test
    void shouldAllocateParkingSpotsForTwoWheelersAndReturnErrorMessageForFourWheelersAndTrucks() throws Exception {
        parkingLot.parkVehicle("Motorcycle", "29-May-2022 14:04:07");
        parkingLot.parkVehicle("Scooter", "29-May-2022 14:44:07");
        Exception exception = assertThrows(SpotsUnAllocated.class, () -> {
            parkingLot.parkVehicle("Truck", "29-May-2022 14:54:07");
        });
        assertTrue(exception.getMessage().contains("No spots"));
        Exception exception1 = assertThrows(SpotsUnAllocated.class, () -> {
            parkingLot.parkVehicle("Car", "29-May-2022 14:54:07");
        });
        assertTrue(exception1.getMessage().contains("No spots"));
    }

    @Test
    void shouldUnAllocateParkingSpotForGivenTicketAndReturnFees() throws Exception {
        Ticket ticket1 = parkingLot.parkVehicle("Motorcycle", "29-May-2022 14:04:07");
        Ticket ticket2 = parkingLot.parkVehicle("Scooter", "29-May-2022 14:44:07");
        assertEquals(1, ticket1.getSpotNumber());
        assertEquals(2, ticket2.getSpotNumber());
        Receipt receipt = parkingLot.unParkVehicle(ticket1,"29-May-2022 17:44:07"); // 3 hours and 40 minutes
        assertEquals(40, receipt.getFee());
        Receipt receipt1 = parkingLot.unParkVehicle(ticket2,"29-May-2022 15:40:07"); // 56 minutes
        assertEquals(10, receipt1.getFee());
    }

    @Test
    void shouldParkAfterUnparkingAndReturnSpotNumber() throws Exception {
        Ticket ticket1 = parkingLot.parkVehicle("Motorcycle", "29-May-2022 14:04:07");
        parkingLot.parkVehicle("Scooter", "29-May-2022 14:44:07");
        parkingLot.unParkVehicle(ticket1,"29-May-2022 17:44:07");
        Ticket ticket2 = parkingLot.parkVehicle("Scooter", "29-May-2022 15:59:07");
        assertEquals(2, ticket2.getSpotNumber());;
    }

}
