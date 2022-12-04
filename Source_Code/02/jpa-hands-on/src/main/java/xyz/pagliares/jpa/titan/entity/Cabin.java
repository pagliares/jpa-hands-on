package xyz.pagliares.jpa.titan.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "CABIN")
public class Cabin {
    private int id;
    private String name;
    private int deckLevel;
    private int shipId;
    private int bedCount;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CABIN_ID")
    public int getId() {
        return id;
    }

    public void setId(int pk) {
        this.id = pk;
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

    public void setDeckLevel(int level) {
        this.deckLevel = level;
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
}
