package com.inventory.inventoryrepository.mapper.impl;

import com.inventory.inventoryrepository.dto.MerchantMetricDto;
import org.apache.ibatis.jdbc.SQL;

public class MerchantMetricsUtil {

  private static final String ALL_COLUMNS = "merchant_id, merchant_rating, total_orders, total_unrated_orders, update_date";


  public String insert(MerchantMetricDto dto) {
    return new SQL().INSERT_INTO("merchant_metrics_tab")
        .VALUES(ALL_COLUMNS,
            "#{dto.merchantId},#{dto.merchantRating},#{dto.totalOrders},#{dto.totalUnratedOrders},#{dto.updateDate}")
        .toString();
  }

  public String update(MerchantMetricDto dto) {
    SQL sqlBuilder = new SQL() {{
      UPDATE("merchant_metrics_tab");
    }};
    if (dto.getMerchantRating() != null) {
      sqlBuilder.SET("merchant_rating = #{dto.merchantRating}");
    }
    if (dto.getTotalOrders() != null) {
      sqlBuilder.SET("total_orders = #{dto.totalOrders}");
    }
    if (dto.getTotalUnratedOrders() != null) {
      sqlBuilder.SET("total_unrated_orders = #{dto.totalUnratedOrders}");
    }
    if (dto.getUpdateDate() != null) {
      sqlBuilder.SET("update_date = #{dto.updateDate}");
    }
    sqlBuilder.WHERE("merchant_id = #{dto.merchantId}");
    return sqlBuilder.toString();
  }

  public String selectByMerchantId(Long merchantId) {
    return new SQL() {{
      SELECT(ALL_COLUMNS);
      FROM("merchant_metrics_tab");
      WHERE("merchant_id = #{merchantId}");
    }}.toString();
  }

}
