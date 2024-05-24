package com.inventory.inventorybiz.merchant;

import com.inventory.inventorybiz.domainevents.InventoryUpdatedWithNewProductEvent;
import com.inventory.inventoryrepository.dto.MerchantInvoiceDto;
import com.inventory.inventoryrepository.mapper.MerchantInvoiceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class MerchantDomain {

  private final MerchantInvoiceMapper merchantInvoiceMapper;

  @Transactional(rollbackFor = Exception.class)
  @EventListener(value = InventoryUpdatedWithNewProductEvent.class)
  public void handleOrderCreated(InventoryUpdatedWithNewProductEvent event) {
    this.merchantInvoiceMapper.insert(MerchantInvoiceDto.builder().merchantId(event.getMerchantId())
        .productId(event.getProductId()).invoiceDate(event.getDateLastAdded())
        .orderId(event.getOrderId())
        .paymentStatus(event.getPaymentStatus()).quantity(event.getQuantity()).build());
  }
}
