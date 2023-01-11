package xyz.pagliares.jpa.titan.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Reservation {
    private int id;
    private float amountPaid;
    private LocalDateTime date;
    private Cruise cruise;
    private Set<Customer> customers = new HashSet<>();

    private Set<Cabin> cabins = new HashSet<>();

    public Reservation() {
    }

    public Reservation(Cruise cruise) {
        this.cruise = cruise;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name="AMOUNT_PAID")
    public float getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(float amountPaid) {
        this.amountPaid = amountPaid;
    }

    @Column(name="DATE_RESERVED")
    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @ManyToOne
    @JoinColumn(name="CRUISE_ID")
    public Cruise getCruise() {
        return cruise;
    }

    public void setCruise(Cruise cruise) {
        this.cruise = cruise;
    }

    @ManyToMany
    @JoinTable(name="RESERVATION_CUSTOMER",
                joinColumns={@JoinColumn(name="RESERVATION_ID")},
                inverseJoinColumns={@JoinColumn(name="CUSTOMER_ID")})
    public Set<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(Set<Customer> customers) {
        this.customers = customers;
    }

    @ManyToMany
    @JoinTable(name="RESERVATION_CABIN",
               joinColumns = {@JoinColumn(name="RESERVATION_ID")},
               inverseJoinColumns = {@JoinColumn(name="CABIN_ID")}
    )
    public Set<Cabin> getCabins() {
        return cabins;
    }

    public void setCabins(Set<Cabin> cabins) {
        this.cabins = cabins;
    }
}
