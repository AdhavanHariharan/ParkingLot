package org.assignment.parkingLot.serviceImpl;

import org.assignment.parkingLot.vehicles.FourWheeler;
import org.assignment.parkingLot.vehicles.Trucks;
import org.assignment.parkingLot.vehicles.TwoWheeler;
import org.assignment.parkingLot.exception.SpaceUnavailable;
import org.assignment.parkingLot.exception.SpotsUnAllocated;
import org.assignment.parkingLot.service.Receipt;
import org.assignment.parkingLot.service.Ticket;
import org.assignment.parkingLot.vehicles.Vehicle;
import org.assignment.parkingLot.utitity.Config;
import org.assignment.parkingLot.utitity.FeeModels;
import org.assignment.parkingLot.utitity.VehicleTypes;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private final Map<VehicleTypes, Vehicle> spots;
    private final FeeModels feeModel;

    public Map<VehicleTypes, Vehicle> getSpots() {
        return spots;
    }

    @Override
    public String toString() {
        return "ParkingLot{" + "spots=" + spots + '}';
    }

    ParkingLot(Map<VehicleTypes, Vehicle> spots, FeeModels feeModel) {
        this.spots = spots;
        this.feeModel = feeModel;
    }

    public Ticket parkVehicle(String vehicle, String startDateTime) throws Exception {
        if (Config.twoWheelersList.stream().anyMatch(v -> v.contains(vehicle.toLowerCase()))) {
            return allocateSpot(VehicleTypes.TWOWHEELERS, startDateTime);
        } else if (Config.fourWheelersList.stream().anyMatch(v -> v.contains(vehicle.toLowerCase()))) {
            return allocateSpot(VehicleTypes.FOURWHEELERS, startDateTime);
        } else if (Config.trucksList.stream().anyMatch(v -> v.contains(vehicle.toLowerCase()))) {
            return allocateSpot(VehicleTypes.TRUCKS, startDateTime);
        }
        return null;
    }

     Ticket allocateSpot(VehicleTypes type, String startDateTime) throws Exception {
        if(spots.get(type).getAllocatedSpots().hasNoSpots()) {
            throw new SpotsUnAllocated("No spots allocated");
        } else if (spots.get(type).getAllocatedSpots().isFull()) {
            throw new SpaceUnavailable("No space available");
        }
        int spotNumber = spots.get(type).getAllocatedSpots().getSpotNumber();
        spots.get(type).getAllocatedSpots().incrParkingCount();
        int parkingCount = spots.get(type).getAllocatedSpots().getParkingCount();
        return new Ticket(parkingCount, spotNumber + 1, startDateTime, type);
    }

    public Receipt unParkVehicle(Ticket ticket, String endDateTime) {
        if (spots.get(ticket.getType()).getAllocatedSpots().isEmpty()) {
            System.out.println("No vehicle to unpark"+"\n");
            return null;
        }
        spots.get(ticket.getType()).getAllocatedSpots().setSpotNumberForUnParking();
        spots.get(ticket.getType()).getAllocatedSpots().incrUnParkingCount();
        return new Receipt(spots.get(ticket.getType()), ticket.getStartDateTime(), endDateTime, feeModel);
    }

    public static class LotBuilder {
        public static final int DEFAULT_SPOTS = 0;
        private int twoWheelerSpots = DEFAULT_SPOTS;
        private int fourWheelerSpots = DEFAULT_SPOTS;
        private int trucksSpots = DEFAULT_SPOTS;
        private FeeModels feeModel = null;

        public LotBuilder setTwoWheelerSpots(int count) {
            this.twoWheelerSpots = count;
            return this;
        }

        public LotBuilder setFourWheelerSpots(int count) {
            this.fourWheelerSpots = count;
            return this;
        }

        public LotBuilder setTrucksSpots(int count) {
            this.trucksSpots = count;
            return this;
        }

        public LotBuilder setFeeModel(FeeModels feeModel) {
            this.feeModel = feeModel;
            return this;
        }

        public ParkingLot createParkingLot() {
            Map<VehicleTypes, Vehicle> spots = new HashMap();
            spots.put(VehicleTypes.TWOWHEELERS, new TwoWheeler(this.twoWheelerSpots));
            spots.put(VehicleTypes.FOURWHEELERS, new FourWheeler(this.fourWheelerSpots));
            spots.put(VehicleTypes.TRUCKS, new Trucks(this.trucksSpots));
            return new ParkingLot(spots, feeModel);
        }
    }
}