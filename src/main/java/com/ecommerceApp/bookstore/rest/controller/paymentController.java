package com.ecommerceApp.bookstore.rest.controller;

import com.ecommerceApp.bookstore.model.Orders;
import com.ecommerceApp.bookstore.model.PaymentModel;
import com.ecommerceApp.bookstore.model.Persons;
import com.ecommerceApp.bookstore.repository.OrderRepository;
import com.ecommerceApp.bookstore.repository.PersonRepository;
import com.ecommerceApp.bookstore.service.PaymentService;
import com.instamojo.wrapper.model.PaymentOrderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/payment")
public class paymentController {

    private PersonRepository personRepository;
    private PaymentService paymentService;

    @Autowired
    public paymentController(PersonRepository personRepository,PaymentService paymentService){
        this.personRepository=personRepository;
        this.paymentService=paymentService;
    }

    @PostMapping(produces = "application/json", value = "/paymentGateway")
    public ResponseEntity<PaymentOrderResponse> paymentGateway(@Valid @RequestBody Orders orders, BindingResult bindingResult){
        System.out.println(orders.getOrderId());
        Persons user = personRepository.findByPersonId(orders.getPersonId());
        System.out.println(user.getEmail());
        UUID uuid = UUID.randomUUID();
        PaymentModel paymentModel =PaymentModel.builder()
                .orderId(orders.getOrderId())
                .orderedBooks(orders.getBookIds())
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
        return ResponseEntity.ok(paymentService.paymentService(paymentModel));
    }
}
