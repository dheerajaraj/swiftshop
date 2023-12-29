package com.inventory.inventoryrepository.dto;

import java.sql.Date;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductMetricDto {

  private Long productId;
  private Long merchantId;
  private Float productRating;
  private Integer totalOrders;
  private Integer totalUnratedOrders;
  private Date updateDate;

}
