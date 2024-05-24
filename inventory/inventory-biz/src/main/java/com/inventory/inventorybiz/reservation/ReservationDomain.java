package com.inventory.inventorybiz.reservation;

import com.inventory.inventorybiz.domainevents.InventoryUpdatedWithNewProductEvent;
import com.inventory.inventorybiz.reservation.entity.ReservationEntity;
import com.inventory.inventoryrepository.dto.ReservationDto;
import com.inventory.inventoryrepository.mapper.ReservationMapper;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Data
@Builder
@Component
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ReservationDomain {
    private final ReservationMapper reservationMapper;

    @Transactional(rollbackFor = Exception.class)
    @EventListener(value = InventoryUpdatedWithNewProductEvent.class)
    public void handleReservationForOrderCreation(InventoryUpdatedWithNewProductEvent entity){
        ReservationDto reservationDto = reservationMapper.selectUnPaidReservedItemsByUser(
                entity.getProductId(), entity.getMerchantId(), entity.getShopperId());
        if (reservationDto != null) {
            this.reservationMapper.update(
                    ReservationDto.builder().id(reservationDto.getId())
                            .action(ReservationEntity.ReservationActionEnum.PAID.name()).expired(Boolean.TRUE).build());
        }
    }
}
