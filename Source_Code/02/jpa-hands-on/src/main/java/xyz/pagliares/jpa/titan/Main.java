package xyz.pagliares.jpa.titan;

import xyz.pagliares.jpa.titan.controller.CabinController;
import xyz.pagliares.jpa.titan.controller.CustomerController;
import xyz.pagliares.jpa.titan.entity.Cabin;
import xyz.pagliares.jpa.titan.entity.Customer;

public class Main {
    public static void main(String[] args) {
        Customer customer = new Customer();
        customer.setFirstName("Florentino");
        customer.setLastName("Ariza");

        CustomerController customerController = new CustomerController();
        customerController.persist(customer);

        Cabin cabin = new Cabin();
        cabin.setName("Cabin Sea View");
        cabin.setBedCount(2);
        cabin.setDeckLevel(3);
        cabin.setShipId(1);

        CabinController cabinController = new CabinController();
        cabinController.persist(cabin);
    }
}
