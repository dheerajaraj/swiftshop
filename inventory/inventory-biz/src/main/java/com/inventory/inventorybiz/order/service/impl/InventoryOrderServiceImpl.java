package com.inventory.inventorybiz.order.service.impl;

import com.inventory.inventorybiz.inventory.InventoryDomain;
import com.inventory.inventorybiz.inventory.model.entity.WarehouseInventoryEntity;
import com.inventory.inventorybiz.merchant.MerchantDomain;
import com.inventory.inventorybiz.order.service.InventoryOrderService;
import com.inventory.inventorybiz.valueObj.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryOrderServiceImpl implements InventoryOrderService {

  private final InventoryDomain inventoryDomain;
  private final MerchantDomain merchantDomain;

  @Autowired
  public InventoryOrderServiceImpl(InventoryDomain inventoryDomain, MerchantDomain merchantDomain) {
    this.inventoryDomain = inventoryDomain;
    this.merchantDomain = merchantDomain;
  }

  @Override
  public void handleOrderCreatedEvent() {
    try {
      inventoryDomain.handleOrderCreated(WarehouseInventoryEntity.builder().build(),
          new UserId(123L));
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
