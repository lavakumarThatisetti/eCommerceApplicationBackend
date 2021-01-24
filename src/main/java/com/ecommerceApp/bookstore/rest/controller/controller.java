package com.ecommerceApp.bookstore.rest.controller;

import com.ecommerceApp.bookstore.model.Orders;
import com.ecommerceApp.bookstore.model.PaymentModel;
import com.ecommerceApp.bookstore.model.Persons;
import com.ecommerceApp.bookstore.repository.OrderRepository;
import com.ecommerceApp.bookstore.repository.PersonRepository;
import com.ecommerceApp.bookstore.service.BookService;
import com.ecommerceApp.bookstore.service.PaymentService;
import com.instamojo.wrapper.model.PaymentOrderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/bookStore")
public class controller {
    private OrderRepository orderRepository;
    private PersonRepository personRepository;

    @Autowired
    public controller(OrderRepository orderRepository, PersonRepository personRepository){
        this.orderRepository=orderRepository;
        this.personRepository=personRepository;
    }
    @CrossOrigin(origins = "https://ekart-books-zonee.herokuapp.com/")
    @PostMapping(produces = "application/json", value = "/saveOrder")
    public ResponseEntity<Orders> saveOrder(@Valid @RequestBody Orders orders, BindingResult bindingResult){
        try {
            if (bindingResult.hasErrors()) {
                throw new Exception(bindingResult.getFieldErrors().iterator().next().getDefaultMessage());
            }
        }catch (Exception e) {
            ResponseEntity.ok(e.getMessage());
        }
        Orders savedOrder =  orderRepository.save(orders);
        return ResponseEntity.ok(savedOrder);
    }
    @CrossOrigin(origins = "https://ekart-books-zonee.herokuapp.com/")
    @GetMapping(value = "/getUser/{email}")
    public ResponseEntity<Integer> getUser(@PathVariable("email") String email){
        Persons persons = personRepository.findByEmail(email);
        return ResponseEntity.ok(persons.getPersonId());
    }
    @CrossOrigin(origins = "https://ekart-books-zonee.herokuapp.com/")
    @GetMapping(value = "/getUsers")
    public ResponseEntity<List<Persons>> getUsers(){
        List<Persons> persons = personRepository.findAll();
        return ResponseEntity.ok(persons);
    }
    @CrossOrigin(origins = "https://ekart-books-zonee.herokuapp.com/")
    @PostMapping(produces = "application/json", value = "/saveUser")
    public ResponseEntity<Persons> saveUser(@Valid @RequestBody Persons persons, BindingResult bindingResult){
        try {
            if (bindingResult.hasErrors()) {
                throw new Exception(bindingResult.getFieldErrors().iterator().next().getDefaultMessage());
            }
        }catch (Exception e) {
            ResponseEntity.ok(e.getMessage());
        }
        System.out.println(persons);
        Persons persons1=null;
        try{
            persons1 = personRepository.save(persons);
        }catch (Exception e ){
            System.out.println(e.getMessage());
        }
        return ResponseEntity.ok(persons1);
    }
}
