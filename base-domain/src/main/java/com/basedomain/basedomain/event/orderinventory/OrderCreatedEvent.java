package com.basedomain.basedomain.event.orderinventory;

import java.sql.Date;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderCreatedEvent {
  private Long orderId;
  private Long productId;
  private Long merchantId;
  private Long shopperId;
  private Integer orderQuantity;
  private String action;
  private String status;
  private Float rating;
  private Date dateLastAdded;
  private Float lowStockThreshold;
}
