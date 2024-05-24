package com.inventory.inventorybiz.metrics;

import com.inventory.inventorybiz.domainevents.InventoryUpdatedWithNewMetricsEvent;
import com.inventory.inventorybiz.metrics.entity.MerchantMetricsEntity;
import com.inventory.inventorybiz.metrics.entity.ProductMetricsEntity;
import com.inventory.inventoryrepository.dto.MerchantMetricDto;
import com.inventory.inventoryrepository.dto.ProductMetricDto;
import com.inventory.inventoryrepository.mapper.MerchantMetricsMapper;
import com.inventory.inventoryrepository.mapper.ProductMetricsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class MetricsDomain {

  private final MerchantMetricsMapper merchantMetricsMapper;
  private final ProductMetricsMapper productMetricsMapper;

  @Async
  @Transactional(rollbackFor = Exception.class)
  @TransactionalEventListener
  public void handleInventoryUpdatedEvent(InventoryUpdatedWithNewMetricsEvent event) {
    ProductMetricsEntity productMetricsEntity = ProductMetricsEntity.event2Entity(event);
    ProductMetricDto productMetricDto = this.productMetricsMapper.selectByProductIdAndMerchantId(
        productMetricsEntity.getProductId(),
        productMetricsEntity.getMerchantId());
    if (productMetricDto == null) {
      productMetricsEntity.setTotalOrders(1);
      productMetricsEntity.setTotalUnratedOrders(
          productMetricsEntity.getProductRating() == null ? 1 : 0);
      productMetricsEntity.setAverageProductRating(
          productMetricsEntity.getProductRating() == null ? null
              : productMetricsEntity.getProductRating());
      productMetricsMapper.insert(ProductMetricsEntity.entity2Dto(productMetricsEntity));
    } else {
      ProductMetricsEntity retrievedProduct = ProductMetricsEntity.dto2Entity(productMetricDto);
      if (productMetricsEntity.getProductRating() == null) {
        productMetricsEntity.setTotalUnratedOrders(retrievedProduct.getTotalUnratedOrders() + 1);
      } else {
        productMetricsEntity.setAverageProductRating(retrievedProduct.getProductRating()
            .calculateAverageRating(retrievedProduct.getAverageProductRating(),
                retrievedProduct.getTotalUnratedOrders()));
      }
      productMetricsEntity.setTotalOrders(retrievedProduct.getTotalOrders() + 1);
      productMetricsMapper.update(ProductMetricsEntity.entity2Dto(productMetricsEntity));
    }

    MerchantMetricsEntity merchantMetricsEntity = MerchantMetricsEntity.event2Entity(event);
    MerchantMetricDto merchantMetricDto = this.merchantMetricsMapper.selectByMerchantId(
        merchantMetricsEntity.getMerchantId());
    if (merchantMetricDto == null) {
      merchantMetricsEntity.setTotalOrders(1);
      merchantMetricsEntity.setTotalUnratedOrders(
          merchantMetricsEntity.getProductRating() == null ? 1 : 0);
      merchantMetricsEntity.setAverageMerchantRating(
          merchantMetricsEntity.getProductRating() == null ? null
              : merchantMetricsEntity.getProductRating());
      merchantMetricsMapper.insert(MerchantMetricsEntity.entity2Dto(merchantMetricsEntity));
    } else {
      MerchantMetricsEntity retrievedMerchantMetric = MerchantMetricsEntity.dto2Entity(merchantMetricDto);
      if (merchantMetricsEntity.getProductRating() == null) {
        merchantMetricsEntity.setTotalUnratedOrders(retrievedMerchantMetric.getTotalUnratedOrders() + 1);
      } else {
        merchantMetricsEntity.setAverageMerchantRating(retrievedMerchantMetric.getProductRating()
            .calculateAverageRating(retrievedMerchantMetric.getAverageMerchantRating(),
                retrievedMerchantMetric.getTotalUnratedOrders()));
      }
      merchantMetricsEntity.setTotalOrders(retrievedMerchantMetric.getTotalOrders() + 1);
      merchantMetricsMapper.update(MerchantMetricsEntity.entity2Dto(merchantMetricsEntity));
    }
  }
}
