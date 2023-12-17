package com.inventory.inventorybiz.inventory.model.entity;

import com.inventory.inventorybiz.inventory.model.entity.valueobj.warehouseInventory.WarehouseInventoryId;
import com.inventory.inventorybiz.valueObj.MerchantId;
import com.inventory.inventorybiz.valueObj.ProductId;
import com.inventory.inventoryrepository.dto.InventoryDto;
import lombok.Data;

import java.util.Date;

@Data
public class WarehouseInventoryEntity {

  private WarehouseInventoryId warehouseInventoryId;
  private ProductId productId;
  private MerchantId merchantId;
  private Integer stock;
  private Date dateLastAdded;
  private Float lowStockThreshold;

  public static InventoryDto domain2Dto(WarehouseInventoryEntity entity) {
    if (entity == null) {
      return null;
    }
    return InventoryDto.builder().productId(entity.getProductId().getId())
        .merchantId(entity.getMerchantId().getId())
        .stock(entity.getStock() == null ? null : entity.getStock()).dateLastAdded(
            entity.getDateLastAdded() == null ? null : entity.getDateLastAdded().toString())
        .lowStockThreshold(entity.getLowStockThreshold() == null ? null
            : String.valueOf(entity.getLowStockThreshold())).build();
  }
}
