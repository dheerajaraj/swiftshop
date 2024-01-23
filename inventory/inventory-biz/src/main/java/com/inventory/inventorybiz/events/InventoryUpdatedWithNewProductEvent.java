package com.inventory.inventorybiz.events;


import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InventoryUpdatedWithNewProductEvent {
  private Long productId;
  private Long merchantId;
  private Long orderId;
  private Integer quantity;
  private Date invoiceDate;
  private String paymentStatus;
}
