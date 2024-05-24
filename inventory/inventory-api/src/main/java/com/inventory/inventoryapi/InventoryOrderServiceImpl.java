package com.inventory.inventoryapi;

import com.basedomain.basedomain.event.orderinventory.OrderCreatedEvent;
import com.inventory.inventorybiz.domainevents.InventoryUpdatedWithNewProductEvent;
import com.inventory.inventorybiz.inventory.InventoryDomain;
import com.inventory.inventorybiz.valueObj.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.InvalidObjectException;

@Service
public class InventoryOrderServiceImpl implements InventoryOrderService {

    private final InventoryDomain inventoryDomain;

    @Autowired
    public InventoryOrderServiceImpl(InventoryDomain inventoryDomain) {
        this.inventoryDomain = inventoryDomain;
    }

    @KafkaListener(topics = "order")
    public void handleOrderCreatedEvent(OrderCreatedEvent orderCreatedEvent) throws InvalidObjectException {
        InventoryUpdatedWithNewProductEvent inventoryUpdatedWithNewProductEvent = convertOrderCreatedEventToInventoryUpdatedEvent(orderCreatedEvent);
        this.inventoryDomain.handleOrderCreated(inventoryUpdatedWithNewProductEvent, new Rating(orderCreatedEvent.getRating()));
    }

    @Override
    public InventoryUpdatedWithNewProductEvent convertOrderCreatedEventToInventoryUpdatedEvent(OrderCreatedEvent orderCreatedEvent) {
        return InventoryOrderService.super.convertOrderCreatedEventToInventoryUpdatedEvent(orderCreatedEvent);
    }
}
