package com.inventory.inventorybiz.metrics.entity;

import com.inventory.inventorybiz.events.InventoryUpdatedWithNewMetricsEvent;
import com.inventory.inventorybiz.valueObj.Rating;
import com.inventory.inventoryrepository.dto.MerchantMetricDto;
import com.inventory.inventoryrepository.dto.ProductMetricDto;
import java.sql.Date;
import java.time.Instant;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MerchantMetricsEntity {

  private Long merchantId;
  private Rating productRating;

  /**
   * Results from DB: Merchant rating is the average of all the product ratings
   */
  private Rating averageMerchantRating;
  private Integer totalOrders;
  private Integer totalUnratedOrders;
  private Date updateDate;


  public static MerchantMetricDto entity2Dto(MerchantMetricsEntity entity) {
    if (entity == null) {
      return null;
    }
    return MerchantMetricDto.builder().merchantId(entity.getMerchantId())
        .merchantRating(entity.getAverageMerchantRating().getRating())
        .totalOrders(entity.getTotalOrders()).totalUnratedOrders(entity.getTotalUnratedOrders())
        .updateDate(new Date(Instant.EPOCH.toEpochMilli()))
        .build();
  }

  public static MerchantMetricsEntity dto2Entity(MerchantMetricDto dto) {
    if (dto == null) {
      return null;
    }
    return MerchantMetricsEntity.builder().merchantId(dto.getMerchantId())
        .averageMerchantRating(new Rating(dto.getMerchantRating()))
        .totalOrders(dto.getTotalOrders()).totalUnratedOrders(dto.getTotalUnratedOrders())
        .updateDate(dto.getUpdateDate())
        .build();
  }

  public static MerchantMetricsEntity event2Entity(InventoryUpdatedWithNewMetricsEvent event) {
    if (event == null) {
      return null;
    }
    return MerchantMetricsEntity.builder().merchantId(event.getMerchantId())
        .productRating(
            event.getProductRating() == null ? null : new Rating(event.getProductRating())).build();
  }

}
