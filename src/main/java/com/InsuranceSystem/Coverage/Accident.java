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
public class Accident {

	private int accidentID;
	private String location; // ��� ���
	private String customerName; // �̸�
	private int customerID; // ��ID
	private LocalDate occuredAccidentDate; // ���߻��ð�
	private boolean hospitalization; // �Կ�����
	private String hospitalPlace; // �Կ����
	private AccidentCause accidentCause;
	private int yieldAmount; // �����
	private int receivedAmount; // �޴� �ݾ�


	public String toStringAccidentDate() {
		return occuredAccidentDate.getYear() + "년 " + occuredAccidentDate.getMonth() + "월 "
				+ occuredAccidentDate.getDayOfMonth() + "일";
	}

}
