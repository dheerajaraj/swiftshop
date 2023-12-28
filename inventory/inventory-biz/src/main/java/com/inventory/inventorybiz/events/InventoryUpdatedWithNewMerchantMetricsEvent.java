package com.inventory.inventorybiz.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.context.ApplicationEvent;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
public class InventoryUpdatedWithNewMerchantMetricsEvent extends ApplicationEvent {

  private Long merchantId;
  private Float productRating;

  public InventoryUpdatedWithNewMerchantMetricsEvent(Object source, Long merchantId,
      Float productRating) {
    super(source);
    setMerchantId(merchantId);
    setProductRating(productRating);
  }

}
