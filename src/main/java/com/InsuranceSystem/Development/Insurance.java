package com.InsuranceSystem.Development;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.InsuranceSystem.Customer.Customer;

import lombok.Getter;
import lombok.Setter;

/**
 * @author JeongChanho
 * @version 1.0
 * @created 24-4-2020 ���� 12:27:09
 */

@Getter
@Setter
@Component
@Lazy
@Scope("prototype")
public abstract class Insurance {

	ContractConditions contractConditions; // �������
	private String explanation; // ����
	private int insuranceID; // ����ID
	private String insuranceName; // �����̸�
	private int salesRate = 0; // �Ǹŷ�
	private insuranceType insuranceType; // ����Ÿ��
	private boolean state;	//상태값 추가 alter table insurances add state tinyint(1) default 0;
	
	public enum insuranceType {
		Life, Fire, Lossproportionality
	}

	abstract public double calculateRate(Customer customer);


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Insurance other = (Insurance) obj;
		if (insuranceID != other.insuranceID)
			return false;
		if (insuranceName == null) {
			if (other.insuranceName != null)
				return false;
		} else if (!insuranceName.equals(other.insuranceName))
			return false;
		return true;
	}

}