package com.lonesome.eurder.api.controllers;

import com.lonesome.eurder.infrastructure.dtos.customersdto.CustomerDto;
import com.lonesome.eurder.service.customers.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;


@RestController
@RequestMapping(path=UserController.USERS_RESOURCE_PATH)
public class UserController {
    public static final String USERS_RESOURCE_PATH = "/users";
    private CustomerService customerService;
    private final Logger myLogger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public UserController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDto createUser(@RequestBody CustomerDto.CustomerDtoBuilder customerDtoBuilder) {

        CustomerDto customerDto = new CustomerDto(customerDtoBuilder);
        customerService.saveCustomer(customerDto);
        myLogger.info("someone saved a customer");
        return customerDto;
    }

    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public Collection<CustomerDto> getAllUsers() {
        return customerService.getAll();
    }

    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping(produces = "application/json", path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDto getUserById(@PathVariable UUID id) {
        return customerService.getCustomerById(id);
    }


}
