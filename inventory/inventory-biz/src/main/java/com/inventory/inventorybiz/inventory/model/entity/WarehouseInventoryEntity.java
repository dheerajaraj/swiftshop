package com.inventory.inventorybiz.inventory.model.entity;

import com.inventory.inventorybiz.inventory.model.entity.valueobj.reservation.Stock;
import com.inventory.inventorybiz.inventory.model.entity.valueobj.reservation.StockThreshold;
import com.inventory.inventoryrepository.dto.InventoryDto;
import lombok.Builder;
import lombok.Data;

import java.sql.Date;

@Data
@Builder
public class WarehouseInventoryEntity {

  private Long warehouseInventoryId;
  private Long productId;
  private Long merchantId;
  private Stock stock;
  private Date dateLastAdded;
  private StockThreshold stockThreshold;

  public static InventoryDto domain2Dto(WarehouseInventoryEntity entity) {
    if (entity == null) {
      return null;
    }
    return InventoryDto.builder().productId(entity.getProductId())
        .merchantId(entity.getMerchantId())
        .stock(entity.getStock() == null || entity.getStock().getQuantity() == null ? null
            : entity.getStock().getQuantity()).dateLastAdded(
            entity.getDateLastAdded() == null ? null : entity.getDateLastAdded().toString())
        .lowStockThreshold(entity.getStockThreshold() == null
            || entity.getStockThreshold().getLowStockThreshold() == null ? null
            : entity.getStockThreshold().getLowStockThreshold()).build();
  }

  public static WarehouseInventoryEntity dto2Domain(InventoryDto dto) {
    if (dto == null) {
      return null;
    }
    return WarehouseInventoryEntity.builder().warehouseInventoryId(dto.getId())
        .productId(dto.getProductId()).merchantId(dto.getMerchantId())
        .stock(new Stock(dto.getStock())).dateLastAdded(
            Date.valueOf(dto.getDateLastAdded()))
        .stockThreshold(new StockThreshold(dto.getLowStockThreshold())).build();
  }
}
