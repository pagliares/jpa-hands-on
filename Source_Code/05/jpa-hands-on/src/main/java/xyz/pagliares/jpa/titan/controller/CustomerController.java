package xyz.pagliares.jpa.titan.controller;

import jakarta.persistence.EntityManager;
import xyz.pagliares.jpa.titan.entity.Customer;

public class CustomerController extends Controller {
    public CustomerController(EntityManager entityManager) {
        super(entityManager);
    }
    public void persist(Customer customer) {
        this.getEntityManager().getTransaction().begin();
        this.getEntityManager().persist(customer);
        this.getEntityManager().getTransaction().commit();
    }
    public Customer findCustomer(Long id){
        return this.getEntityManager().find(Customer.class, id);
    }
}
