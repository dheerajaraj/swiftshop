package com.swiftshop.inventory.inventory_biz.order.service;

import com.swiftshop.inventory.inventory_biz.inventory.InventoryDomain;
import com.swiftshop.inventory.inventory_biz.merchant.MerchantDomain;
import org.springframework.beans.factory.annotation.Autowired;


public class InventoryOrderService {

    private final InventoryDomain inventoryDomain;
    private final MerchantDomain merchantDomain;

    @Autowired
    public InventoryOrderService(InventoryDomain inventoryDomain,
                                 MerchantDomain merchantDomain){
        this.inventoryDomain = inventoryDomain;
        this.merchantDomain = merchantDomain;
    }

    public void handleOrderCreatedEvent(){
        inventoryDomain.handleOrderCreated();
        //merchantDomain.handleOrderCreated();
    }
}
