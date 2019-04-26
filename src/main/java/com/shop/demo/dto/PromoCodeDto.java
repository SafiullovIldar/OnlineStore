package com.shop.demo.dto;

import com.shop.demo.entity.Item;
import lombok.Data;

import java.util.Date;

@Data
public class PromoCodeDto {

    private String id;
    private Date expirationDate;
    private Float discount;
    private String itemId;
}
