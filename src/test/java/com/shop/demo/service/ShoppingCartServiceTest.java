package com.shop.demo.service;


import com.shop.demo.util.Util;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

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

    @Test
    public void ShouldPrintCorrectMessageDependingOnPaymentMethod() throws Exception {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        shoppingCartService.pay("1", "1");
        assertThat(outContent.toString()).contains("paid using credit card");

        shoppingCartService.pay("1", "2");
        assertThat(outContent.toString()).contains("paid using PayPal");

        shoppingCartService.pay("1", "3");
        assertThat(outContent.toString()).contains("There is no payment information");
    }
}