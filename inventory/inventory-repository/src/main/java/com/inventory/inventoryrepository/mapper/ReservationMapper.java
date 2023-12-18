package com.inventory.inventoryrepository.mapper;

import com.inventory.inventoryrepository.dto.ReservationDto;
import com.inventory.inventoryrepository.mapper.impl.InventorySqlUtil;
import com.inventory.inventoryrepository.mapper.impl.ReservationSqlUtil;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

@Mapper
public interface ReservationMapper {

  @InsertProvider(type = InventorySqlUtil.class, method = "insert")
  int insert(ReservationDto dto);

  @UpdateProvider(type = ReservationSqlUtil.class, method = "update")
  int update(ReservationDto dto);

  @SelectProvider(type = ReservationSqlUtil.class, method = "selectUnPaidReservedItemsByUser")
  ReservationDto selectUnPaidReservedItemsByUser(Long productId, Long merchantId, Long userId);
}
