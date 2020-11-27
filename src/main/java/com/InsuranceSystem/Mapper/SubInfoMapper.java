package com.InsuranceSystem.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.InsuranceSystem.Sale.AssociationCus;

@Mapper
public interface SubInfoMapper {
	
	public List<AssociationCus> select_subInfo();
	
	public void insert_subInfo(AssociationCus associationCus);
}
