package com.inventory.inventorybiz.order.model;

import lombok.NonNull;
import lombok.Value;

@Value
public class OrderCreatedEvent extends OrderEvent {

    @NonNull int quantity;
    @NonNull String paymentStatus;
    @NonNull String productCategory;


}
