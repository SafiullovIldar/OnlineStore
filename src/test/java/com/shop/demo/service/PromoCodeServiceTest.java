package com.shop.demo.service;

import com.shop.demo.dto.PromoCodeDto;
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
public class PromoCodeServiceTest {

    @Autowired
    private PromoCodeService promoCodeService;

    @Autowired
    private Util util;

    @Before
    public void setUp() {
        util.setUp();
    }

    @Test
    public void ShouldReturnExpectedPromoCodeIfItWasCreated() {
        PromoCodeDto expectedPromoCode = Util.createTestPromoCode("3");
        promoCodeService.createPromoCode(expectedPromoCode);
        PromoCodeDto actualPromoCode = promoCodeService.getPromoCode("3");

        assertThat(actualPromoCode).isEqualTo(expectedPromoCode);
    }

    @Test
    public void ShouldReturnPromoCodeWithExpectedPromoCodeId() {
        String expectedPromoCodeId = "1";
        String actualPromoCodeId = promoCodeService.getPromoCode("1").getId();

        assertThat(actualPromoCodeId).isEqualTo(expectedPromoCodeId);
    }

}