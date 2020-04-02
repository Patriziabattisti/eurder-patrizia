package com.lonesome.eurder.domain.customers;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CustomerRepository {

    private Map<UUID,Customer> customers;

    public CustomerRepository() {
        this.customers = new HashMap<>();
    }

    public Customer saveCustomer(Customer customer){
        customers.put(customer.getId(),customer);

        return getCustomerById(customer.getId());
    }

    public Customer getCustomerById(UUID id){
        Customer customerSearched=customers.get(id);

        if(customerSearched == null) {
            throw new IllegalArgumentException("No Customer could be found for id " + id);
        }

        return customerSearched;

    }

}
