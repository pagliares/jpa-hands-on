package xyz.pagliares.jpa.titan.inheritance.entity;

import jakarta.persistence.Entity;

@Entity
public class Employee extends Customer{
    private int employeeId;

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }
}
