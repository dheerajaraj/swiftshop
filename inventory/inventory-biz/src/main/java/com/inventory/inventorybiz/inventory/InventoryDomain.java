package com.inventory.inventorybiz.inventory;

import com.inventory.inventorybiz.inventory.model.entity.ReservationEntity.ReservationActionEnum;
import com.inventory.inventorybiz.inventory.model.entity.WarehouseInventoryEntity;
import com.inventory.inventorybiz.valueObj.UserId;
import com.inventory.inventoryrepository.dto.InventoryDto;
import com.inventory.inventoryrepository.dto.ReservationDto;
import com.inventory.inventoryrepository.mapper.InventoryMapper;
import com.inventory.inventoryrepository.mapper.ReservationMapper;
import java.io.InvalidObjectException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@AllArgsConstructor
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class InventoryDomain {

  private final InventoryMapper inventoryMapper;
  private final ReservationMapper reservationMapper;

  /**
   * 1. Update inventory tab with the correct quantity 2. Update reservation tab if necessary by
   * marking it as delete.
   */
  @Transactional(rollbackFor = Exception.class)
  public void handleOrderCreated(WarehouseInventoryEntity entity, UserId userId)
      throws InvalidObjectException {
    InventoryDto dto = this.inventoryMapper.selectByInventoryId(
        entity.getWarehouseInventoryId().getId());
    ReservationDto reservationDto = reservationMapper.selectUnPaidReservedItemsByUser(
        entity.getProductId().getId(), entity.getMerchantId().getId(), userId.getId());
    if (dto == null) {
      throw new InvalidObjectException(
          String.format("Inventory item does not exist for the merchantId: %d and productId: %d",
              entity.getMerchantId().getId(), entity.getProductId().getId()));
    }
    this.inventoryMapper.update(WarehouseInventoryEntity.domain2Dto(entity));
    if (reservationDto != null) {
      this.reservationMapper.update(
          ReservationDto.builder().id(reservationDto.getId())
              .action(ReservationActionEnum.PAID.name()).expired(Boolean.TRUE).build());
    }
  }

  public void addNewProduct(WarehouseInventoryEntity entity) {
    this.inventoryMapper.insert(WarehouseInventoryEntity.domain2Dto(entity));
  }
}
