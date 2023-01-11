package xyz.pagliares.jpa.titan.inheritance.entity;

import jakarta.persistence.*;

@Entity
@Table(name="PERSON_HIERARCHY")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="DISCRIMINATOR", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("Person")  // Optional
public class Person {
    private int id;
    private String firstName;
    private String lastName;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
