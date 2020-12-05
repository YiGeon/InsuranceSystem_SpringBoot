package com.InsuranceSystem.Development;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.InsuranceSystem.Customer.Customer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@Lazy
@Scope("prototype")
public class Fire extends Insurance {

	private double buildingClassRate; // �ǹ���޿���
	private double tariff;
	private double payCount;
	private int guaranteeAmount; // �����

	public Fire() {
		super();
		super.setInsuranceType(insuranceType.Fire);
		
	}

	public double calculateRate(Customer customer) {

		if (customer.isGender())
			tariff *= 0.5; // ����
		else
			tariff *= 0.4; // ����
		switch ((customer.getAge() / 10) * 10) {
		case 0:
			tariff *= 0.5;
			break;
		case 10:
			tariff *= 0.4;
			break;
		case 20:
			tariff *= 0.3;
			break;
		case 30:
			tariff *= 0.5;
			break;
		case 40:
			tariff *= 0.6;
			break;
		case 50:
			tariff *= 0.6;
			break;
		case 60:
			tariff *= 0.8;
			break;
		case 70:
			tariff *= 0.9;
			break;
		default:
			tariff *= 1.0;
			break;
		}
		switch (customer.getBuildingClass()) {
		case one_grade:
			buildingClassRate *= 0.7;
		case two_grade:
			buildingClassRate *= 1.1;
		case three_grade:
			buildingClassRate *= 1.5;
		case four_grade:
			buildingClassRate *= 1.8;
		}

		
		return (double) (tariff * buildingClassRate * 0.1);
	}

}