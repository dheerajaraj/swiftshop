package com.inventory.inventorybiz.merchant.entity;

import com.inventory.inventorybiz.valueObj.*;
import lombok.Data;

import java.util.Date;

@Data
public class MerchantProfileEntity {
    private Long id;
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
