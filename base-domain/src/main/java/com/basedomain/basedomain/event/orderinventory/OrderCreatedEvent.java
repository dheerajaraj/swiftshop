package com.basedomain.basedomain.event.orderinventory;

import java.sql.Date;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderCreatedEvent {
  private Long productId;
  private Long merchantId;
  private Long userId;
  private Long orderId;
  private Float rating;
  private Integer stockQuantity;
  private Date dateLastAdded;
  private Float lowStockThreshold;

}
