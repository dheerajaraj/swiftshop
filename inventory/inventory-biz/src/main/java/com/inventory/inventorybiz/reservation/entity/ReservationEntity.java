package com.inventory.inventorybiz.reservation.entity;

import com.inventory.inventoryrepository.dto.ReservationDto;
import lombok.Data;

import java.sql.Date;

@Data
public class ReservationEntity {

  private Long id;
  private Long productId;
  private Long userId;
  private Long merchantId;
  private Integer quantity;
  private ReservationActionEnum action;
  private Boolean expired;
  private Date dateAdded;

  public enum ReservationActionEnum {
    CART,
    ORDER_PLACED,
    PAID
  }

  public static ReservationDto domain2Dto(ReservationEntity entity) {
    if (entity == null) {
      return null;
    }
    return ReservationDto.builder().productId(entity.getProductId())
        .userId(entity.getUserId()).merchantId(entity.getMerchantId())
        .quantity(entity.getQuantity())
        .action(entity.getAction().name()).expired(entity.getExpired())
        .dateAdded(entity.getDateAdded()).build();
  }
}

