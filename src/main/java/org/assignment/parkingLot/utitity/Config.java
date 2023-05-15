package org.assignment.parkingLot.utitity;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Config {

    public static Map<VehicleTypes, Integer> mallBaseChargesConstruct(){
        return Map.of(VehicleTypes.TWOWHEELERS, 10, VehicleTypes.FOURWHEELERS, 20, VehicleTypes.TRUCKS, 50);
    }

    public static Map<VehicleTypes, Map<Integer, Integer>> stadiumBaseChargesConstruct(){
        return Map.of(VehicleTypes.TWOWHEELERS, Map.of(4, 30, 8, 60, 12, 100), VehicleTypes.FOURWHEELERS, Map.of(4, 60, 8, 120, 12, 200));
    }

    public static Map<VehicleTypes, Map<Integer, Integer>> airportBaseChargesConstruct() {
        return Map.of(VehicleTypes.TWOWHEELERS, Map.of(1, 0, 8, 40, 16, 60, 24, 80), VehicleTypes.FOURWHEELERS, Map.of(12, 60, 23, 80, 24, 100));
    }

    public static List<String> twoWheelersList = Arrays.asList("scooter", "motorcycle", "electric bike");
    public static List<String> fourWheelersList = Arrays.asList("suv", "electric suv", "car");
    public static List<String> trucksList = Arrays.asList("trucks", "bus", "trailer");
}
