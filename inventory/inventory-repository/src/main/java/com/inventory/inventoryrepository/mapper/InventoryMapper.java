package com.inventory.inventoryrepository.mapper;

import com.inventory.inventoryrepository.dto.InventoryDto;
import com.inventory.inventoryrepository.mapper.impl.InventorySqlUtil;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

@Mapper
public interface InventoryMapper {

    @InsertProvider(type= InventorySqlUtil.class, method="insert")
    int insert(InventoryDto dto);

    @UpdateProvider(type= InventorySqlUtil.class, method="update")
    int update(InventoryDto dto);

    @SelectProvider(type= InventorySqlUtil.class, method="selectByInventoryId")
    @Results(id = "inventoryResult", value = {
        @Result(column = "id", property = "id", id = true),
        @Result(column = "product_id", property = "productId"),
        @Result(column = "merchant_id", property = "merchantId"),
        @Result(column = "stock", property = "stock"),
        @Result(column = "date_last_added", property = "dateLastAdded"),
        @Result(column = "low_stock_threshold", property = "lowStockThreshold")
    })
    InventoryDto selectByInventoryId(long id);
}
