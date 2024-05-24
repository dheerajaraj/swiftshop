package com.inventory.inventoryapi;

import com.basedomain.basedomain.event.orderinventory.OrderCreatedEvent;
import com.inventory.inventorybiz.domainevents.InventoryUpdatedWithNewProductEvent;
import com.inventory.inventorybiz.inventory.InventoryDomain;
import com.inventory.inventorybiz.inventory.model.entity.WarehouseInventoryEntity;
import com.inventory.inventorybiz.inventory.model.entity.valueobj.reservation.Stock;
import com.inventory.inventorybiz.inventory.model.entity.valueobj.reservation.StockThreshold;
import com.inventory.inventorybiz.valueObj.Rating;

import java.io.InvalidObjectException;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequiredArgsConstructor
@RestController
public class InventoryController implements InventoryOrderService {

    private final InventoryDomain inventoryDomain;

    @PostMapping
    public void handleOrderCreatedEvent(@RequestBody OrderCreatedEvent orderCreatedEvent)
            throws InvalidObjectException {
        InventoryUpdatedWithNewProductEvent inventoryUpdatedWithNewProductEvent = convertOrderCreatedEventToInventoryUpdatedEvent(orderCreatedEvent);
        this.inventoryDomain.handleOrderCreated(inventoryUpdatedWithNewProductEvent, new Rating(orderCreatedEvent.getRating()));
    }
}
