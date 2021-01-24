package com.ecommerceApp.bookstore.repository;

import com.ecommerceApp.bookstore.model.PaymentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentModel,Long> {

}
