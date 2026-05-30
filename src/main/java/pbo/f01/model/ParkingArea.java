package pbo.f01.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class ParkingArea {
    @Id
    private String name;
    private int capacity;
    private String allowedType;

    @OneToMany(mappedBy = "area", cascade = CascadeType.ALL)
    private List<Vehicle> vehicles = new ArrayList<>();

    public ParkingArea() {}

    public ParkingArea(String name, int capacity, String allowedType) {
        this.name = name;
        this.capacity = capacity;
        this.allowedType = allowedType;
    }

    public String getName() { return name; }
    public int getCapacity() { return capacity; }
    public String getAllowedType() { return allowedType; }
    public List<Vehicle> getVehicles() { return vehicles; }
    public boolean isFull() { return vehicles.size() >= capacity; }

    public void parkVehicle(Vehicle v) {
        if (v == null) return;
        if (!isFull() && v.getType().equals(this.allowedType) && v.getArea() == null) {
            vehicles.add(v);
            v.setArea(this);
        }
    }

    @Override
    public String toString() {
        return name + " " + allowedType + " " + capacity + "|" + vehicles.size();
    }
}
