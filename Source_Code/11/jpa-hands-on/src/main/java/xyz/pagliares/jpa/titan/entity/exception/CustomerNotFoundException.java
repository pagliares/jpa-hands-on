package xyz.pagliares.jpa.titan.entity.exception;

public class CustomerNotFoundException extends Exception{

    public CustomerNotFoundException(String message) {
        super(message);
    }
}
