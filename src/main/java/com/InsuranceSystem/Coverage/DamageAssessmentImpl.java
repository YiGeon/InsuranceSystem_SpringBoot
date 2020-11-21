package com.InsuranceSystem.Coverage;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import com.InsuranceSystem.Customer.Customer;
import com.InsuranceSystem.Customer.CustomerListimpl;
import com.InsuranceSystem.Development.Fire;
import com.InsuranceSystem.Development.Life;
import com.InsuranceSystem.Development.LossProportionality;
import com.InsuranceSystem.Sale.SalesListImpl;

import DB.AccidentDaoImpl;
import DB.CustomerDaoImpl;

public class DamageAssessmentImpl {
	static Scanner sc = new Scanner(System.in);
	private ArrayList<DamageAssessed> investigatedAccidentList = new ArrayList<DamageAssessed>();
	private SalesListImpl salesListImpl;
	private CustomerListimpl customerListimpl;
	private AccidentDaoImpl accidentDaoImpl;
	private CustomerDaoImpl customerDaoImpl;

	public DamageAssessmentImpl() {
		customerListimpl = new CustomerListimpl();
		salesListImpl = new SalesListImpl();
		new AccidentListImpl();
		accidentDaoImpl = new AccidentDaoImpl();
		customerDaoImpl = new CustomerDaoImpl();

		try {
			accidentDaoImpl.selectDamageAssessed();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public DamageAssessed getAccident(int selectID) {
		for (DamageAssessed damageAssessed : investigatedAccidentList) {
			if (selectID == damageAssessed.getCustomerID()) {
				return damageAssessed;
			} else {
				System.out.println("해당 고객이 없습니다.");
			}
		}
		return null;
	}

	public Accident show() {
		for (DamageAssessed accident : investigatedAccidentList) {
			System.out.print("고객 ID : " + accident.getCustomerID() + " ");
			System.out.print("고객 이름 : " + accident.getName() + " ");
			System.out.print("사고 날짜 : " + accident.toStringAccidentDate() + " ");
			System.out.println("사고 원인 : " + accident.getAccidentCause());
		}
		return null;
	}

	public void assessment(int selectID) {
		for (DamageAssessed damageAssessed : investigatedAccidentList) {
			if (damageAssessed.getCustomerID() == selectID) {
				int yieldAmount = 0; // 산출액
				int lossRate = 0; // 손실률
				int guaranteeAmount = 0; // 보장액
				int limitRate = 0; // 한도율
				switch (salesListImpl.returnInsurance(selectID).getInsuranceType()) {
				case Life:
					guaranteeAmount = ((Life) salesListImpl.returnInsurance(selectID)).getGuaranteeAmount();
					if (getAccident(selectID).getAccidentCause().equals(AccidentCause.death)) {
						System.out.println(damageAssessed.getName() + " 고객님의 산출한 손해액을 입력해 주십시오.");
						yieldAmount = sc.nextInt();
						damageAssessed.setYieldAmount(yieldAmount); // 사망하면 사망보험금은 보험에 설정한 보장액 만큼 줌
						damageAssessed.setReceivedAmount(guaranteeAmount);
						System.out.println("입력이 완료되었습니다.");
					} else if (getAccident(selectID).getAccidentCause().equals(AccidentCause.seriousIllness)) {
						System.out.println(damageAssessed.getName() + " 고객님의 산출한 손해액을 입력해 주십시오.");
						yieldAmount = sc.nextInt();
						damageAssessed.setYieldAmount(yieldAmount); // 나머지 큰 질병은 보험에 설정한 보장액의 절반을 줌
						damageAssessed.setReceivedAmount(guaranteeAmount / 2);
						System.out.println("입력이 완료되었습니다.");
					} else {
						System.out.println("다음의 병명은 해당 보험에서 처리하실 수 없습니다.");
					}
					break;
				case Fire:
					guaranteeAmount = ((Fire) salesListImpl.returnInsurance(selectID)).getGuaranteeAmount();
					if (getAccident(selectID).getAccidentCause().equals(AccidentCause.fire)) {
						System.out.println(damageAssessed.getName() + " 고객님의 산출한 손해액을 입력해 주십시오.");
						yieldAmount = sc.nextInt();
						System.out.println("고객 재산의 손실률을 입력해 주십시오.");
						lossRate = sc.nextInt();

						damageAssessed.setYieldAmount(yieldAmount); // 보험금은 피해액 + 피해액 * 손실율/100 만약 피해액이 처음정한 보장액보다 많으면
																	// 보장액만큼만
						// 줌
						if ((yieldAmount + (yieldAmount * lossRate / 100)) < guaranteeAmount) {
							damageAssessed.setReceivedAmount((yieldAmount + (yieldAmount * lossRate / 100)));
							System.out.println("입력이 완료되었습니다.");
						} else {
							damageAssessed.setReceivedAmount(guaranteeAmount);
							System.out.println("입력이 완료되었습니다.");
						}
					} else {
						System.out.println("다음의 병명은 해당 보험에서 처리하실 수 없습니다.");
					}
					break;
				case LossProportionality:
					limitRate = ((LossProportionality) salesListImpl.returnInsurance(selectID)).getLimitRate();
					if (getAccident(selectID).getAccidentCause().equals(AccidentCause.ailment)) {
						System.out.println(damageAssessed.getName() + " 고객님의 산출한 손해액을 입력해 주십시오.");
						yieldAmount = sc.nextInt();

						damageAssessed.setYieldAmount(yieldAmount); // 보험금은 산출한 손해액 * 한도율 만큼 줌
						damageAssessed.setReceivedAmount(yieldAmount * limitRate / 100);
						System.out.println("입력이 완료되었습니다.");
					} else {
						System.out.println("다음의 병명은 해당 보험에서 처리하실 수 없습니다.");
					}
					break;
				}

			}
		}
	}

	public boolean delete(int selectID) {
		Iterator<DamageAssessed> accidentIterator = investigatedAccidentList.iterator();
		while (accidentIterator.hasNext()) {
			DamageAssessed damageAssessed = accidentIterator.next();
			if (damageAssessed.getCustomerID() == selectID) {
				try {
					accidentDaoImpl.deleteDamageAssessed(damageAssessed);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				try {
					accidentDaoImpl.selectDamageAssessed();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				investigatedAccidentList.remove(getAccident(selectID));
				break;
			}
		}

		return false;
	}

	public boolean receivedAmount(int selectID) {
		for (DamageAssessed accident : investigatedAccidentList) {
			if (accident.getCustomerID() == selectID) {
				System.out.println(
						accident.getName() + "고객님이 현재 받으실 수 있는 보험금은 " + accident.getReceivedAmount() + "원입니다.");
				System.out.println("보험금을 수령하시겠습니까? 1. 예, 2. 아니오");
				int index = sc.nextInt();
				if (index == 1) {
					for (Customer customer : customerListimpl.getCustomerList()) {
						if (customer.getCustomerID() == accident.getCustomerID()) {
							System.out.println(customer.getCustomerName() + "고객님의 계좌로 보험금 " + accident.getReceivedAmount()
									+ "원 이 이체되었습니다.");
							customer.setPremium(customer.getPremium() + accident.getReceivedAmount());
							customerDaoImpl.update(customer);
						} else {
							System.out.println("수령 오류");
						}
					}
				} else if (index == 2) {
					System.out.println("다음에 다시 시도해 주십시오.");
					return false;
				} else {
					System.out.println("잘못 입력하셨습니다.");
					return false;
				}
			}
		}
		return false;
	}

	public ArrayList<DamageAssessed> getInvestigatedAccidentList() {
		return investigatedAccidentList;
	}

	public void setInvestigatedAccidentList(ArrayList<DamageAssessed> investigatedAccidentList) {
		this.investigatedAccidentList = investigatedAccidentList;
	}

	public boolean change(Accident accident) {
		DamageAssessed damageAssessed = new DamageAssessed();
		damageAssessed.setDamageAssessedID(accident.getAccidentID());
		damageAssessed.setAccidentCause(accident.getAccidentCause());
		damageAssessed.setHospitalization(accident.isHospitalization());
		damageAssessed.setHospitalPlace(accident.getHospitalPlace());
		damageAssessed.setCustomerID(accident.getCustomerID());
		damageAssessed.setName(accident.getCustomerName());
		damageAssessed.setLocation(accident.getLocation());
		damageAssessed.setOccuredAccidentDate(accident.getOccuredAccidentDate());
		damageAssessed.setReceivedAmount(accident.getReceivedAmount());
		damageAssessed.setYieldAmount(accident.getYieldAmount());

		investigatedAccidentList.add(damageAssessed);
		try {
			accidentDaoImpl.insertDanageAssessed(damageAssessed);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			accidentDaoImpl.selectDamageAssessed();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

}
