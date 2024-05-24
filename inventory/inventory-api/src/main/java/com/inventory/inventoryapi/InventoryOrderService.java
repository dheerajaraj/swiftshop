package com.inventory.inventoryapi;

import com.basedomain.basedomain.event.orderinventory.OrderCreatedEvent;
import com.inventory.inventorybiz.domainevents.InventoryUpdatedWithNewProductEvent;
import org.springframework.stereotype.Service;

import java.io.InvalidObjectException;

@Service
public interface InventoryOrderService {

    void handleOrderCreatedEvent(OrderCreatedEvent orderCreatedEvent) throws InvalidObjectException;

    default InventoryUpdatedWithNewProductEvent convertOrderCreatedEventToInventoryUpdatedEvent(OrderCreatedEvent orderCreatedEvent) {
        return InventoryUpdatedWithNewProductEvent.builder()
                .shopperId(orderCreatedEvent.getShopperId())
                .productId(orderCreatedEvent.getProductId()).merchantId(orderCreatedEvent.getMerchantId())
                .quantity(orderCreatedEvent.getOrderQuantity())
                .dateLastAdded(orderCreatedEvent.getDateLastAdded())
                .stockThreshold(orderCreatedEvent.getLowStockThreshold()).build();
    }
}
