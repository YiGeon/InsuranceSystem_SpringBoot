package com.InsuranceSystem.Development;

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
public class ContractConditions {

	private String insuranceName;
	private payMethod payMethod;
	private payCycle payCycle;
	private int period;
	private int payment;

	public ContractConditions() {
	}

	public enum payMethod {
		card, BankTransfer, phonePay
	}

	public enum payCycle {
		week, month
	}

}