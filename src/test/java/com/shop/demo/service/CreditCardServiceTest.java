package com.shop.demo.service;


import com.shop.demo.dto.CreditCardDto;
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
public class CreditCardServiceTest {

    @Autowired
    private CreditCardService creditCardService;

    @Autowired
    private Util util;

    @Before
    public void setUp() {
        util.setUp();
    }

    @Test
    public void ShouldGetExpectedCreditCartIfItWasCreated() {
        CreditCardDto expectedCreditCard = Util.createTestCreditCard("3", "1");
        creditCardService.createCreditCard(expectedCreditCard);
        CreditCardDto actualCreditCard = creditCardService.getCreditCard(expectedCreditCard.getId());

        assertThat(actualCreditCard).isEqualTo(expectedCreditCard);
    }
}