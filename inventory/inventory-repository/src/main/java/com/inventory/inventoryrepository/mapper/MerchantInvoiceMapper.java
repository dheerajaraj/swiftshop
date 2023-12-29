package com.inventory.inventoryrepository.mapper;

import com.inventory.inventoryrepository.dto.MerchantInvoiceDto;
import com.inventory.inventoryrepository.mapper.impl.MerchantInvoiceSqlUtil;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MerchantInvoiceMapper {

  @InsertProvider(type = MerchantInvoiceSqlUtil.class, method = "insert")
  int insert(MerchantInvoiceDto dto);
}
