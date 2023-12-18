package com.inventory.inventoryrepository.mapper.impl;

import com.inventory.inventoryrepository.dto.InventoryDto;
import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.jdbc.SqlBuilder;

public class InventorySqlUtil {

  private static final String ALL_COLUMNS = "product_id, merchant_id, stock, date_last_added, low_stock_threshold";

  public String insert(InventoryDto dto) {
    return new SQL().INSERT_INTO("inventory_tab")
        .VALUES(ALL_COLUMNS,
            "#{dto.productId},#{dto.merchantId},#{dto.stock},#{dto.dateLastAdded},#{dto.lowStockThreshold}")
        .toString();
  }

  public String update(InventoryDto dto) {
    SQL sqlBuilder = new SQL() {{
      UPDATE("inventory_tab");
    }};
    if (dto.getStock() != null) {
      sqlBuilder.SET("stock = #{dto.stock}");
    }
    if (dto.getDateLastAdded() != null) {
      sqlBuilder.SET("date_last_added = #{dto.dateLastAdded}");
    }
    if (dto.getLowStockThreshold() != null) {
      sqlBuilder.SET("low_stock_threshold = #{dto.lowStockThreshold}");
    }
    sqlBuilder.WHERE("id = #{dto.id}");
    return sqlBuilder.toString();
  }

  public String selectByInventoryId(long id) {
    return new SQL() {{
      SELECT("id, ", ALL_COLUMNS);
      FROM("inventory_tab");
      WHERE("id = #{id}");
    }}.toString();
  }
}
