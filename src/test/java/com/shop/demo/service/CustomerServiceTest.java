package com.shop.demo.service;

import com.shop.demo.dto.CustomerDto;
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
public class CustomerServiceTest {

    @Autowired
    private CustomerService customerService;

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
    public void ShouldReturnExpectedCustomerIfItWasCreated() {
        CustomerDto expectedCustomer = Util.createTestCustomer("3");
        customerService.createCustomer(expectedCustomer);

        CustomerDto actualCustomer = customerService.getCustomerById(expectedCustomer.getId());

        assertThat(actualCustomer).isEqualTo(expectedCustomer);
    }

    @Test
    public void ShouldReturnExpectedCustomerCount() {
        int expectedCustomerCount = 2;
        int actualCustomerCount = customerService.getAllCustomers().size();

        assertThat(actualCustomerCount).isEqualTo(expectedCustomerCount);
    }

    @Test
    public void ShouldGetCustomerWithExpectedFields() {
        String expectedCustomerId = "1";
        String expectedCustomerName = "Pasha";

        CustomerDto actualCustomer = customerService.getCustomerById(expectedCustomerId);

        assertThat(actualCustomer.getId()).isEqualTo(expectedCustomerId);
        assertThat(actualCustomer.getName()).isEqualTo(expectedCustomerName);
    }
}