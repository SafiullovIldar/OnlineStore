package com.shop.demo.dao.impl;

import com.shop.demo.dao.CustomerDao;
import com.shop.demo.entity.Customer;
import com.shop.demo.transaction.TransactionalManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CustomerDaoImpl implements CustomerDao {

    private final DataSource dataSource;
    private final TransactionalManager transactionalManager;

    @Override
    public void createCustomer(Customer customer) throws SQLException {
        Connection connection = transactionalManager.getConnection();

        PreparedStatement preparedStatement = connection
                .prepareStatement("INSERT INTO customer VALUES (?,?,?,?,?)");

        preparedStatement.setString(1, customer.getId());
        preparedStatement.setString(2, customer.getName());
        preparedStatement.setString(3, customer.getAddress());
        preparedStatement.setString(4, customer.getEmail());
        preparedStatement.setString(5, customer.getPhoneNumber());

        preparedStatement.executeUpdate();

    }

    @Override
    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();

        try (Connection connection = dataSource.getConnection()) {

            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM customer");

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Customer customer = new Customer();
                customer.setId(resultSet.getString("id"));
                customer.setName(resultSet.getString("name"));
                customer.setEmail(resultSet.getString("email"));
                customer.setAddress(resultSet.getString("address"));
                customer.setPhoneNumber(resultSet.getString("phone"));
                customers.add(customer);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customers;
    }

    @Override
    public Customer getCustomerById(String customer_id) {
        Customer customer = null;

        try (Connection connection = dataSource.getConnection()) {

            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM customer WHERE id = ?");
            preparedStatement.setString(1, customer_id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                customer = new Customer();
                customer.setId(resultSet.getString("id"));
                customer.setName(resultSet.getString("name"));
                customer.setEmail(resultSet.getString("email"));
                customer.setAddress(resultSet.getString("address"));
                customer.setPhoneNumber(resultSet.getString("phone"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customer;
    }


}
