package com.ecommerceApp.bookstore.model;

import lombok.Data;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
@Access(AccessType.FIELD)
@Table(name = "orders")
public class Orders {
    @Id
    @Column(name= "order_id")
    @GeneratedValue(strategy= GenerationType.IDENTITY) //This if for Auto Increment
    private Long orderId;

    @Column(name= "person_id")
    @NotNull
    private Integer personId;

    @Column(name= "book_ids")
    @NotNull
    private String bookIds;

    @Column(name= "total_items")
    @NotNull
    private Integer totalItems;

    @Column(name= "total_money")
    @NotNull
    private Integer totalMoney;

    @Column(name= "status")
    @NotNull
    private String status;
}
