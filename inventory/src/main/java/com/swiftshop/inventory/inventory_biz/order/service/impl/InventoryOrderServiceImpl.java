package com.swiftshop.inventory.inventory_biz.order.service.impl;

import com.swiftshop.inventory.inventory_biz.inventory.InventoryDomain;
import com.swiftshop.inventory.inventory_biz.merchant.MerchantDomain;
import com.swiftshop.inventory.inventory_biz.order.service.InventoryOrderService;
import org.springframework.beans.factory.annotation.Autowired;


public class InventoryOrderServiceImpl implements InventoryOrderService {

    private final InventoryDomain inventoryDomain;
    private final MerchantDomain merchantDomain;

    @Autowired
    public InventoryOrderServiceImpl(InventoryDomain inventoryDomain,
                                     MerchantDomain merchantDomain){
        this.inventoryDomain = inventoryDomain;
        this.merchantDomain = merchantDomain;
    }

    @Override
    public void handleOrderCreatedEvent(){
        inventoryDomain.handleOrderCreated();
        merchantDomain.handleOrderCreated();
    }
}
