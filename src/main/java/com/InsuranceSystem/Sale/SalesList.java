package com.InsuranceSystem.Sale;

import com.InsuranceSystem.Customer.Customer;
import com.InsuranceSystem.Development.Insurance;

/**
 * @author JeongChanho
 * @version 1.0
 * @created 24-4-2020 ���� 12:27:10
 */
public interface SalesList {

	public Subscribers addSubscriberList(Insurance insurance, Subscribers subscribers);
	public Insurance insuranceshow();
	public Insurance getInsurance(int insuranceID);
	public Subscribers getSubscriber(int subscriberID);
	public Customer getCusomter(int customerID);
	public void addCustomerList(int subscriberID);
	public Insurance returnInsurance(int customerID);
	public AssociationCus associationIC(int insuranceID, int customerID);
	public double calculateGuarantee(int customerID);
	public void changeCustomer(int insuranceID, Subscribers subscribers);
}