package com.InsuranceSystem.Coverage;

import java.time.LocalDate;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@Lazy
@Scope("prototype")
public class DamageAssessed {
	private int damageAssessedID;
	private String location; // ��� ���
	private String customerName; // �̸�
	private int customerID; // ��ID
	private LocalDate occuredAccidentDate; // ���߻��ð�
	private boolean hospitalization; // �Կ�����
	private String hospitalPlace; // �Կ����
	private AccidentCause accidentCause;
	private int yieldAmount; //�����
	private int receivedAmount; //�޴� �ݾ�

	
	public DamageAssessed() {

	}

	public LocalDate getOccuredAccidentDate() {
		return occuredAccidentDate;
	}

	public void setOccuredAccidentDate(LocalDate occuredAccidentDate) {
		this.occuredAccidentDate = occuredAccidentDate;
	}

	public boolean isHospitalization() {
		return hospitalization;
	}

	public void setHospitalization(boolean hospitalization) {
		this.hospitalization = hospitalization;
	}

	public String getHospitalPlace() {
		return hospitalPlace;
	}

	public void setHospitalPlace(String hospitalPlace) {
		this.hospitalPlace = hospitalPlace;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getName() {
		return customerName;
	}

	public void setName(String name) {
		this.customerName = name;
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public AccidentCause getAccidentCause() {
		return accidentCause;
	}

	public void setAccidentCause(AccidentCause accidentCause) {
		this.accidentCause = accidentCause;
	}

	public String toStringAccidentDate() {
		return occuredAccidentDate.getYear() + "년 " + occuredAccidentDate.getMonth() + "월 " + occuredAccidentDate.getDayOfMonth() + "일";
	}

	public int getYieldAmount() {
		return yieldAmount;
	}

	public void setYieldAmount(int yieldAmount) {
		this.yieldAmount = yieldAmount;
	}

	public int getReceivedAmount() {
		return receivedAmount;
	}

	public void setReceivedAmount(int receivedAmount) {
		this.receivedAmount = receivedAmount;
	}


	public int getDamageAssessedID() {
		return damageAssessedID;
	}

	public void setDamageAssessedID(int damageAssessedID) {
		this.damageAssessedID = damageAssessedID;
	}
}
