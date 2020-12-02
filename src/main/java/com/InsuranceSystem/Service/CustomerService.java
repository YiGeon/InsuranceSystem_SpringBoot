package com.InsuranceSystem.Service;

import java.util.List;

import com.InsuranceSystem.Customer.Customer;

public interface CustomerService {
	public List<Customer> selectCustomerList();
	
	public Customer selectCustomer(int customerID);
}
