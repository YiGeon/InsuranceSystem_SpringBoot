package com.InsuranceSystem.Sale;

import com.InsuranceSystem.Development.Insurance;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AssociationCus {

	private int subInfoID;
	private int customerID;
	private int insuranceID;
	private String customerName;
	private String insuranceName;
	private Insurance.insuranceType insuranceType;


}