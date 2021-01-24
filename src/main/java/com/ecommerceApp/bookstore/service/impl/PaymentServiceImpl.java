package com.ecommerceApp.bookstore.service.impl;

//import com.ecommerceApp.bookstore.config.AppConfig;
import com.ecommerceApp.bookstore.config.AppConfig;
import com.ecommerceApp.bookstore.model.PaymentModel;
import com.ecommerceApp.bookstore.repository.PaymentRepository;
import com.ecommerceApp.bookstore.service.PaymentService;
import com.ecommerceApp.bookstore.util.ConstantObjects;
import com.instamojo.wrapper.api.ApiContext;
import com.instamojo.wrapper.api.Instamojo;
import com.instamojo.wrapper.api.InstamojoImpl;
import com.instamojo.wrapper.exception.ConnectionException;
import com.instamojo.wrapper.exception.HTTPException;
import com.instamojo.wrapper.model.PaymentOrder;
import com.instamojo.wrapper.model.PaymentOrderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final AppConfig appConfig;

    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository,AppConfig appConfig){
        this.paymentRepository=paymentRepository;
        this.appConfig = appConfig;
    }
    @Override
    public PaymentOrderResponse paymentService(PaymentModel paymentModel) {
        System.out.println(paymentModel);
        Instamojo api;
        try {
            ApiContext context = ApiContext.create(
                    ConstantObjects.CLIENT_ID, ConstantObjects.CLIENT_SECRET, ApiContext.Mode.TEST);
            api = new InstamojoImpl(context);
            PaymentOrder order = new PaymentOrder();
            order.setName(paymentModel.getName());
            order.setEmail(paymentModel.getEmail());
            order.setPhone(paymentModel.getPhoneNumber());
            order.setCurrency(paymentModel.getCurrency());
            order.setAmount(paymentModel.getAmount());
            order.setDescription(paymentModel.getDescription());
            order.setRedirectUrl(appConfig.getAppUrl()+"yourOrders");
            order.setWebhookUrl("https://github.com/lavakumarThatisetti");
            order.setTransactionId(paymentModel.getTransactionId());

            PaymentOrderResponse paymentOrderResponse = api.createPaymentOrder(order);
            System.out.println(paymentOrderResponse.getPaymentOrder().getId());
            System.out.println(paymentOrderResponse.getPaymentOrder().getStatus());

            paymentModel.setStatus(paymentOrderResponse.getPaymentOrder().getStatus());
            paymentModel.setCreatedAt(paymentOrderResponse.getPaymentOrder().getCreatedAt());
            paymentModel.setResourceUri(paymentOrderResponse.getPaymentOrder().getResourceUri());
            System.out.println(paymentModel);
            paymentRepository.save(paymentModel);
            System.out.println(paymentOrderResponse);
            return paymentOrderResponse;

        } catch (HTTPException e) {
            System.out.println(e.getStatusCode());
            System.out.println(e.getMessage());
            System.out.println(e.getJsonPayload());

        } catch (ConnectionException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
