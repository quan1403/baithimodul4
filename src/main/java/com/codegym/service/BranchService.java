package com.codegym.service;

import com.codegym.models.Branch;
import com.codegym.repository.IBranchRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BranchService {
    @Autowired
    IBranchRepo iBranchRepo;
    public List<Branch> getAll(){
        return (List<Branch>) iBranchRepo.findAll();
    }
    public Optional<Branch> findById(int id){
        return iBranchRepo.findById(id);
    }

}
