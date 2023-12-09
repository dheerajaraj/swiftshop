package com.inventory.inventorybiz.order.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
public class OrderEvent {
    protected String eventId;
    protected String orderId;
    protected String productId;
    protected String merchantId;
    protected Instant eventTime;
}
