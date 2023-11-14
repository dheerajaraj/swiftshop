package com.swiftshop.inventory.inventory_biz.merchant.entity;

import com.swiftshop.inventory.inventory_biz.common.valueObj.*;
import lombok.Data;

import java.util.Date;

@Data
public class MerchantProfileEntity {
    private MerchantId id;
    private String companyName;
    private Date joinedDate;
    private EmailAddress emailAddress;
    private String pointOfContact;
    private CountryEnum country;
    private PhoneNumber phoneNumber;
    private Rating rating;
    private UrlLink link;
    private String description;
}
