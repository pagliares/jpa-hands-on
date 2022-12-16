package xyz.pagliares.jpa.titan.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
@Entity
@Table(name="CUSTOMER")
public class Customer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="CUSTOMER_ID", nullable = false)
    private Long id;
    @Column(name="FIRST_NAME", length=20, nullable = false)
    private String firstName;
    private String lastName;
    @Transient
    private long ssn;

    private LocalDateTime timeCreated;
    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    private CustomerType customerType;

    /**
     * A customer has one address. Contrast this solution with
     * the solution presented in the previous example
     * that used @Embedded, @AttributeOverrides, @AttributeOverride, and @Embeddable
     * If you want, you can customize the name of the column to be
     * created in the table Customer by using the annotation
     * @JoinColumn. For example, @JoinColumn(name="ADDR_ID")
     * The attribute cascade with value CascadeType.ALL indicates to
     * JPA that if we remove a customer, we also want to automatically
     * delete its address associated. In the same way, if we persist
     * a customer, it's address will be persisted automatically as well.
     * CascadeType has other constants, for example CascadeType.PERSIST
     * If you use CascadeType.PERSIST you are telling JPA that if you
     * persist a customer, you also want to persist its address, but
     * if you remove a customer, the associate address WILL NOT be
     * deleted from the database.
     */
    @OneToOne(cascade={CascadeType.ALL})
    // @JoinColumn(name="ADDR_ID")
    private Address address;

    public Customer() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public long getSsn() {
        return ssn;
    }
    public void setSsn(long ssn) {
        this.ssn = ssn;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public LocalDateTime getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(LocalDateTime timeCreated) {
        this.timeCreated = timeCreated;
    }

    public CustomerType getCustomerType() {
        return customerType;
    }

    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    // Notice one method by using substring
    // (Pre Java SE 8) to get only the date without time information
    public String toString() {
        return "Customer{\n" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", ssn=" + ssn +
                ", timeCreated=" + timeCreated +
                ", birthDate=" + birthDate.toString().substring(0, 10) +
                ", customerType=" + customerType +
                '}' + "\n";
    }
}
