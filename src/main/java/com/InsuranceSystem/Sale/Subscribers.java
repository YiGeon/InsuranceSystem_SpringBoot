package com.InsuranceSystem.Sale;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.InsuranceSystem.Customer.Customer.BuildingClass;
import com.InsuranceSystem.Customer.Customer.Job;
import com.InsuranceSystem.Customer.Customer.MedicalHistory;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@Lazy
@Scope("prototype")
public class Subscribers {

	private int subscribersID = 0;
	private boolean accidentHistory;
	private int age;
	private BuildingClass s_buildingClass;
	private String email;
	private boolean gender;
	private Job job;
	private MedicalHistory s_medicalHistory;
	private String name;
	private String phoneNo;
	private String residentNo;
	private String accountNumber;
	private int money; // ���¿� ���� ���� �Է¹޴´�. ���� ������ ������ ���� ���� ����
	public SalesListImpl m_SalesListImpl;
	static int count = 0;

	public Subscribers() {
		count++;
		this.subscribersID = subscribersID + count;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Subscribers other = (Subscribers) obj;
		if (subscribersID != other.subscribersID)
			return false;
		return true;
	}


}