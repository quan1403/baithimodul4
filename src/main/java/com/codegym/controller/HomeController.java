package com.codegym.controller;

import com.codegym.models.Branch;
import com.codegym.models.Customer;
import com.codegym.service.BranchService;
import com.codegym.service.CustomerService;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.OrderBy;
import javax.validation.Valid;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    BranchService branchService;
    @Autowired
    CustomerService customerService;

    @ModelAttribute(name = "customer")
    public Customer customer() {
        return new Customer();
    }

    @ModelAttribute(name = "branch")
    public List<Branch> branchList() {
        return branchService.getAll();
    }


    @GetMapping("/customer")
    public ModelAndView show() {
        ModelAndView modelAndView = new ModelAndView("show");
        modelAndView.addObject("customers", customerService.sortAge());

        return modelAndView;
    }

    @GetMapping("/show/{id}")
    public ModelAndView showNameById(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("show");
        modelAndView.addObject("customers", customerService.findById(id).get());
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreate() {
        ModelAndView modelAndView = new ModelAndView("create");
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView create(@Valid @ModelAttribute("customer") Customer customer, BindingResult bindingResult, @RequestParam("branchA") Branch branch) {
        if (bindingResult.hasFieldErrors()) {
            ModelAndView modelAndView = new ModelAndView("create");
            return modelAndView;
        }
        customer.setBranch(branch);
        customerService.save(customer);
        ModelAndView modelAndView = new ModelAndView("redirect:/customer");
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("redirect:/customer");
        customerService.delete(id);
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEdit(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("edit");
        modelAndView.addObject("customer",customerService.findById(id));
        return modelAndView;
    }

    @PostMapping("/edit/{id}")
    public ModelAndView edit(@ModelAttribute("customer") Customer customer) {
        customerService.save(customer);
        return new ModelAndView("redirect:/customer");
    }
    @PostMapping("/search")
    public String search(@RequestParam(value = "search", required = false) String search, Model model) {
        List<Customer> customers = customerService.findCustomerByNameContaining(search);
        model.addAttribute("customers", customers);
        return "show";
    }
}


