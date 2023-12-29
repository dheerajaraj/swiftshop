package com.inventory.inventorybiz.merchant.entity;

import com.inventory.inventorybiz.valueObj.Quantity;
import com.inventory.inventorybiz.valueObj.Rating;
import com.inventory.inventoryrepository.dto.MerchantInvoiceDto;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class MerchantInvoiceEntity {

  private Long merchantInvoiceId;
  private Long productId;
  private Long merchantId;
  private Date invoiceDate;
  private PaymentStatusEnum paymentStatus;
  private String productCategory;
  private Rating rating;
  private Quantity quantity;

  public enum PaymentStatusEnum {
    PAID,
    ORDER_PLACED
  }

  public static MerchantInvoiceDto domain2Dto(MerchantInvoiceEntity entity) {
    return MerchantInvoiceDto.builder().productId(entity.getProductId())
        .merchantId(entity.getMerchantId()).invoiceDate(entity.getInvoiceDate())
        .paymentStatus(entity.getPaymentStatus().name())
        .quantity(entity.getQuantity().getQuantity())
        .build();
  }

}
