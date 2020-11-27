package com.InsuranceSystem.Customer;

import java.time.LocalDate;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

/**
 * 0
 * 
 * @author JeongChanho
 * @version 1.0
 * @created 24-4-2020 ���� 12:27:08
 */

@Getter
@Setter
@Component
@Lazy
@Scope("prototype")
public class Customer {

	private int customerID;
	private String customerName;
	private boolean gender;
	private String phoneNo;
	private String residentNo;
	private int age;
	private int premium;				//���� ����Ḧ ��Ÿ����.
	private String email;
	private String accountNumber;
	private LocalDate addInsuranceDate;
	private LocalDate maturityDate;
	private boolean accidentHistory;
	private Job job;
	private MedicalHistory medicalHistory;
	private BuildingClass buildingClass;
	private boolean state;				//상태값
	
	public Customer() {
	}

	public enum BuildingClass {
		one_grade, two_grade, three_grade, four_grade, 
	}

	public enum MedicalHistory {
		cancer, nothing, bloodpresure, fracture, diabetes, etc
	}

	public enum Job {
		driver, soldier, policer, officer, student, lawer, doctor
	}


	public String addInsuranceDatetoString(LocalDate time) {
		return addInsuranceDate.getYear() + "년 " + addInsuranceDate.getMonth() + "월 " + addInsuranceDate.getDayOfMonth() + "일";		
	}
	public String maturityDatetoString(LocalDate time) {
		return maturityDate.getYear() + "년 " + maturityDate.getMonth() + "월 " + maturityDate.getDayOfMonth() + "일";		
	}

}
