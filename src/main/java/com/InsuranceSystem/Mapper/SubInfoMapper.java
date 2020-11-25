package com.InsuranceSystem.Mapper;

import java.util.List;

import com.InsuranceSystem.Customer.Customer;
import com.InsuranceSystem.Development.Insurance;
import com.InsuranceSystem.Sale.AssociationCus;

public interface SubInfoMapper {
	
	public List<AssociationCus> select_subInfo();
	
	public void insert_subInfo(AssociationCus associationCus);
}
