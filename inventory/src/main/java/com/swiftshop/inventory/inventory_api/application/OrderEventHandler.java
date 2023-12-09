package com.swiftshop.inventory.inventory_api.application;

import com.inventory.inventorybiz.order.model.OrderCreatedEvent;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class OrderEventHandler {

    //subscriber class to kafka

    public void handle(OrderCreatedEvent event){
        /**
         * 1. Update inventory tab with the correct quantity
         * 2. Update reservation tab if necessary by marking it as delete.
         * 3. Add entry into merchant invoice tab.
         */
    }
}
