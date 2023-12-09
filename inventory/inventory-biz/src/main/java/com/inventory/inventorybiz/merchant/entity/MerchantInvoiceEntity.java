package com.inventory.inventorybiz.merchant.entity;

import com.inventory.inventorybiz.merchant.entity.valueObj.MerchantInvoiceId;
import com.inventory.inventorybiz.valueObj.MerchantId;
import com.inventory.inventorybiz.valueObj.ProductId;
import lombok.Data;

import java.util.Date;

@Data
public class MerchantInvoiceEntity {
    private MerchantInvoiceId merchantInvoiceId;
    private ProductId productId;
    private MerchantId merchantId;
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
