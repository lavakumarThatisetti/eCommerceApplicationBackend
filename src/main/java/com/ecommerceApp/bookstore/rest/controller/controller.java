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
import java.util.UUID;

@RestController
@RequestMapping("/bookStore")
public class controller {
    private OrderRepository orderRepository;
    private PersonRepository personRepository;
    private PaymentService paymentService;
    UUID uuid = UUID.randomUUID();

    @Autowired
    public controller(OrderRepository orderRepository, PersonRepository personRepository, PaymentService paymentService){
        this.orderRepository=orderRepository;
        this.personRepository=personRepository;
        this.paymentService=paymentService;
    }

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
    @GetMapping(value = "/getUser/{email}")
    public ResponseEntity<Integer> getUser(@PathVariable("email") String email){
        Persons persons = personRepository.findByEmail(email);
        return ResponseEntity.ok(persons.getPersonId());
    }
    @GetMapping(value = "/getUsers")
    public ResponseEntity<List<Persons>> getUsers(){
        List<Persons> persons = personRepository.findAll();
        return ResponseEntity.ok(persons);
    }
    @PostMapping(produces = "application/json", value = "/saveUser")
    public ResponseEntity<Persons> saveUser(@Valid @RequestBody Persons persons, BindingResult bindingResult){
        try {
            if (bindingResult.hasErrors()) {
                throw new Exception(bindingResult.getFieldErrors().iterator().next().getDefaultMessage());
            }
        }catch (Exception e) {
            ResponseEntity.ok(e.getMessage());
        }
        return ResponseEntity.ok(personRepository.save(persons));
    }
    @PostMapping(produces = "application/json", value = "/paymentGateway")
    public ResponseEntity<PaymentOrderResponse> paymentGateway(@Valid @RequestBody Orders orders, BindingResult bindingResult){
        Persons user = personRepository.findByPersonId(orders.getPersonId());
        PaymentModel paymentModel =PaymentModel.builder()
                .orderId(orders.getOrderId())
                .name(user.getUserName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .currency("INR")
                .amount(orders.getTotalMoney())
                .description("Secure Payment")
                .transactionId(String.valueOf(uuid))
                .createdAt("")
                .resourceUri("")
                .status(orders.getStatus())
                .build();
        System.out.println(paymentModel);
        PaymentOrderResponse paymentOrderResponse = paymentService.paymentService(paymentModel);
        return ResponseEntity.ok(paymentOrderResponse);
    }

}
