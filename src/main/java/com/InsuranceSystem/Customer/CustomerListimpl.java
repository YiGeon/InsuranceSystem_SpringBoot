package com.InsuranceSystem.Customer;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Scanner;

import com.InsuranceSystem.Customer.Customer.BuildingClass;
import com.InsuranceSystem.Customer.Customer.Job;
import com.InsuranceSystem.Customer.Customer.MedicalHistory;
import com.InsuranceSystem.Development.Insurance;
import com.InsuranceSystem.Sale.SalesListImpl;

import DB.CustomerDaoImpl;

public class CustomerListimpl implements CustomerList {

	static Scanner sc = new Scanner(System.in);
	static ArrayList<Customer> customerList;
	private SalesListImpl salesListImpl;
	private CustomerDaoImpl customerDaoImpl;


	public CustomerListimpl() {
		salesListImpl = new SalesListImpl();
		customerDaoImpl = new CustomerDaoImpl();
		
		this.setCustomerList(customerDaoImpl.select());
		
	}

	// 고객 추가 하기
	public boolean add(Customer customer) {
		customerList.add(customer);
		customerDaoImpl.insert(customer);
		return true;
	}

	// 상품 해지 하기
	public boolean delete(int customerID) {

		Iterator<Customer> customerIterator = customerList.iterator();
		while (customerIterator.hasNext()) {
			Customer customer = customerIterator.next();
			if (customer.getCustomerID() == customerID) {
				customerList.remove(salesListImpl.getCusomter(customerID));
				customerDaoImpl.delete(customer);
				customerDaoImpl.select();
				break;
			}
		}

		return false;
	}

	// 만기 계약 관리 하기
	public boolean maturityContractsManaging(int customerID) {
		for (Customer customer : customerList) {
			if (customer.getCustomerID() == customerID) {
				Calendar calendar = Calendar.getInstance();
				int year = calendar.get(Calendar.YEAR);
				int month = calendar.get(Calendar.MONTH) + 1;
//				if (year == customer.getMaturityDate().get(Calendar.YEAR)
//						&& month == customer.getMaturityDate().get(Calendar.MONTH) + 1) {
//					System.out.println("현재 가입된 보험이 만기된 상태입니다.");
//					System.out.println("재계약을 진행하시겠습니까?   1. 예   2. 아니오");
//					int index = sc.nextInt();
//					if (index == 1) {
//						System.out.println("다시 계약할 기간을 정해주십시오.");
//						int contractPeriod = sc.nextInt();
//						salesListImpl.returnInsurance(customerID).getContractConditions().setPeriod(contractPeriod);
//						System.out.println("만기일은 " + customer.maturityDatetoString(customer.getMaturityDate())
//								+ " 재계약이 완료되었습니다. 만기환급금이 상환됩니다.");
//
//					}
//				} else {
//					System.out.println("현재 가입된 보험은 만기되지 않았습니다.");
//				}
			}
		}
		return false;
	}

	// 고객 정보 확인
	public Customer search(int customerID) {

		for (Customer customer : customerList) {
			if (customer.getCustomerID() == customerID) {
				System.out.print("고객명 : " + customer.getName() + " ");
				System.out.print("고객ID : " + customer.getCustomerID() + " ");
				System.out.print("나이 : " + customer.getAge() + " ");
				System.out.print("이메일 : " + customer.getEmail() + " ");
				System.out.print("전화번호 : " + customer.getPhoneNo() + " ");
				System.out.print("주민번호 : " + customer.getResidentNo() + " ");
				System.out.print("현재 자산" + customer.getAccount().getMoney() + " ");
				System.out.print("고객 건물 급수 : " + customer.getBuildingClass() + " ");
				System.out.print("고객 직업 : " + customer.getJob() + " ");
				System.out.println("고객병력 : " + customer.getMedicalHistory());

				Insurance insurance = salesListImpl.returnInsurance(customer.getCustomerID());
				System.out.print("보험 타입 : " + insurance.getI_type() + " ");
				System.out.print("보험명 : " + insurance.getName() + " ");
				System.out.print("보험ID : " + insurance.getInsuranceID() + " ");
//				System.out.print("보험 가입일 : " + customer.addInsuranceDatetoString(customer.getAddInsuranceDate()) + " ");
//				System.out.println("보험 만기일 : " + customer.maturityDatetoString(customer.getMaturityDate()));
			}
		}
		return null;
	}

