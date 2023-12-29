package com.inventory.inventoryrepository.dto;

import java.util.Date;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MerchantInvoiceDto {

  private Long merchantInvoiceId;
  private Long productId;
  private Long merchantId;
  private Date invoiceDate;
  private String paymentStatus;
  private Integer quantity;
}
