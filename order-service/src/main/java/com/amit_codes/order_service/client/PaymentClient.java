package com.amit_codes.order_service.client;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Data
@Component
public class PaymentClient {

    private Logger logger = LoggerFactory.getLogger(PaymentClient.class);

    public void processPayment(Long id, double total) {
        logger.info("Processing payment, id {}, total {}", id, total);
    }
}
