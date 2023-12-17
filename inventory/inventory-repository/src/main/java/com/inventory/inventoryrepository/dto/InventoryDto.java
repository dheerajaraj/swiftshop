package com.inventory.inventoryrepository.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class InventoryDto {
    private long id;
    private long productId;
    private long merchantId;
    private Integer stock;
    private String dateLastAdded;
    private String lowStockThreshold;
}
