package com.codegym.repository;

import com.codegym.models.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;


public interface ICustomerRepo extends CrudRepository<Customer,Integer> {
    @Query (nativeQuery = true,value = "select * from customer order by age")
    List<Customer> sortByAge();

    List<Customer> findCustomerByNameContaining(String name);
}
