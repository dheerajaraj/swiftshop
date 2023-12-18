package com.inventory.inventorybiz.inventory.model.entity;

import com.inventory.inventorybiz.inventory.model.entity.valueobj.reservation.ReservationId;
import com.inventory.inventorybiz.valueObj.MerchantId;
import com.inventory.inventorybiz.valueObj.ProductId;
import com.inventory.inventorybiz.valueObj.UserId;
import com.inventory.inventoryrepository.dto.ReservationDto;
import lombok.Data;

import java.sql.Date;

@Data
public class ReservationEntity {

  private ReservationId id;
  private ProductId productId;
  private UserId userId;
  private MerchantId merchantId;
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
    return ReservationDto.builder().productId(entity.getProductId().getId())
        .userId(entity.getUserId().getId()).quantity(entity.getQuantity())
        .action(entity.getAction().name()).expired(entity.getExpired())
        .dateAdded(entity.getDateAdded()).build();
  }
}

