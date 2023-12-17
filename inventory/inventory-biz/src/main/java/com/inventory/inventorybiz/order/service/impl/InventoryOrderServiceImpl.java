package com.inventory.inventorybiz.order.service.impl;

import com.inventory.inventorybiz.inventory.InventoryDomain;
import com.inventory.inventorybiz.merchant.MerchantDomain;
import com.inventory.inventorybiz.order.service.InventoryOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class InventoryOrderServiceImpl implements InventoryOrderService {

    private final InventoryDomain inventoryDomain;
    private final MerchantDomain merchantDomain;

    @Override
    public void handleOrderCreatedEvent() {
        //inventoryDomain.handleOrderCreated();
    }
}
