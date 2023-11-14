package com.swiftshop.inventory.inventory_biz.inventory.entity;

import com.swiftshop.inventory.inventory_biz.common.valueObj.MerchantId;
import com.swiftshop.inventory.inventory_biz.common.valueObj.ProductId;
import com.swiftshop.inventory.inventory_biz.inventory.valueobj.warehouseInventory.WarehouseInventoryId;
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
