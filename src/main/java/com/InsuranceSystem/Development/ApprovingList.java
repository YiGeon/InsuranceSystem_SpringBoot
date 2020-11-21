package com.InsuranceSystem.Development;


/**
 * @author JeongChanho
 * @version 1.0
 * @created 24-4-2020 ���� 12:27:08
 */
public interface ApprovingList {

	public Insurance approve(String insuranceName);
	public Insurance show();
	public Insurance delete(int insuranceID);

}