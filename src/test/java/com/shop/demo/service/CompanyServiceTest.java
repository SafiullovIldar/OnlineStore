package com.shop.demo.service;

import com.shop.demo.dto.CompanyDto;
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
public class CompanyServiceTest {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private Util util;

    @Before
    public void setUp() {
        util.setUp();
    }

    @Test
    public void ShouldGetExpectedCompanyIfItWasCreated() {
        CompanyDto expectedCompany = Util.createTestCompany("2");
        companyService.createCompany(expectedCompany);
        CompanyDto actualCompany = companyService.getCompanyById(expectedCompany.getId());

        assertThat(actualCompany).isEqualTo(expectedCompany);
    }
}