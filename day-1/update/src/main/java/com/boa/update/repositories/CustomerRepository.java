package com.boa.update.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.boa.update.models.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long>{

}
