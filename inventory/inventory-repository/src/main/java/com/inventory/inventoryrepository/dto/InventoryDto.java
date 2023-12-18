package com.inventory.inventoryrepository.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventoryDto {
    private long id;
    private long productId;
    private long merchantId;
    private Integer stock;
    private String dateLastAdded;
    private Float lowStockThreshold;
}
