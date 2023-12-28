package com.inventory.inventoryrepository.mapper.impl;

import com.inventory.inventoryrepository.dto.InventoryDto;
import org.apache.ibatis.jdbc.SQL;

public class MerchantInvoiceSqlUtil {

  private static final String ALL_COLUMNS = "product_id, merchant_id, invoice_date, payment_status, quantity";

  public String insert(InventoryDto dto) {
    return new SQL().INSERT_INTO("merchant_invoice_tab")
        .VALUES(ALL_COLUMNS,
            "#{dto.productId},#{dto.merchantId},#{dto.invoiceDate},#{dto.paymentStatus},#{dto.quantity}")
        .toString();
  }

}
