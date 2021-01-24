package com.ecommerceApp.bookstore.repository;

import com.ecommerceApp.bookstore.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrderRepository extends JpaRepository<Orders,Long> {

      @Query(value = "SELECT orders.order_id FROM orders where person_id:personId",nativeQuery = true)
      List<String> getAllOrders(int personId);
}
