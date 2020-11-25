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
public class LossProportionality extends Insurance {

	private int limitRate; // �ѵ���
	private double tariff; // ����
	private int guaranteeAmount; // ����� --> ��� ���� ��������� �Ǻ����� ������� �ִ� ������ �ƴϴ�. ��� ���ϰ� �Ϸ��� ����. ��������� 1����� ������
									// 1200���� ������� �Ѵ�.
	private int payCount;
	

	public LossProportionality() {
		super();
		super.setInsuranceType(insuranceType.LossProportionality);
	}

	public double calculateRate(Customer customer) {

		if (customer.isAccidentHistory())
			tariff *= 0.6;
		else
			tariff *= 0.3;

		if (customer.isGender())
			tariff *= 0.5;
		else
			tariff *= 0.4;

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
			tariff *= 0.5;
			break;
		case 10:
			tariff *= 0.4;
			break;
		case 20:
			tariff *= 0.3;
			break;
		case 30:
			tariff *= 0.4;
			break;
		case 40:
			tariff *= 0.5;
			break;
		case 50:
			tariff *= 0.6;
			break;
		case 60:
			tariff *= 0.7;
			break;
		case 70:
			tariff *= 0.8;
			break;
		default:
			tariff *= 0.9;
			break;
		}
		

		return (double) tariff / payCount;
	}

}