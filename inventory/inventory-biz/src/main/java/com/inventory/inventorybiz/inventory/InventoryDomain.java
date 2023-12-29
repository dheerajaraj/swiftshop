package com.inventory.inventorybiz.inventory;

import com.inventory.inventorybiz.events.InventoryUpdatedWithNewProductEvent;
import com.inventory.inventorybiz.events.InventoryUpdatedWithNewMetricsEvent;
import com.inventory.inventorybiz.inventory.model.entity.ReservationEntity.ReservationActionEnum;
import com.inventory.inventorybiz.inventory.model.entity.WarehouseInventoryEntity;
import com.inventory.inventorybiz.merchant.entity.MerchantInvoiceEntity.PaymentStatusEnum;
import com.inventory.inventorybiz.valueObj.Rating;
import com.inventory.inventorybiz.valueObj.UserId;
import com.inventory.inventoryrepository.dto.InventoryDto;
import com.inventory.inventoryrepository.dto.ReservationDto;
import com.inventory.inventoryrepository.mapper.InventoryMapper;
import com.inventory.inventoryrepository.mapper.ReservationMapper;
import java.io.InvalidObjectException;
import java.sql.Date;
import java.time.Instant;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Data
@Builder
@Component
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class InventoryDomain {

  private final InventoryMapper inventoryMapper;
  private final ReservationMapper reservationMapper;
  private final ApplicationEventPublisher applicationEventPublisher;
  private final ApplicationEventMulticaster applicationEventMulticaster;

  /**
   * 1. Update inventory tab with the correct quantity 2. Update reservation tab if necessary by
   * marking it as delete.
   */
  @Transactional(rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
  public void handleOrderCreated(WarehouseInventoryEntity entity, UserId userId, Rating rating)
      throws InvalidObjectException {
    InventoryDto dto = this.inventoryMapper.selectByInventoryId(
        entity.getWarehouseInventoryId());
    if (dto == null) {
      throw new InvalidObjectException(
          String.format("Inventory item does not exist for the merchantId: %d and productId: %d",
              entity.getMerchantId(), entity.getProductId()));
    }
    WarehouseInventoryEntity retrievedInventory = WarehouseInventoryEntity.dto2Domain(dto);
    retrievedInventory.getStock().validateAndUpdateStocksToRemove(entity.getStock());
    this.inventoryMapper.update(WarehouseInventoryEntity.domain2Dto(retrievedInventory));

    ReservationDto reservationDto = reservationMapper.selectUnPaidReservedItemsByUser(
        entity.getProductId(), entity.getMerchantId(), userId.getId());
    if (reservationDto != null) {
      this.reservationMapper.update(
          ReservationDto.builder().id(reservationDto.getId())
              .action(ReservationActionEnum.PAID.name()).expired(Boolean.TRUE).build());
    }

    this.applicationEventPublisher.publishEvent(
        InventoryUpdatedWithNewProductEvent.builder().productId(entity.getProductId())
            .merchantId(entity.getMerchantId())
            .invoiceDate(new Date(Instant.now().getEpochSecond()))
            .paymentStatus(PaymentStatusEnum.PAID.name()).quantity(entity.getStock().getQuantity())
            .build());
    InventoryUpdatedWithNewMetricsEvent metricsEvent = new InventoryUpdatedWithNewMetricsEvent(
        this, entity.getMerchantId(), entity.getProductId(), rating.getRating());
    this.applicationEventMulticaster.multicastEvent(metricsEvent);
  }

  public void addNewProduct(WarehouseInventoryEntity entity) {
    this.inventoryMapper.insert(WarehouseInventoryEntity.domain2Dto(entity));
  }
}
