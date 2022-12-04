package xyz.pagliares.jpa.titan.entity;

import jakarta.persistence.*;

@Entity
@Table(name="CABIN")
public class Cabin {
    private int id;
    private String name;
    private int deckLevel;

    @Id
    @GeneratedValue
    @Column(name="CABIN_ID")
    public int getId() {
        return id;
    }

    public void setId(int pk) {
        this.id = pk;
    }

    @Column(name="CABIN_NAME")
    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name="CABIN_DECK_LEVEL")
    public int getDeckLevel() {
        return deckLevel;
    }

    public void setDeckLevel(int level) {
        this.deckLevel = level;
    }
}
