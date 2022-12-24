package xyz.pagliares.jpa.titan.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "CREDIT_CARD")
public class CreditCard implements Serializable {
    private int id;
    private LocalDate expiration;
    private String number;
    private String name;
    private String organization;
    private Customer customer;

    private int securityCode;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getExpiration() {
        return expiration;
    }

    public void setExpiration(LocalDate expiration) {
        this.expiration = expiration;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    @OneToOne(mappedBy = "creditCard")
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(int securityCode) {
        this.securityCode = securityCode;
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "id=" + id +
                ", expiration=" + expiration +
                ", number='" + number + '\'' +
                ", name='" + name + '\'' +
                ", organization='" + organization + '\'' +
                ", securityCode=" + securityCode +
                '}';
    }
}
