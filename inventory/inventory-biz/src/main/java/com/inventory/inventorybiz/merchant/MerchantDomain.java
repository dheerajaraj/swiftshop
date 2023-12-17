package com.inventory.inventorybiz.merchant;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class MerchantDomain {

    /**
     * update merchant_invoice_tab
     */
    @Transactional(rollbackFor = Exception.class)
    public void handleOrderCreated(){

    }
}
