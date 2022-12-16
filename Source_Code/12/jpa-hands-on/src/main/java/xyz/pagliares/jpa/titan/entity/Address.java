package xyz.pagliares.jpa.titan.entity;

import jakarta.persistence.*;

import java.io.Serializable;

/**
 * In this example, instead of considering Address as an embeddable
 * java object (without identity/primary key), we will consider it
 * as an entity object (with identity/primary key - @Id). In this way
 * a separated table will be generated to it, and we are going
 * to associate the address with the customer via one-to-one
 * relationship in entity bean Customer (@OneToOne)
 */
@Entity
public class Address implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    private String street;
    private String city;
    private String state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
