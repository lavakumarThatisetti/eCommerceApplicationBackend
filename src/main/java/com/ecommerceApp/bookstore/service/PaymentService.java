package com.ecommerceApp.bookstore.service;

import com.ecommerceApp.bookstore.model.PaymentModel;
import com.instamojo.wrapper.model.PaymentOrderResponse;

public interface PaymentService {
    PaymentOrderResponse paymentService(PaymentModel paymentModel);
}
