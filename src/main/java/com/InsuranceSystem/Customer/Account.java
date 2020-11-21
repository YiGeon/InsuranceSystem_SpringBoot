package com.InsuranceSystem.Customer;

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
public class Account {
	private String accountNumber; // ���¹�ȣ
	private String customerName; // ��������
	private int money; // �ڻ�

	

}
