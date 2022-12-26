package xyz.pagliares.jpa.titan.entity;


import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class Cruise implements Serializable {
    private int id;
    private String name;
    private Ship ship;

    public Cruise() {
    }

    public Cruise(String name, Ship ship) {
        this.name = name;
        this.ship = ship;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne
    @JoinColumn(name="SHIP_ID")
    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    @Override
    public String toString() {
        return "Cruise{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ship=" + ship +
                '}';
    }
}
