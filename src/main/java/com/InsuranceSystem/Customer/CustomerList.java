package com.InsuranceSystem.Customer;


/**
 * @author JeongChanho
 * @version 1.0
 * @created 24-4-2020 ���� 12:27:09
 */
public interface CustomerList {

	public Customer show();
	public boolean delete(int customerID);
	public boolean maturityContractsManaging(int customerID);
	public Customer search(int customerID);
	public boolean add(Customer customer);
	public void changeCustomer(int customerID);
}