package com.inventory.inventoryrepository.mapper;

import com.inventory.inventoryrepository.dto.InventoryDto;
import com.inventory.inventoryrepository.mapper.impl.InventorySqlUtil;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

@Mapper
public interface InventoryMapper {

    @InsertProvider(type= InventorySqlUtil.class, method="insert")
    int insert(InventoryDto dto);

    @UpdateProvider(type= InventorySqlUtil.class, method="update")
    int update(InventoryDto dto);

    @SelectProvider(type= InventorySqlUtil.class, method="selectByInventoryId")
    InventoryDto selectByInventoryId(long id);
}
