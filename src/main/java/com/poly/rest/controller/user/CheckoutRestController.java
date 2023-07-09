package com.poly.rest.controller.user;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poly.service.PayPalHttpClient;
import com.poly.model.paypal.PayPalResponse;
import com.poly.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
public class CheckoutRestController {

    private final PayPalHttpClient payPalHttpClient;
    @Autowired
    public CheckoutRestController(PayPalHttpClient payPalHttpClient) {
        this.payPalHttpClient = payPalHttpClient;
    }
    @PostMapping("/rest/checkout")
    public ResponseEntity<PayPalResponse> create(@RequestBody JsonNode orderData) {
        try {
            var order = new ObjectMapper().treeToValue(orderData, Order.class);
            var orderResponse = payPalHttpClient.createOrder(order);
            System.out.println("Response " + orderResponse);
            var test = new PayPalResponse();
            test.setUrl(orderResponse);
            return ResponseEntity.ok(test);
        } catch (Exception e) {
            return null;
        }
    }
}
