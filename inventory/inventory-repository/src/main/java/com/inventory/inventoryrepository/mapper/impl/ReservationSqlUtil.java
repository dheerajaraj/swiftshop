package com.inventory.inventoryrepository.mapper.impl;

import com.inventory.inventoryrepository.dto.ReservationDto;
import org.apache.ibatis.jdbc.SQL;

public class ReservationSqlUtil {

  private static final String ALL_COLUMNS = "product_id, merchant_id, user_id, quantity, action, date_added, expired";

  public String insert(ReservationDto dto) {
    return new SQL() {{
      INSERT_INTO("reservation_tab");
      VALUES(ALL_COLUMNS,
          "#{dto.productId},#{dto.merchantId},#{dto.userId},#{dto.quantity},#{dto.action},#{dto.dateAdded},#{dto.expired}");
    }}.toString();
  }

  public String update(ReservationDto dto) {
    return new SQL() {{
      UPDATE("reservation_tab");
      if (dto.getQuantity() != null) {
        SET("quantity = #{dto.quantity}");
      }
      if (dto.getAction() != null) {
        SET("action = #{dto.action}");
      }
      if (dto.getExpired() != null) {
        SET("expired = #{dto.expired}");
      }
      if (dto.getDateAdded() != null) {
        SET("date_added = #{dto.dateAdded}");
      }
      WHERE("id = #{dto.id}");
    }}.toString();
  }

  public String selectUnPaidReservedItemsByUser(Long productId, Long merchantId, Long userId) {
    return new SQL() {{
      SELECT("id, ", ALL_COLUMNS);
      FROM("reservation_tab");
      WHERE("product_id = #{productId}");
      AND();
      WHERE("merchant_id = #{merchantId}");
      AND();
      WHERE("user_id = #{userId}");
      AND();
      WHERE("NOT action = 'PAID'");
      AND();
      WHERE("expired = 0");
    }}.toString();
  }

}
