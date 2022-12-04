package xyz.pagliares.jpa.titan.controller;

import xyz.pagliares.jpa.titan.entity.Customer;

public class CustomerController extends Controller {
    public boolean persist(Customer customer) {
        entityManager.getTransaction().begin();
        entityManager.persist(customer);
        entityManager.getTransaction().commit();
        return true;
    }

    public Customer findCustomer(Long id){
        return entityManager.find(Customer.class, id);
    }
}
