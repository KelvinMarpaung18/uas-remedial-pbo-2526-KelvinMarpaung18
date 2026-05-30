package pbo.f01.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Vehicle {
    @Id
    private String plateNumber;
    private String owner;
    private String type;
    @ManyToOne
    private ParkingArea area;

    public Vehicle() {}

    public Vehicle(String plateNumber, String owner, String type) {
        this.plateNumber = plateNumber;
        this.owner = owner;
        this.type = type;
    }

    public String getPlateNumber() { return plateNumber; }
    public String getOwner() { return owner; }
    public String getType() { return type; }
    public ParkingArea getArea() { return area; }
    public void setArea(ParkingArea area) { this.area = area; }

    @Override
    public String toString() {
        return plateNumber + " " + owner + " " + type;
    }
}
