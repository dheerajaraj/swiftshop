package com.inventory.inventorybiz.order.model;

import lombok.Data;
import lombok.NonNull;
import lombok.Value;

@Data
public class OrderCreatedEvent extends OrderEvent {

    int quantity;
    String paymentStatus;
    String productCategory;

}
