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

    /* Instead of using @Temporal(TemporalType.TIMESTAMP) that is associated
    with java.util. Date  (Pre Java SE-8), in this example we will usenot on
    LocalDateTime (Since Java SE-8).
    Since LocalDateTime is considered a type value (@Basic) in JPA,
    We don't need to put any annotation to indicate this is a date/time field.
     */
    private LocalDateTime timeCreated;
    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    private CustomerType customerType;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="street", column=@Column(name="CUSTOMER_STREET")),
            @AttributeOverride(name="city", column=@Column(name="CUSTOMER_CITY")),
            @AttributeOverride(name="state", column=@Column(name="CUSTOMER_STATE")),
    })
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
