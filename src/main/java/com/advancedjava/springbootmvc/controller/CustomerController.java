package com.advancedjava.springbootmvc.controller;

import com.advancedjava.springbootmvc.entity.Customer;
import com.advancedjava.springbootmvc.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    CustomerRepository customerRepository;

    @GetMapping("")
    public List<Customer> findAll() {
        return customerRepository.findAll();

    }
    @GetMapping("/{id}")
    public ResponseEntity<Customer> findOne(@PathVariable String id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        Customer customer = optionalCustomer.isPresent()?optionalCustomer.get():null;
        HttpStatus status;
        if (customer == null) {
            status = HttpStatus.NOT_FOUND;
        } else {
            status = HttpStatus.ACCEPTED;
        }
        return new ResponseEntity<>(customer, status);

    }
    @GetMapping("/fake")
    public List<Customer> findAllFake() {
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer("customer1"));
        customerRepository.save(new Customer("customer1"));
        return customers;

    }
    @PostMapping("/add")
    public void add(@RequestBody @Valid Customer customer) {
        customerRepository.save(customer);
    }
    @PutMapping("/edit/{id}")
    public void edit(@RequestBody @Valid Customer customer, @PathVariable String id) throws Exception {
        if (!id .equals(customer.getId()) ) {
            throw new Exception("incoherence between ids");
        }
        customerRepository.save(customer);
    }

    @PatchMapping("/patch/{id}")
    public void editPatch(@RequestBody Map<String, Object> fields, @PathVariable String id) throws Exception {
        if (!customerRepository.findById(id).isPresent()) {
            throw new Exception("customer not found");
        }
        Customer customer = customerRepository.findById(id).get();
        if(id.equals(customer.getId() )) {
            throw new Exception("incoherence between ids");
        }

        fields.forEach((key,value) -> {
            Field field = ReflectionUtils.findField(Customer.class, key);
            ReflectionUtils.makeAccessible(field);
            ReflectionUtils.setField(field, customer, value);
        });
        customerRepository.save(customer);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable String id) {
        customerRepository.deleteById(id);
    }

}
