package com.shop.demo.util;

import com.shop.demo.dto.*;
import com.shop.demo.enums.PurchaseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class Util {

    @Autowired
    private DataSource dataSource;


    public void setUp() {
        try (Connection connection = dataSource.getConnection();) {
            Statement statement = connection.createStatement();
            statement.execute("INSERT INTO company VALUES ('1', 'Ebay');");
            statement.execute("INSERT INTO catalog VALUES" +
                    "('1', 'Electronics', '1')," +
                    "('2', 'Clothes', '1');");
            statement.execute("INSERT INTO customer VALUES" +
                    "('1', 'Pasha', 'Pasha@gmail.com', 'Tverskya 5-55', 806995948123)," +
                    "('2', 'Sasha', 'Sasha@gmail.com', 'Polakova 14-45', 89078776543);");
            statement.execute("INSERT INTO credit_card VALUES" +
                    "('1', 45688483828123450943, '2021-12-09', 094, '1')," +
                    "('2', 45688483828123450943, '2022-11-16', 194, '2');");
            statement.execute("INSERT INTO item VALUES" +
                    "('1', 'Shoes', 4500, 19, '2')," +
                    "('2', 'Computer', 24500, 9, '1')," +
                    "('3', 'Jacket', 3400, 3, '2')," +
                    "('4', 'Smartphone', 44500, 76, '1');");
            statement.execute("INSERT INTO promo_code VALUES" +
                    "('1', '2019-09-09', 15, '3')," +
                    "('2', '2019-06-19', 5, '4');");
            statement.execute("INSERT INTO shopping_cart VALUES" +
                    "('1', '1')," +
                    "('2', '2');");
            statement.execute("INSERT INTO purchase VALUES" +
                    "('1', '2019-11-09', 'init', 2, '2', '1', '1')," +
                    "('2', '2019-05-19', 'completed', 1, '1', null, '1');");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void tearDown() {

        try (Connection connection = dataSource.getConnection()){
            Statement statement = connection.createStatement();
            statement.execute("DELETE FROM company");
            statement.execute("DELETE FROM catalog");
            statement.execute("DELETE FROM customer");
            statement.execute("DELETE FROM credit_card");
            statement.execute("DELETE FROM item");
            statement.execute("DELETE FROM promo_code");
            statement.execute("DELETE FROM shopping_cart");
            statement.execute("DELETE FROM purchase");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static CreditCardDto createTestCreditCard(String creditCardId, String customerId) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse ("2019-12-31");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        CreditCardDto creditCard = new CreditCardDto();
        creditCard.setId(creditCardId);
        creditCard.setCardNumber(12345L);
        creditCard.setCvsNumber(555L);
        creditCard.setExpirationDate(date);
        creditCard.setCustomerId(customerId);
        return creditCard;
    }

    public static CompanyDto createTestCompany(String companyId) {
        CompanyDto dto = new CompanyDto();
        dto.setId(companyId);
        dto.setName("TestCompany");
        return dto;
    }

    public static List<PurchaseDto> createTestPurchases() {
        List<PurchaseDto> purchases = new ArrayList<>();

        PurchaseDto purchase1 = createTestPurchase("1");
        PurchaseDto purchase2 = createTestPurchase("2");

        purchases.add(purchase1);
        purchases.add(purchase2);

        return purchases;
    }

    public static PurchaseDto createTestPurchase(String purchaseId) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse ("2019-12-31");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        PurchaseDto dto = new PurchaseDto();
        dto.setId(purchaseId);
        dto.setShoppingCartId("1");
        dto.setPromoCodeId("1");
        dto.setItemId("1");
        dto.setStatus(PurchaseStatus.INIT);
        dto.setAmount(50);
        dto.setCreateDate(date);
        return dto;
    }

    public static ShoppingCartDto createTestShoppingCart(String shoppingCartId) {
        ShoppingCartDto dto = new ShoppingCartDto();
        dto.setId(shoppingCartId);
        dto.setPurchases(createTestPurchases());
        return dto;
    }

    public static PromoCodeDto createTestPromoCode(String promoCodeId) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
           date = format.parse ("2019-12-31");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        PromoCodeDto dto = new PromoCodeDto();
        dto.setId(promoCodeId);
        dto.setItemId("1");
        dto.setExpirationDate(date);
        dto.setDiscount(10f);
        return dto;
    }

    public static CustomerDto createTestCustomer(String customerId) {
        CustomerDto dto = new CustomerDto();
        dto.setId(customerId);
        dto.setName("TestCustomer");
        dto.setPhoneNumber("TestNumber");
        dto.setEmail("TestEmail");
        dto.setAddress("TestAddress");
        return dto;
    }

    public static CatalogDto createTestCatalog(String catalogId, String firstItemId, String secondItemId) {
        CatalogDto dto = new CatalogDto();
        dto.setId(catalogId);
        dto.setName("Test");
        dto.setItems(createTestItems(firstItemId, secondItemId));

        return dto;
    }

    public static ItemDto createTestItem(String itemId) {
        ItemDto item = new ItemDto();
        item.setId(itemId);
        item.setPrice(150);
        item.setAvailability(50);
        item.setName("Test1");
        return item;
    }

    public static List<ItemDto> createTestItems(String firstItemId, String secondItemId) {
        List<ItemDto> items = new ArrayList<>();

        ItemDto item1 = createTestItem(firstItemId);
        ItemDto item2 = createTestItem(secondItemId);

        items.add(item1);
        items.add(item2);

        return items;
    }
}
