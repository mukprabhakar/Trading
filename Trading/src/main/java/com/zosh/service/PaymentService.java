package com.zosh.service;

import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;
import com.zosh.domain.PaymentMethod;
import com.zosh.modal.PaymentOrder;
import com.zosh.modal.User;
import com.zosh.response.PaymentResponse;

public interface PaymentService {

    PaymentOrder createOrder(User user, Long amount, PaymentMethod paymentMethod);

    PaymentOrder getPaymentOrderById(Long id) throws Exception;

    Boolean ProceedPaymentOrder(PaymentOrder paymentOrder,String paymentId) throws RazorpayException;

    PaymentResponse createRazorpayPayment(User user, Long amount) throws RazorpayException;

    PaymentResponse createStripepayPayment(User user, Long amount,Long orderid) throws StripeException;

}
