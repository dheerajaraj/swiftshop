package com.inventory.inventorybiz.domainevents;


import java.sql.Date;

import com.inventory.inventorybiz.inventory.model.entity.WarehouseInventoryEntity;
import com.inventory.inventorybiz.inventory.model.entity.valueobj.reservation.Stock;
import com.inventory.inventorybiz.inventory.model.entity.valueobj.reservation.StockThreshold;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InventoryUpdatedWithNewProductEvent {
  private Long warehouseInventoryId;
  private Long productId;
  private Long merchantId;
  private Long orderId;
  private Long shopperId;
  private Integer quantity;
  private Date dateLastAdded;
  private String paymentStatus;
  private Float stockThreshold;

  public static WarehouseInventoryEntity getWarehouseInventoryEntity(InventoryUpdatedWithNewProductEvent entity) {
    if(entity == null){
      return null;
    }
    return WarehouseInventoryEntity.builder()
            .warehouseInventoryId(entity.getWarehouseInventoryId())
            .shopperId(entity.getShopperId())
            .productId(entity.getProductId()).merchantId(entity.getMerchantId())
            .stock(new Stock(entity.getQuantity()))
            .dateLastAdded(entity.getDateLastAdded())
            .stockThreshold(new StockThreshold(entity.getStockThreshold())).build();
  }
}
