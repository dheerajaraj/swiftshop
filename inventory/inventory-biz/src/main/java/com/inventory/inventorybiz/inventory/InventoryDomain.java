package com.inventory.inventorybiz.inventory;

import com.inventory.inventorybiz.inventory.model.entity.WarehouseInventoryEntity;
import com.inventory.inventoryrepository.mapper.InventoryMapper;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@AllArgsConstructor
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class InventoryDomain {

    private final InventoryMapper inventoryMapper;
    /**
     * 1. Update inventory tab with the correct quantity
     * 2. Update reservation tab if necessary by marking it as delete.
     */
    @Transactional(rollbackFor = Exception.class)
    public void handleOrderCreated(WarehouseInventoryEntity entity) {
        this.inventoryMapper.insert(WarehouseInventoryEntity.domain2Dto(entity));
    }
}
