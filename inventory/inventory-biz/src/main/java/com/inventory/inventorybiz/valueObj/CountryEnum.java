package com.inventory.inventorybiz.valueObj;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
public enum CountryEnum {
    SG("Singapore", 65),
    USA("United States of America", 1),
    IND("India", 91),
    CHIN("China", 86),
    MY("Malaysia", 60),
    TH("Thailand", 66),
    VIET("Vietnam", 84),
    INDO("Indonesia", 62),
    UK("United Kingdom", 44),
    AUS("Australia", 61),
    ;

    private final String fullName;
    private final int countryCode;
}
