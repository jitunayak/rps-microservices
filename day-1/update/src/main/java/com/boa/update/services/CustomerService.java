package com.boa.update.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boa.update.models.Customer;
import com.boa.update.repositories.CustomerRepository;

@Service
public class CustomerService {
	@Autowired
	private CustomerRepository customerRepository;

	//insert
	
	public Customer addCustomer(Customer customer) {
		
		if( customer.getAddressList().size() > 0 )
        {
            customer.getAddressList().stream().forEach( address -> {
                address.setCustomer(customer);
            } );
        }
    	return this.customerRepository.save(customer);
    }

	
	//findall
	
	public List<Customer> getAllCustomers(){
		return this.customerRepository.findAll();
	}
	
	//findbyid
	public Customer getCustomerById(long customerId) {
		return this.customerRepository.findById(customerId).orElse(null);
		
	}
	
	//update
	
	public Customer updateCustomer(Customer customer) {
		if( customer.getAddressList().size() > 0 )
        {
            customer.getAddressList().stream().forEach( address -> {
                address.setCustomer(customer);
            } );
        }
    	return this.customerRepository.save(customer);
	}
	
	//delete
	
	public boolean deleteCustomerById(long customerId) {
		boolean status=false;
		this.customerRepository.deleteById(customerId);
	    if(getCustomerById(customerId)==null)
	    	status=true;
	    return status;
	}
	
}