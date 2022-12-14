package xyz.pagliares.jpa.titan.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

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

    // Date and time the customer is persisted in the database
    // Based on java.util.Date class (Pre Java SE-8), not on
    // LocalDateTime (Since Java SE-8)
    // In the next example I will discuss using LocalDateTime (mapped
    // by default - same as using @Basic annotation (optional in JPA)
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeCreated;

    @Temporal(TemporalType.TIMESTAMP)
    private Date birthDate;
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

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Date getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(Date timeCreated) {
        this.timeCreated = timeCreated;
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
                '}' + "\n";
    }
}
