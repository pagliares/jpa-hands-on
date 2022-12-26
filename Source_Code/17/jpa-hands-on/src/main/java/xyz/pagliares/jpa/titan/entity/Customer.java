package xyz.pagliares.jpa.titan.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

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

    @OneToOne(cascade={CascadeType.ALL})
    // @JoinColumn(name="ADDR_ID")
    private Address address;
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name="CREDIT_CARD_ID")
    private CreditCard creditCard;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="CustomerID ")
    //    @JoinTable(name="CUSTOMER_PHONE",
    //                joinColumns = { @JoinColumn(name="CUSTOMER_ID") },
    //                inverseJoinColumns = {@JoinColumn(name="PHONE_ID")})
    private Collection<Phone> phoneNumbers = new ArrayList<>();

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

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    public Collection<Phone> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(Collection<Phone> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public void addPhoneNumber(Phone phoneNumber) {
        this.phoneNumbers.add(phoneNumber);
    }

    @Override
    // Notice one method by using substring
    // (Pre Java SE 8) to get only the date without time information
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", ssn=" + ssn +
                ", timeCreated=" + timeCreated +
                ", birthDate=" + birthDate.toString().substring(0, 10) +
                ", customerType=" + customerType +
                ", address=" + address +
                ", creditCard=" + creditCard +
                '}';
    }
}
