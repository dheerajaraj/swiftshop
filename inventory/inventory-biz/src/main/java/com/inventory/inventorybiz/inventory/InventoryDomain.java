package com.inventory.inventorybiz.inventory;

import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
public class InventoryDomain {

    /**
     * 1. Update inventory tab with the correct quantity
     * 2. Update reservation tab if necessary by marking it as delete.
     */
    @Transactional(rollbackFor = Exception.class)
    public void handleOrderCreated() {
    }
}
