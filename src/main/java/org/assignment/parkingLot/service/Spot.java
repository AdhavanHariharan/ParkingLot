package org.assignment.parkingLot.service;

import java.util.Arrays;

public class Spot {
    private int spotNumber = -1;
    private int spots[];
    private int parkingCount = 0;
    private int unParkingCount = 0;

    public void incrParkingCount() {
        this.parkingCount++;
    }

    public int getUnParkingCount() {
        return unParkingCount;
    }

    public int getParkingCount() {
        return parkingCount;
    }

    public void incrUnParkingCount() {
        this.unParkingCount++;
    }

    public Spot(int allocatedSpots) {
        spots = new int[allocatedSpots];
    }

    public int getSpotNumber() {
        return ++spotNumber;
    }

    public void setSpotNumberForUnParking() {
        this.spotNumber = spotNumber - 1;
    }

    @Override
    public String toString() {
        return Arrays.toString(spots) + ", " + "ptr" + this.spotNumber;
    }

    public Boolean isFull() {
        return this.spotNumber == spots.length - 1;
    }

    public Boolean hasNoSpots() {
        return this.spots.length == 0;
    }

    public Boolean isEmpty() {
        return this.parkingCount == 0 ? true : false;
    }
}
