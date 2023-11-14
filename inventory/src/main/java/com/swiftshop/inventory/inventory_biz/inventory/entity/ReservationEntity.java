package com.swiftshop.inventory.inventory_biz.inventory.entity;

import com.swiftshop.inventory.inventory_biz.common.valueObj.ProductId;
import com.swiftshop.inventory.inventory_biz.common.valueObj.UserId;
import com.swiftshop.inventory.inventory_biz.inventory.valueobj.reservation.ReservationId;
import lombok.Data;

import java.util.Date;

@Data
public class ReservationEntity {
    private ReservationId id;
    private ProductId productId;
    private int quantity;
    private UserId userId;
    private ReservationActionEnum action;
    private boolean expired;
    private Date dateAdded;

    public enum ReservationActionEnum {
        CART,
        ORDER_PLACED
    }
}

