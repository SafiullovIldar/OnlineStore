package com.shop.demo.service;


import com.shop.demo.dto.ShoppingCartDto;
import com.shop.demo.util.Util;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShoppingCartServiceTest {

    @Autowired
    private Util util;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Before
    public void setUp() {
        util.setUp();
    }

    @After
    public void tearDown() {
        util.tearDown();
    }

    @Test
    public void ShouldReturnShoppingCartWithExpectedShoppingCartId() {
        String expectedShoppingCartId = "1";
        String actualId = shoppingCartService.getShoppingCartDtoById("1").getId();

        assertThat(actualId).isEqualTo(expectedShoppingCartId);
    }

}