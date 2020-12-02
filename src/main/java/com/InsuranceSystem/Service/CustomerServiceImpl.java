package com.InsuranceSystem.Service;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.InsuranceSystem.Customer.Customer;
import com.InsuranceSystem.Mapper.CustomerMapper;

@Service
public class CustomerServiceImpl implements CustomerService {

	public final CustomerMapper customerMapper;

	public CustomerServiceImpl(CustomerMapper customerMapper) {
		this.customerMapper = customerMapper;
	}

	@Override
	public List<Customer> selectCustomerList() {
		List<Customer> customerList = Collections.emptyList();
		
		customerList = customerMapper.select_ApprovedCustomer();
		if(!customerList.isEmpty()) {
			return customerList;
		}
		return null;
	}

	@Override
	public Customer selectCustomer(int customerID) {
		Customer customer = new Customer();
		customer = customerMapper.select_ApprovedCustomer_insuranceID(customerID);
		return customer;
	}

}
