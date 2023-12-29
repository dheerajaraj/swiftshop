package com.inventory.inventoryrepository.mapper;

import com.inventory.inventoryrepository.dto.ProductMetricDto;
import com.inventory.inventoryrepository.mapper.impl.ProductMetricsUtil;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

public interface ProductMetricsMapper {

  @InsertProvider(type= ProductMetricsUtil.class, method="insert")
  int insert(ProductMetricDto dto);

  @UpdateProvider(type= ProductMetricsUtil.class, method="update")
  int update(ProductMetricDto dto);

  @SelectProvider(type = ProductMetricsUtil.class, method = "selectByProductIdAndMerchantId")
  @Results(id = "productMetricData", value = {
      @Result(column = "product_id", property = "productId"),
      @Result(column = "merchant_id", property = "merchantId"),
      @Result(column = "product_rating", property = "productRating"),
      @Result(column = "total_orders", property = "totalOrders"),
      @Result(column = "total_unrated_orders", property = "totalUnratedOrders"),
      @Result(column = "update_date", property = "updateDate")
  })
  ProductMetricDto selectByProductIdAndMerchantId(Long productId, Long merchantId);
}
