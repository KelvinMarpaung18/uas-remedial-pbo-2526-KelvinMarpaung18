package pbo.f01;

import pbo.f01.model.ParkingArea;
import pbo.f01.model.Vehicle;
import java.util.*;

public class App {
    private static final Map<String, ParkingArea> areas = new HashMap<>();
    private static final Map<String, Vehicle> vehicles = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine().trim();
            if (input.equals("---")) break;

            if (input.startsWith("area-add#")) {
                String[] parts = input.split("#");
                if (parts.length == 4) {
                    String name = parts[1];
                    int capacity = Integer.parseInt(parts[2]);
                    String allowed = parts[3];
                    areas.putIfAbsent(name, new ParkingArea(name, capacity, allowed));
                }
            } else if (input.startsWith("vehicle-add#")) {
                String[] parts = input.split("#");
                if (parts.length == 4) {
                    String plate = parts[1];
                    String owner = parts[2];
                    String type = parts[3];
                    vehicles.putIfAbsent(plate, new Vehicle(plate, owner, type));
                }
            } else if (input.startsWith("park#")) {
                String[] parts = input.split("#");
                if (parts.length == 3) {
                    String plate = parts[1];
                    String areaName = parts[2];
                    Vehicle v = vehicles.get(plate);
                    ParkingArea a = areas.get(areaName);
                    if (v != null && a != null &&
                        v.getType().equals(a.getAllowedType()) &&
                        !a.isFull() && v.getArea() == null) {
                        a.parkVehicle(v);
                    }
                }
            } else if (input.equals("display-all")) {
                displayAll();
            }
        }
        scanner.close();
    }

    private static void displayAll() {
        List<ParkingArea> areaList = new ArrayList<>(areas.values());
        areaList.sort(Comparator.comparing(ParkingArea::getName));
        for (ParkingArea a : areaList) {
            System.out.println(a);
            List<Vehicle> parked = new ArrayList<>(a.getVehicles());
            parked.sort(Comparator.comparing(Vehicle::getPlateNumber));
            for (Vehicle v : parked) {
                System.out.println(v);
            }
        }
    }
}