package com.shop.demo.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    private String id;
    private String name;
    private Integer price;
    private Integer availability;

}
