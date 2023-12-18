package com.inventory.inventorybiz.merchant.entity;

import lombok.Data;

import java.util.Date;

@Data
public class MerchantInvoiceEntity {
    private Long merchantInvoiceId;
    private Long productId;
    private Long merchantId;
    private Date invoiceDate;
    private PaymentStatusEnum paymentStatus;
    private String productCategory;
    private int rating;
    private int quantity;

    public enum PaymentStatusEnum {
        PAID,
        ORDER_PLACED
    }
}
