package com.shop.demo.dao;

import com.shop.demo.entity.Purchase;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PurchaseDao {

    void createPurchase(Purchase purchase);
    Purchase getPurchaseById(String cus_id, String pur_id);
    List<Purchase> getPurchases(String id);
    List<Purchase> getPurchaseHistory(String cus_id);

}
