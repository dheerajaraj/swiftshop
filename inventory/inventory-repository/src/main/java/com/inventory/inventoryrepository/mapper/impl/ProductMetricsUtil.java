package com.inventory.inventoryrepository.mapper.impl;

import com.inventory.inventoryrepository.dto.MerchantMetricDto;
import com.inventory.inventoryrepository.dto.ProductMetricDto;
import org.apache.ibatis.jdbc.SQL;

public class ProductMetricsUtil {

  private static final String ALL_COLUMNS = "product_id, merchant_id, product_rating, total_orders, total_unrated_orders, update_date";

  public String insert(ProductMetricDto dto) {
    return new SQL().INSERT_INTO("product_metrics_tab")
        .VALUES(ALL_COLUMNS,
            "#{dto.productId},#{dto.merchantId},#{dto.productRating},#{dto.totalOrders},#{dto.totalUnratedOrders},#{dto.updateDate}")
        .toString();
  }

  public String update(ProductMetricDto dto) {
    SQL sqlBuilder = new SQL() {{
      UPDATE("merchant_metrics_tab");
    }};
    if (dto.getProductRating() != null) {
      sqlBuilder.SET("product_rating = #{dto.productRating}");
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
    sqlBuilder.AND();
    sqlBuilder.WHERE("product_id = #{dto.productId}");
    return sqlBuilder.toString();
  }

  public String selectByProductIdAndMerchantId(Long productId, Long merchantId) {
    return new SQL() {{
      SELECT(ALL_COLUMNS);
      FROM("product_metrics_tab");
      WHERE("product_id = #{productId}");
      AND();
      WHERE("merchant_id = #{merchantId}");
    }}.toString();
  }
}
