package com.InsuranceSystem.Service;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.InsuranceSystem.Customer.Customer;
import com.InsuranceSystem.Development.Insurance;
import com.InsuranceSystem.Development.Life;
import com.InsuranceSystem.Mapper.CustomerMapper;
import com.InsuranceSystem.Mapper.DevelopmentMapper;
import com.InsuranceSystem.Mapper.SubInfoMapper;

@Service
public class CustomerServiceImpl implements CustomerService {

	public final CustomerMapper customerMapper;
	public final SubInfoMapper subInfoMapper;
	public final DevelopmentMapper developmentMapper;

	public CustomerServiceImpl(CustomerMapper customerMapper, SubInfoMapper subInfoMapper, DevelopmentMapper developmentMapper) {
		this.customerMapper = customerMapper;
		this.subInfoMapper = subInfoMapper;
		this.developmentMapper = developmentMapper;
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

	@Override
	public boolean deleteCustomer(int customerID) {
		int queryResult = 0;
		queryResult = customerMapper.delete_Customer(customerID);
		return (queryResult == 1) ? true : false;
	}

	@Override
	public int select_CustomerIDtoInsuranceID(int customerID) {
		return subInfoMapper.select_subInfo_customerIDtoInsuranceID(customerID);
	}

	@Override
	public Life select_Insurance_insuranceID(int insuranceID) {
		return developmentMapper.select_Insurance_insuranceID(insuranceID);
	}

	@Override
	public boolean updateCustomer(Customer customer) {
		int queryResult = 0;
        queryResult = customerMapper.update_Customer(customer);
        return (queryResult == 1) ? true : false;
	}

}
