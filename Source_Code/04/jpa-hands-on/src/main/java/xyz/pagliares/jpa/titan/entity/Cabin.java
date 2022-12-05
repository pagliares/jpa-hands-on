package xyz.pagliares.jpa.titan.entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "CABIN")
public class Cabin implements Serializable {
    private int id;
    private String name;
    private int deckLevel;
    private int shipId;
    private int bedCount;

    // Differently from the bean Customer, here we annotate the method getId of the bean
    // with @Id and @GeneratedValue. An alternative would be
    // use these annotations before declaration of the id property.
    // If you decide to annotate the property, all other JPA annotations must be on properties
    // If you decide do annotate methods, all other JPA annotations must be on methods.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CABIN_ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @Column(name = "CABIN_NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "DECK_LEVEL")
    public int getDeckLevel() {
        return deckLevel;
    }

    public void setDeckLevel(int deckLevel) {
        this.deckLevel = deckLevel;
    }

    @Column(name = "SHIP_ID")
    public int getShipId() {
        return shipId;
    }

    public void setShipId(int shipId) {
        this.shipId = shipId;
    }

    @Column(name = "BED_COUNT")
    public int getBedCount() {
        return bedCount;
    }

    public void setBedCount(int bedCount) {
        this.bedCount = bedCount;
    }

    @Override
    public String toString() {
        return "Cabin{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", deckLevel=" + deckLevel +
                ", shipId=" + shipId +
                ", bedCount=" + bedCount +
                '}';
    }
}
