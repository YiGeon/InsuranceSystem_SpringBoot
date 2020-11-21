package com.InsuranceSystem.Development;

import java.util.ArrayList;

public class DevelopingListImpl implements DevelopList {
	private static ArrayList<Insurance> waitingApproveList = new ArrayList<Insurance>();
	private ApprovingListImpl approvingListImpl;
	// 생성자
	public DevelopingListImpl() {
		approvingListImpl = new ApprovingListImpl();
	}

	// DTO
	public ArrayList<Insurance> getWaitingApproveList() {
		return waitingApproveList;
	}

	public void setWaitingApprovalList(ArrayList<Insurance> waitingApproveList) {
		DevelopingListImpl.waitingApproveList = waitingApproveList;
	}

	// 승인 대기중인 보험상품을 보여준다.
	public Insurance show() {
		// waitingApproveList의 상품이 존재하지 않을 때 실행 됨
		if (waitingApproveList.size() == 0) {
			System.out.println("설계된 상품이 없습니다.");
		} else {
			System.out.println("현재 설계되어 있는 보험 목록");
			for (Insurance insurance : DevelopingListImpl.waitingApproveList) {
				System.out.println("보험타입 : " + insurance.getInsuranceType() + " 보험이름 : " + insurance.getInsuranceName());
			}
		}
		return null;
	}

	// 상품을 추가한다.
	public boolean add(Insurance insurance) {
		for (Insurance insurance2 : DevelopingListImpl.waitingApproveList) {
			if (insurance2.getInsuranceName().equals(insurance.getInsuranceName())) {
				System.out.println("중복된 보험 이름입니다.");
				return false;
			}
		}
		for (Insurance insurance3 : approvingListImpl.getApprovingCompleteList()) {
			if (insurance3.getInsuranceName().equals(insurance.getInsuranceName())) {
				System.out.println("중복된 보험 이름입니다.");
				return false;
			}
		}
		DevelopingListImpl.waitingApproveList.add(insurance);

		return true;
	}

}