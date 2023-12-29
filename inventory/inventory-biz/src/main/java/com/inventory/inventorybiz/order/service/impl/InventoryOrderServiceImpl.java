package com.inventory.inventorybiz.order.service.impl;

import com.inventory.inventorybiz.inventory.InventoryDomain;
import com.inventory.inventorybiz.inventory.model.entity.WarehouseInventoryEntity;
import com.inventory.inventorybiz.merchant.MerchantDomain;
import com.inventory.inventorybiz.order.service.InventoryOrderService;
import com.inventory.inventorybiz.valueObj.Rating;
import com.inventory.inventorybiz.valueObj.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryOrderServiceImpl implements InventoryOrderService {

  private final InventoryDomain inventoryDomain;

  @Autowired
  public InventoryOrderServiceImpl(InventoryDomain inventoryDomain) {
    this.inventoryDomain = inventoryDomain;
  }

  @Override
  public void handleOrderCreatedEvent() {
    try {
      inventoryDomain.handleOrderCreated(WarehouseInventoryEntity.builder().build(),
          new UserId(123L), new Rating(4.7f));
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
