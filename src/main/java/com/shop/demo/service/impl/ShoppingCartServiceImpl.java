package com.shop.demo.service.impl;

import com.shop.demo.dao.PurchaseDao;
import com.shop.demo.dao.ShoppingCartDao;
import com.shop.demo.dto.PurchaseDto;
import com.shop.demo.dto.ShoppingCartDto;
import com.shop.demo.entity.Purchase;
import com.shop.demo.entity.ShoppingCart;
import com.shop.demo.service.OrderService;
import com.shop.demo.service.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final ShoppingCartDao shoppingCartDao;
    private final ConversionService conversion;
    private final PurchaseDao purchaseDao;

    @Override
    public ShoppingCartDto getShoppingCartDtoById(String customerId) {
        ShoppingCart shoppingCart = shoppingCartDao.getShoppingCartById(customerId);
        ShoppingCartDto shoppingCartDto = conversion.convert(shoppingCart, ShoppingCartDto.class);

        List<Purchase> purchases = purchaseDao.getPurchases(customerId);
        List<PurchaseDto> purchaseDtos = new ArrayList<>();

        if (purchases.size() != 0) {
            for (Purchase purchase : purchases) {
                purchaseDtos.add(conversion.convert(purchase, PurchaseDto.class));
            }

            shoppingCartDto.setPurchases(purchaseDtos);
        }

        return shoppingCartDto;
    }

    @Override
    public void createShoppingCart(ShoppingCartDto dto, String customerId) throws SQLException {
        ShoppingCart shoppingCart = conversion.convert(dto, ShoppingCart.class);
        shoppingCart.setId(UUID.randomUUID().toString());

        shoppingCartDao.createShoppingCart(shoppingCart, customerId );

    }
}
