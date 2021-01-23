package com.ecommerceApp.bookstore.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Entity
@Access(AccessType.FIELD)
@Builder
@Table(name = "payment_info")
public class PaymentModel {

    @Id
    @Column(name= "id")
    @GeneratedValue(strategy= GenerationType.IDENTITY) //This if for Auto Increment
    private Long id;

    @Column(name= "order_id")
    @NotNull
    private Long orderId;

    @Column(name="name")
    @NotNull
    private String name;

    @Column(name="email")
    @NotNull
    private String email;

    @Column(name= "phone_number")
    @NotNull
    private String phoneNumber;

    @Column(name= "currency")
    private String currency;

    @Column(name= "amount")
    @NotNull
    private double amount;

    @Column(name= "description")
    private String description;

    @Column(name= "transaction_id")
    @NotNull
    private String transactionId;

    @Column(name= "created_at")
    private String createdAt;

    @Column(name= "resource_uri")
    private String resourceUri;

    @Column(name= "status")
    private String status;
}