package com.InsuranceSystem.Mapper;

import java.util.List;

import com.InsuranceSystem.Customer.Customer;
import com.InsuranceSystem.Development.Insurance;
import com.InsuranceSystem.Development.Life;
import org.apache.ibatis.annotations.Mapper;

import com.InsuranceSystem.Sale.AssociationCus;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SubInfoMapper {
	
	public List<AssociationCus> select_subInfo();

	public List<AssociationCus> select_disapproval_SubInfo();

	public int select_count_subInfo();

	public int select_count_disapproval_SubInfo();

	public Customer select_Customer_by_id(@Param("id") int id);

	public Life select_Ins_by_id(@Param("id") int id);		// insurance가 추상 객체라 임시로 Life 사용
	
	public int insert_subInfo(AssociationCus associationCus);
	
	public int select_subInfo_customerIDtoInsuranceID(@Param("customerID") int customerID);
}
