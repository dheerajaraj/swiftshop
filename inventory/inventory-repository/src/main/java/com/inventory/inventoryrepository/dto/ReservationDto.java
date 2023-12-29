package com.inventory.inventoryrepository.dto;

import java.sql.Date;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReservationDto {
  private Long id;
  private Long productId;
  private Long userId;
  private Long merchantId;
  private Integer quantity;
  private String action;
  private Boolean expired;
  private Date dateAdded;

}
