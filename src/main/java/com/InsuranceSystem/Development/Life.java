package com.InsuranceSystem.Development;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.InsuranceSystem.Customer.Customer;
import com.InsuranceSystem.Sale.SalesListImpl;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@Lazy
@Scope("prototype")
public class Life extends Insurance {

	private double ageDividendRate; // ���̹��
	private double caseHistoryDividendRate; // ���¹��
	private int guaranteeAmount; // �����
	private double tariff; // ����
	private int payCount; // ����Ƚ�� �����̸� 12 * ���Գ�� �ֳ��̸� 52 * ���Գ��
	private SalesListImpl salesListImpl;

	public Life() {
		super();
	}

	public double calculateRate(Customer customer) {
		salesListImpl = new SalesListImpl();
		if (customer.isGender())
			tariff *= 0.5; // ����
		else
			tariff *= 0.4; // ����

		switch (customer.getJob()) {
		case driver:
			tariff *= 0.7;
			break;
		case officer:
			tariff *= 0.5;
			break;
		case doctor:
			tariff *= 0.4;
			break;
		case lawer:
			tariff *= 0.4;
			break;
		case policer:
			tariff *= 0.8;
			break;
		case soldier:
			tariff *= 0.7;
			break;
		case student:
			tariff *= 0.6;
			break;
		}

		switch ((customer.getAge() / 10) * 10) {
		case 0:
			ageDividendRate *= 0.5;
			break;
		case 10:
			ageDividendRate *= 0.4;
			break;
		case 20:
			ageDividendRate *= 0.3;
			break;
		case 30:
			ageDividendRate *= 0.5;
			break;
		case 40:
			ageDividendRate *= 0.6;
			break;
		case 50:
			ageDividendRate *= 0.6;
			break;
		case 60:
			ageDividendRate *= 0.8;
			break;
		case 70:
			ageDividendRate *= 0.9;
			break;
		default:
			ageDividendRate *= 1.0;
			break;
		}

		switch (customer.getMedicalHistory()) {
		case nothing:
			caseHistoryDividendRate *= 0.1;
			break;
		case cancer:
			caseHistoryDividendRate *= 0.9;
			break;
		case bloodpresure:
			caseHistoryDividendRate *= 0.8;
			break;
		case diabetes:
			caseHistoryDividendRate *= 0.6;
			break;
		case etc:
			caseHistoryDividendRate *= 0.4;
			break;
		case fracture:
			caseHistoryDividendRate *= 0.5;
			break;
		}

		switch (salesListImpl.returnInsurance(customer.getCustomerID()).getContractConditions().getPayCycle()) {
		case month:
			payCount = 12 * salesListImpl.returnInsurance(customer.getCustomerID()).getContractConditions().getPeriod();
			break;
		case week:
			payCount = 52 * salesListImpl.returnInsurance(customer.getCustomerID()).getContractConditions().getPeriod();
			break;
		}

		return (double) (tariff * ageDividendRate * caseHistoryDividendRate) / payCount;

	}


}