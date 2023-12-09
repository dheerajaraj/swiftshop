package com.inventory.inventorybiz.inventory.model.entity;

import com.inventory.inventorybiz.inventory.model.entity.valueobj.reservation.ReservationId;
import com.inventory.inventorybiz.valueObj.ProductId;
import com.inventory.inventorybiz.valueObj.UserId;
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

