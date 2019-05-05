package com.shop.demo.service;

import com.shop.demo.util.Util;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private Util util;

    @Before
    public void setUp() {
        util.setUp();
    }

    @After
    public void tearDown() {
        util.tearDown();
    }

    @Test
    public void ShouldReturnPurchaseWithExpectedPurchaseId() {
        String expectedPurchaseId = "1";
        String actualPurchaseId = orderService.getPurchaseById("1", "1").getId();

        assertThat(actualPurchaseId).isEqualTo(expectedPurchaseId);
    }

    @Test
    public void ShouldGetListOfPurchasesWithExpectedPurchasesCount() {
        int expectedPurchasesCount = 1;
        int actualPurchasesCount = orderService.getPurchases("1").size();

        assertThat(actualPurchasesCount).isEqualTo(expectedPurchasesCount);
    }

    @Test
    public void ShouldGetPurchaseIfItWasCreated() {
        int expectedPurchasesCountBeforeCreatingPurchase = orderService.getPurchases("1").size();
        orderService.createPurchase("1", "2", 1, "1", "1");
        int actualPurchasesCountAfterCreatingPurchase = orderService.getPurchases("1").size();

        assertThat(expectedPurchasesCountBeforeCreatingPurchase).isEqualTo(1);
        assertThat(actualPurchasesCountAfterCreatingPurchase).isEqualTo(2);
    }

    @Test
    public void getPurchaseHistory() {

    }
}