	public Customer show() {
		
		for (Customer customer : customerList) {
			System.out.print("고객명 : " + customer.getName() + " ");
			System.out.println("고객ID : " + customer.getCustomerID() + " ");

			Insurance insurance = salesListImpl.returnInsurance(customer.getCustomerID());
			System.out.print("보험 타입 : " + insurance.getI_type() + " ");
			System.out.print("보험명 : " + insurance.getName() + " ");
			System.out.println("보험ID : " + insurance.getInsuranceID());
		}
		return null;
	}

	public ArrayList<Customer> getCustomerList() {
		return customerList;
	}

	public void setCustomerList(ArrayList<Customer> customerList) {
		CustomerListimpl.customerList = customerList;
	}

	public void changeCustomer(int customerID) {
		for (Customer customer : CustomerListimpl.customerList) {
			int index = 0;
			if (customer.getCustomerID() == customerID) {
				System.out.println("고객 정보를 입력합니다.");
				System.out.println("이름을 입력해 주세요.");
				customer.setName(sc.nextLine());

				System.out.println("나이를 입력해 주세요.");
				customer.setAge(sc.nextInt());
				sc.nextLine();

				System.out.println("이메일을 입력해 주세요.");
				customer.setEmail(sc.nextLine());

				System.out.println("전화번호를 입력해 주세요.");
				customer.setPhoneNo(sc.nextLine());

				System.out.println("주민번호를 입력해 주세요.");
				customer.setResidentNo(sc.nextLine());

				System.out.println("직업을 선택해주세요.");
				boolean boo = true;
				while (boo) {
					System.out.println("1. 운전사  2. 군인  3. 경찰   4. 공무원  5. 학생  6. 변호사  7. 의사");
					switch (sc.nextInt()) {
					case 1:
						customer.setJob(Job.driver);
						boo = false;
						break;
					case 2:
						customer.setJob(Job.soldier);
						boo = false;
						break;
					case 3:
						customer.setJob(Job.policer);
						boo = false;
						break;
					case 4:
						customer.setJob(Job.officer);
						boo = false;
						break;
					case 5:
						customer.setJob(Job.student);
						boo = false;
						break;
					case 6:
						customer.setJob(Job.lawer);
						boo = false;
						break;
					case 7:
						customer.setJob(Job.doctor);
						boo = false;
						break;
					default:
						System.out.println("잘못 입력하셨습니다. 다시 입력해 주세요.");
						break;
					}
				}
				sc.nextLine();

				customer.setCustomerID(customer.getCustomerID());

				Insurance insurance = salesListImpl.returnInsurance(customer.getCustomerID());

				switch (insurance.getI_type()) {
				case Life:
					System.out.println("생명보험 정보를 입력해 주세요.");
					System.out.println("병력에 해당하는 번호를 입력해 주세요.");
					System.out.println("1. 골절  2. 암  3. 고혈압  4. 당뇨  5. 기타  6. 없음");
					switch (sc.nextInt()) {
					case 1:
						customer.setMedicalHistory(MedicalHistory.fracture);
						break;
					case 2:
						customer.setMedicalHistory(MedicalHistory.cancer);
						break;
					case 3:
						customer.setMedicalHistory(MedicalHistory.bloodpresure);
						break;
					case 4:
						customer.setMedicalHistory(MedicalHistory.diabetes);
						break;
					case 5:
						customer.setMedicalHistory(MedicalHistory.etc);
						break;
					case 6:
						customer.setMedicalHistory(MedicalHistory.nothing);
						break;
					default:
						System.out.println("잘못입력하셨습니다.");
						break;

					}
					break;
				case Fire:
					System.out.println("화재보험 정보를 입력해 주세요.");
					System.out.println("건물구조급수를 입력해 주세요.");
					switch (sc.nextInt()) {
					case 1:
						customer.setBuildingClass(BuildingClass.one_grade);
						break;
					case 2:
						customer.setBuildingClass(BuildingClass.two_grade);
						break;
					case 3:
						customer.setBuildingClass(BuildingClass.three_grade);
						break;
					case 4:
						customer.setBuildingClass(BuildingClass.four_grade);
						break;
					default:
						System.out.println("잘못입력하셨습니다.");
						break;
					}
					break;
				case LossProportionality:
					System.out.println("실손비례보험 정보를 입력해 주세요.");
					System.out.println("입웝이력유무를 확인해 주세요. 없으면 0, 있으면 1을 입력해 주세요.");
					if (sc.nextInt() == 0) {
						customer.setAccidentHistory(false);
					} else if (sc.nextInt() == 1) {
						customer.setAccidentHistory(true);
					}
				}

				customerList.set(index, customer);
				customerDaoImpl.update(customer);
				customerDaoImpl.select();
				System.out.println("변경이 완료되었습니다.");
				return;
			}

			index++;
		}
		System.out.println("해당 고객이 없습니다.");
	}

}