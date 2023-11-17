package com.swiftshop.inventory.inventory_biz.merchant;

import org.springframework.transaction.annotation.Transactional;

public class MerchantDomain {

    /**
     * update merchant_invoice_tab
     */
    @Transactional(rollbackFor = Exception.class)
    public void handleOrderCreated(){

    }
}
