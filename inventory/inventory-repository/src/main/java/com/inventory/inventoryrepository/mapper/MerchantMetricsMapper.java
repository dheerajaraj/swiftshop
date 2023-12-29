package com.inventory.inventoryrepository.mapper;

import com.inventory.inventoryrepository.dto.MerchantMetricDto;
import com.inventory.inventoryrepository.mapper.impl.MerchantMetricsUtil;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

public interface MerchantMetricsMapper {

  @InsertProvider(type= MerchantMetricsUtil.class, method="insert")
  int insert(MerchantMetricDto dto);

  @UpdateProvider(type= MerchantMetricsUtil.class, method="update")
  int update(MerchantMetricDto dto);

  @SelectProvider(type = MerchantMetricsUtil.class, method = "selectByMerchantId")
  @Results(id = "merchantMetricData", value = {
      @Result(column = "merchant_id", property = "merchantId"),
      @Result(column = "merchant_rating", property = "merchantRating"),
      @Result(column = "total_orders", property = "totalOrders"),
      @Result(column = "total_unrated_orders", property = "totalUnratedOrders"),
      @Result(column = "update_date", property = "updateDate")
  })
  MerchantMetricDto selectByMerchantId(Long merchantId);

}
