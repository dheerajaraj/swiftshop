package com.inventory.inventoryrepository.mapper.impl;

import com.inventory.inventoryrepository.dto.InventoryDto;
import org.apache.ibatis.jdbc.SQL;

public class InventorySqlUtil {

  public String insert(InventoryDto dto) {
    return new SQL().INSERT_INTO("inventory_tab")
        .VALUES("product_id, merchant_id, stock, date_last_added, low_stock_threshold ",
            "#{dto.productId},#{dto.merchantId},#{dto.stock},#{dto.dateLastAdded},#{dto.lowStockThreshold}")
        .toString();
  }

  public String update(InventoryDto dto) {
    return new SQL() {{
      UPDATE("inventory_tab");
      if(dto.getStock() != null){
        SET("stock = #{dto.stock}");
      }
      if(dto.getDateLastAdded() != null){
        SET("date_last_added = #{dto.dateLastAdded}");
      }
      if(dto.getLowStockThreshold()!= null){
        SET("low_stock_threshold = #{dto.lowStockThreshold}");
      }
    }}.toString();
  }
}
