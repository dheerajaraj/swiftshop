package com.swiftshop.inventory.inventory_biz.merchant.entity;

import com.swiftshop.inventory.inventory_biz.common.valueObj.MerchantId;
import com.swiftshop.inventory.inventory_biz.common.valueObj.ProductId;
import com.swiftshop.inventory.inventory_biz.merchant.entity.valueObj.MerchantInvoiceId;
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
