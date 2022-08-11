package com.codegym.service;

import com.codegym.models.Branch;
import com.codegym.models.Customer;
import com.codegym.repository.ICustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    ICustomerRepo iCustomerRepo;
    public List<Customer> getAll(){
        return (List<Customer>) iCustomerRepo.findAll();
    }
    public Customer getallbyid(int id){
        return iCustomerRepo.findById(id).get();
    }
    public void save(Customer customer) {
        iCustomerRepo.save(customer);
    }
    public void delete(int id) {
        iCustomerRepo.deleteById(id);
    }
    public Optional<Customer> findById(int id){
        return iCustomerRepo.findById(id);
    }
    public List<Customer>findCustomerByNameContaining(String name){
        return iCustomerRepo.findCustomerByNameContaining(name);
    }
    public List<Customer>sortAge(){
        return iCustomerRepo.sortByAge();
    }
}
