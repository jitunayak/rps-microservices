package com.boa.update.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.boa.update.models.Customer;
import com.boa.update.services.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.bohnman.squiggly.Squiggly;
import com.github.bohnman.squiggly.util.SquigglyUtils;


@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    //post
    @PostMapping({"/v1.0", "/v1.1"})
    public ResponseEntity<?> addCustomer(@RequestBody Customer customer){

        Customer customerObject=this.customerService.addCustomer(customer);
        if(customerObject!=null) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(customerObject);
        }
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Verify the customer input");
    }

    //getall customers
    @GetMapping({"/v1.0", "/v1.1"})
    public List<Customer> getAllCustomers(){
        return this.customerService.getAllCustomers();
    }

    //filtering
    //http://localhost:7070/customers/v1.0/filters?fields=customerId,customerName,

    @GetMapping({"/v1.0/filters", "/v1.1/filters"})
    public String getFilteredCustomer(@RequestParam(name = "fields", required = false)
                                              String fields)
    {

        List<Customer> customerList = getAllCustomers();
        ObjectMapper mapper = Squiggly.init(new ObjectMapper(), fields);
        return SquigglyUtils.stringify(mapper, customerList);

    }
    //filtering
    //http://localhost:7070/customers/v1.0/filters?fields=customerId,customerName,

    @GetMapping({"/v1.0/filters/{customerId}", "/v1.1/filters/{customerId}"})
    public String getFilteredCustomerById(@RequestParam(name = "fields", required = false)
                                                  String fields, @PathVariable("customerId") long customerId)
    {

        Customer customerObj = this.customerService.getCustomerById(customerId);
        ObjectMapper mapper = Squiggly.init(new ObjectMapper(), fields);
        return SquigglyUtils.stringify(mapper, customerObj);

    }



}