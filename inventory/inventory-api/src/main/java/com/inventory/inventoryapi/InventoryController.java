package com.inventory.inventoryapi;

import com.basedomain.basedomain.event.orderinventory.OrderCreatedEvent;
import com.inventory.inventorybiz.inventory.InventoryDomain;
import com.inventory.inventorybiz.inventory.model.entity.WarehouseInventoryEntity;
import com.inventory.inventorybiz.inventory.model.entity.valueobj.reservation.Stock;
import com.inventory.inventorybiz.inventory.model.entity.valueobj.reservation.StockThreshold;
import com.inventory.inventorybiz.valueObj.Rating;
import com.inventory.inventorybiz.valueObj.UserId;
import java.io.InvalidObjectException;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class InventoryController {

  private final InventoryDomain inventoryDomain;

  @KafkaListener(topics = "order")
  public void handleOrderCreatedEvent(OrderCreatedEvent orderCreatedEvent)
      throws InvalidObjectException {
    WarehouseInventoryEntity warehouseInventoryEntity = WarehouseInventoryEntity.builder()
        .productId(orderCreatedEvent.getProductId()).merchantId(orderCreatedEvent.getMerchantId())
        .stock(new Stock(orderCreatedEvent.getStockQuantity()))
        .dateLastAdded(orderCreatedEvent.getDateLastAdded())
        .stockThreshold(new StockThreshold(orderCreatedEvent.getLowStockThreshold())).build();
    this.inventoryDomain.handleOrderCreated(warehouseInventoryEntity,
        new UserId(orderCreatedEvent.getUserId()), new Rating(
            orderCreatedEvent.getRating()), orderCreatedEvent.getOrderId());
  }

}
