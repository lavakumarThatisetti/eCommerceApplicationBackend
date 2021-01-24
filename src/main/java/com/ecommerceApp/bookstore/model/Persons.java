package com.ecommerceApp.bookstore.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Data
@Entity
@Access(AccessType.FIELD)
@Table(name = "persons")
public class Persons {

    @Id
    @Column(name= "person_id")
    @GeneratedValue(strategy= GenerationType.IDENTITY) //This if for Auto Increment
    private Integer personId;

    @Column(name= "user_name")
    @NotNull
    private String userName;

    @Column(name="email")
    @NotNull
    private String email;

    @Column(name= "phone_number")
    @NotNull
    private String phoneNumber;

}
