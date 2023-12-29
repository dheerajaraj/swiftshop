package com.inventory.inventoryrepository.mapper;

import com.inventory.inventoryrepository.dto.ReservationDto;
import com.inventory.inventoryrepository.mapper.impl.InventorySqlUtil;
import com.inventory.inventoryrepository.mapper.impl.ReservationSqlUtil;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

@Mapper
public interface ReservationMapper {

  @InsertProvider(type = InventorySqlUtil.class, method = "insert")
  int insert(ReservationDto dto);

  @UpdateProvider(type = ReservationSqlUtil.class, method = "update")
  int update(ReservationDto dto);

  @SelectProvider(type = ReservationSqlUtil.class, method = "selectUnPaidReservedItemsByUser")
  @Results(id = "reservationData", value = {
      @Result(column = "id", property = "id", id=true),
      @Result(column = "product_id", property = "productId"),
      @Result(column = "user_id", property = "userId"),
      @Result(column = "merchant_id", property = "merchantId"),
      @Result(column = "quantity", property = "quantity"),
      @Result(column = "action", property = "action"),
      @Result(column = "expired", property = "expired"),
      @Result(column = "date_added", property = "dateAdded")
  })
  ReservationDto selectUnPaidReservedItemsByUser(Long productId, Long merchantId, Long userId);
}
