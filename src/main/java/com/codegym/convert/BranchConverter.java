package com.codegym.convert;

import com.codegym.models.Branch;
import com.codegym.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;


public class BranchConverter implements Converter<Integer,Branch> {
    @Autowired
    BranchService branchService;


    @Override
    public Branch convert(Integer id) {
        return branchService.findById(id).get();
    }

    @Override
    public <U> Converter<Integer, U> andThen(Converter<? super Branch, ? extends U> after) {
        return Converter.super.andThen(after);
    }
}
