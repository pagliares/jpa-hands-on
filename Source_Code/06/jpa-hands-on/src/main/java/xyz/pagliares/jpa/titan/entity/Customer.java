package xyz.pagliares.jpa.titan.entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name="CUSTOMER_TABLE")
public class Customer implements Serializable {
    // We annotate with @Id and @GeneratedValue the property id. An alternative would be
    // use these annotations before declaration of the getId() method.
    // If you decide to annotate the property, all other JPA annotations must be on properties
    // If you decide do annotate methods, all other JPA annotations must be on methods.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="CUST_ID", nullable = false)
    private Long id;
    @Column(name="FIRST_NAME", length=20, nullable = false)
    private String firstName;
    private String lastName;
    @Transient
    private long ssn;

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
    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", ssn=" + ssn +
                '}';
    }
}
