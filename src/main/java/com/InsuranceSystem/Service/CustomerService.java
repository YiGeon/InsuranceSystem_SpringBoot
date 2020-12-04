package com.InsuranceSystem.Service;

import java.util.List;

import com.InsuranceSystem.Customer.Customer;
import com.InsuranceSystem.Development.Life;

public interface CustomerService {
	public List<Customer> selectCustomerList();
	
	public Customer selectCustomer(int customerID);
	
	public boolean deleteCustomer(int customerID);
	
	public boolean updateCustomer(Customer customer);
	
	public int select_CustomerIDtoInsuranceID(int customerID);

	public Life select_Insurance_insuranceID(int insuranceID);
}
