package org.assignment.parkingLot.service;

import org.assignment.parkingLot.utitity.VehicleTypes;

import java.text.DecimalFormat;

public class Ticket {
    private String id;
    private int spotNumber;
    private String startDateTime;
    private VehicleTypes type;

    public String getId() {
        return id;
    }

    public VehicleTypes getType() {
        return type;
    }

    public int getSpotNumber() {
        return spotNumber;
    }

    public String getStartDateTime() {
        return startDateTime;
    }

    DecimalFormat df = new DecimalFormat("000");

    public Ticket(int id, int spotNumber, String startDateTime, VehicleTypes type) {
        this.id = df.format(id);
        this.spotNumber = spotNumber;
        this.startDateTime = startDateTime;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Parking Ticket:" + "\n"+
                "Ticket Number: " + id + "\n"+
                "Spot Number: " + spotNumber + "\n"+
                "Entry Date-time: " + startDateTime +"\n";
    }
}
