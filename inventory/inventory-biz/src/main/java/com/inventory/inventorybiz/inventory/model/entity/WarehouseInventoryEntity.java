package com.inventory.inventorybiz.inventory.model.entity;

import com.inventory.inventorybiz.inventory.model.entity.valueobj.warehouseInventory.WarehouseInventoryId;
import com.inventory.inventorybiz.valueObj.MerchantId;
import com.inventory.inventorybiz.valueObj.ProductId;
import lombok.Data;

import java.util.Date;

@Data
public class WarehouseInventoryEntity {
    private WarehouseInventoryId id;
    private ProductId productId;
    private MerchantId merchantId;
    private int stock;
    private Date dateLastAdded;
    private int lowStockThreshold;
}
