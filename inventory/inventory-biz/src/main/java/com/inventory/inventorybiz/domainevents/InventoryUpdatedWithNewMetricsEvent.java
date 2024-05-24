package com.inventory.inventorybiz.domainevents;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.context.ApplicationEvent;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
public class InventoryUpdatedWithNewMetricsEvent extends ApplicationEvent {

  private Object source;
  private Long merchantId;
  private Long productId;
  private Float productRating;

  public InventoryUpdatedWithNewMetricsEvent(Object source, Long merchantId, Long productId,
      Float productRating) {
    super(source);
    setMerchantId(merchantId);
    setProductId(productId);
    setProductRating(productRating);
  }
}
