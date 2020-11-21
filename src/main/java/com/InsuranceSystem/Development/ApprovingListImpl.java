package com.InsuranceSystem.Development;

import java.sql.SQLException;
import java.util.ArrayList;

import DB.InsuranceDaoImpl;

public class ApprovingListImpl implements ApprovingList {

	DevelopingListImpl developingListImpl;
	private ArrayList<Insurance> waitingApproveList;
	static ArrayList<Insurance> approvingCompleteList = new ArrayList<Insurance>();
	private InsuranceDaoImpl insuranceDaoImpl;

	public ApprovingListImpl() {
		insuranceDaoImpl = new InsuranceDaoImpl();
		
			this.setApprovingCompleteList(insuranceDaoImpl.select());
		
	}

	// 상품을 승인하고 DB에 저장
	public Insurance approve(String insuranceName) {

		for (Insurance insurance : waitingApproveList) {
			if (insurance.getInsuranceName().equals(insuranceName)) {

				ApprovingListImpl.approvingCompleteList.add(insurance);
				System.out.println(insurance.getInsuranceName() + " 상품이 승인되었습니다.");
				insuranceDaoImpl.insert(insurance);
				System.out.println("DB저장 완료!");
				
				this.waitingApproveList.remove(insurance);
				break;
			}
		}

		return null;
	}

	public Insurance show() {
		
		approvingCompleteList = insuranceDaoImpl.select();

		for (Insurance insurance : approvingCompleteList) {
			if (approvingCompleteList.size() == 0) {
				System.out.println("설계된 보험이 없습니다.");
			}
			System.out.print("보험 타입 : " + insurance.getInsuranceType());
			System.out.print(" 보험명 : " + insurance.getInsuranceName());
			System.out.println(" 보험ID : " + insurance.getInsuranceID());
		}
		return null;
	}

	public Insurance delete(int insuranceID) {
		for (Insurance insurance : approvingCompleteList) {
			if (insurance.getInsuranceID() == insuranceID) {
				approvingCompleteList.remove(insurance);
				System.out.println("해당 보험을 삭제합니다.");
				insuranceDaoImpl.delete(insurance);
				System.out.println("DB삭제 완료!");
				insuranceDaoImpl.select();
				break;
			} else {
				System.out.println("잘못입력하셨습니다.");
				return null;
			}
		}
		return null;
	}

	public ArrayList<Insurance> getApprovingCompleteList() {
		return approvingCompleteList;
	}

	public void setApprovingCompleteList(ArrayList<Insurance> approvingCompleteList) {
		ApprovingListImpl.approvingCompleteList = approvingCompleteList;
	}

	public ArrayList<Insurance> getWaitingApproveList() {
		return waitingApproveList;
	}

	public void setWaitingApproveList(ArrayList<Insurance> waitingApproveList) {
		this.waitingApproveList = waitingApproveList;
	}
}