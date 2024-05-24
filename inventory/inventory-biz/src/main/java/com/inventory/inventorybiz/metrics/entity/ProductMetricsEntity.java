package com.inventory.inventorybiz.metrics.entity;

import com.inventory.inventorybiz.domainevents.InventoryUpdatedWithNewMetricsEvent;
import com.inventory.inventorybiz.valueObj.Rating;
import com.inventory.inventoryrepository.dto.ProductMetricDto;
import java.sql.Date;
import java.time.Instant;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductMetricsEntity {

  private Long merchantId;
  private Long productId;
  private Rating productRating;
  /**
   * Data retrieved/computed from DB: This is the average product rating for this particular product
   * for a particular merchant
   */
  private Rating averageProductRating;
  private Integer totalOrders;
  private Integer totalUnratedOrders;
  private Date updateDate;

  public static ProductMetricDto entity2Dto(ProductMetricsEntity entity) {
    if (entity == null) {
      return null;
    }
    return ProductMetricDto.builder().merchantId(entity.getMerchantId())
        .productId(entity.getProductId())
        .productRating(entity.getAverageProductRating().getRating())
        .totalOrders(entity.getTotalOrders()).totalUnratedOrders(entity.getTotalUnratedOrders())
        .updateDate(new Date(Instant.EPOCH.toEpochMilli()))
        .build();
  }

  public static ProductMetricsEntity dto2Entity(ProductMetricDto dto) {
    if (dto == null) {
      return null;
    }
    return ProductMetricsEntity.builder().merchantId(dto.getMerchantId())
        .productId(dto.getProductId())
        .averageProductRating(new Rating(dto.getProductRating()))
        .totalOrders(dto.getTotalOrders()).totalUnratedOrders(dto.getTotalUnratedOrders())
        .updateDate(dto.getUpdateDate())
        .build();
  }

  public static ProductMetricsEntity event2Entity(InventoryUpdatedWithNewMetricsEvent event) {
    if (event == null) {
      return null;
    }
    return ProductMetricsEntity.builder().merchantId(event.getMerchantId())
        .productId(event.getProductId()).productRating(
            event.getProductRating() == null ? null : new Rating(event.getProductRating()))
        .build();
  }
}
