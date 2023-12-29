package com.inventory.inventoryrepository.dto;

import java.sql.Date;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MerchantMetricDto {

  private Long merchantId;
  private Float merchantRating;
  private Integer totalOrders;
  private Integer totalUnratedOrders;
  private Date updateDate;
}
