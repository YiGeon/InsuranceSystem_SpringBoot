package com.InsuranceSystem.Sale;

import java.time.LocalDate;
import java.util.ArrayList;

import com.InsuranceSystem.Customer.Account;
import com.InsuranceSystem.Customer.Customer;
import com.InsuranceSystem.Development.Fire;
import com.InsuranceSystem.Development.Insurance;
import com.InsuranceSystem.Development.Life;
import com.InsuranceSystem.Development.LossProportionality;

import DB.CustomerDaoImpl;
import DB.InsuranceDaoImpl;
import DB.SubInfoImpl;

public class SalesListImpl implements SalesList {

	private Customer customer;
	private Account account;

	
	private static ArrayList<Subscribers> subscriberList = new ArrayList<Subscribers>(); // 가입자 리스트
	private static ArrayList<Customer> customerList = new ArrayList<Customer>(); // 고객 리스트 --> 가입자가 승인이 되면 고객이 된다.

	private static ArrayList<AssociationSub> insuranceSubscriber = new ArrayList<AssociationSub>(); // 보험가입자-->승인이 되면
																									// 보험과 가입자를 보험과 고객으로
																									// 넘긴다.
	private static ArrayList<AssociationCus> insuranceCustomer = new ArrayList<AssociationCus>(); // 보험고객
	private CustomerDaoImpl customerDaoImpl;
	private SubInfoImpl subInfoImpl;
	private InsuranceDaoImpl insuranceDaoImpl;
	private ArrayList<Insurance> insuranceList;
	
	public SalesListImpl() {
		this.customerDaoImpl = new CustomerDaoImpl();
		this.subInfoImpl = new SubInfoImpl();
		this.insuranceDaoImpl = new InsuranceDaoImpl();
		this.insuranceList = new ArrayList<Insurance>();
		
		this.setInsuranceList(insuranceDaoImpl.select());
		this.setCustomerList(customerDaoImpl.select());
		this.setInsuranceCustomer(subInfoImpl.select());

	}

	public Subscribers addSubscriberList(Insurance insurance, Subscribers subscribers) {
		AssociationSub associationsub = new AssociationSub();
		associationsub.setInsuranceID(insurance.getInsuranceID());
		associationsub.setSubscribersID(subscribers.getSubscribersID());
		subscriberList.add(subscribers);
		insuranceSubscriber.add(associationsub);

		return subscribers;
	}

	// 승인 대기중인 고객과 보험 보여주기
	public Insurance insuranceshow() {
		for (AssociationSub associationSub : insuranceSubscriber) {
			Insurance insurance = getInsurance(associationSub.getInsuranceID());
			Subscribers subscribers = getSubscriber(associationSub.getSubscribersID());

			System.out.print("보험명: " + insurance.getInsuranceName() + " ");
			System.out.print("가입자 임시ID: " + subscribers.getSubscribersID() + " ");
			System.out.print("가입자명: " + subscribers.getName() + " ");
			System.out.println("보험 타입 :" + insurance.getInsuranceType());
		}

		return null;
	}

	// 보험 ID를 이용하여 보험정보를 가져오는 메소드 ▲위의 함수 에서 쓸 것!
	public Insurance getInsurance(int insuranceID) {
		for (Insurance insurance : this.insuranceList) {
			if (insurance.getInsuranceID() == insuranceID) {
				return insurance;
			}
		}
		return null;
	}

	// getSubscriberID로 Insurance를
	public Subscribers getSubscriber(int subscriberID) {
		for (Subscribers subscribers : SalesListImpl.subscriberList) {
			if (subscribers.getSubscribersID() == subscriberID) {
				return subscribers;
			}
		}
		return null;
	}

	public Customer getCusomter(int customerID) {
		for (Customer customer : SalesListImpl.customerList) {
			if (customer.getCustomerID() == customerID) {
				return customer;
			}
		}
		return null;
	}

	public void addCustomerList(int subscriberID) {
		for (AssociationSub associationSub : SalesListImpl.insuranceSubscriber) {
			if (associationSub.getSubscribersID() == subscriberID) {
				Subscribers subscribers = this.getSubscriber(subscriberID);
				Insurance insurance = getInsurance(associationSub.getInsuranceID());
				changeCustomer(insurance.getInsuranceID(), subscribers);
				calculateGuarantee(subscriberID);
				return;
			}
		}
		System.out.println("해당 고객이 없습니다.");

	}

	// customerID를 넣으면 해당 customer가 가입한 보험의 정보를 리턴한다.
	public Insurance returnInsurance(int customerID) {

		for (AssociationCus associationCus : this.getInsuranceCustomer()) {
			if (customerID == associationCus.getCustomerID()) {
				return getInsurance(associationCus.getInsuranceID());
			}
		}
		return null;
	}

	public AssociationCus associationIC(int insuranceID, int customerID) {
		AssociationCus associationCus = new AssociationCus();
		associationCus.setInsuranceID(insuranceID);
		associationCus.setCustomerID(customerID);
		this.subInfoImpl.insert(getInsurance(insuranceID), getCusomter(customerID));
		this.setInsuranceCustomer(this.subInfoImpl.select());

		return null;

	}

	public double calculateGuarantee(int customerId) {
		double rate = 0;
		double guaranteeAmount = 0;
		for (Customer customer : SalesListImpl.customerList) {
			if (customer.getCustomerID() == customerId) {
				switch (returnInsurance(customerId).getInsuranceType()) {
				case Life:
					rate = returnInsurance(customerId).calculateRate(getCusomter(customerId));
					guaranteeAmount = ((Life) returnInsurance(customerId)).getGuaranteeAmount() * rate;
					customer.setPremium((int) guaranteeAmount);
					System.out.println("보험료는 " + (int) guaranteeAmount + "원 입니다.");
					return guaranteeAmount;
				case Fire:
					rate = returnInsurance(customerId).calculateRate(getCusomter(customerId));
					guaranteeAmount = ((Fire) returnInsurance(customerId)).getGuaranteeAmount() * rate;
					customer.setPremium((int) guaranteeAmount);
					System.out.println("보험료는 " + (int) guaranteeAmount + "원 입니다.");
					return guaranteeAmount;
				case LossProportionality:
					rate = returnInsurance(customerId).calculateRate(getCusomter(customerId));
					guaranteeAmount = ((LossProportionality) returnInsurance(customerId)).getGuaranteeAmount() * rate;
					customer.setPremium((int) guaranteeAmount);
					System.out.println("보험료는 " + (int) guaranteeAmount + "원 입니다.");
					break;
				}
			}
		}

		return 0;
	}

	// 가입자를 고객으로 변하게 해주는 함수
	public void changeCustomer(int insuranceID, Subscribers subscribers) {

		customer = new Customer();
		account = new Account();
		customer.setAge(subscribers.getAge());
		customer.setEmail(subscribers.getEmail());
		customer.setGender(subscribers.isGender());
		customer.setJob(subscribers.getJob());
		customer.setMedicalHistory(subscribers.getS_medicalHistory());
		customer.setAccidentHistory(subscribers.isAccidentHistory());
		customer.setCustomerName(subscribers.getName());
		customer.setBuildingClass(subscribers.getS_buildingClass());
		customer.setPhoneNo(subscribers.getPhoneNo());
		customer.setResidentNo(subscribers.getResidentNo());
		customer.setPremium(subscribers.getMoney());
		account.setCustomerName(subscribers.getName());
		account.setAccountNumber(subscribers.getAccountNumber());
		account.setMoney(subscribers.getMoney());
		customer.setAccount(account);

		customer.setAddInsuranceDate(LocalDate.now());
		LocalDate date = LocalDate.now();
		date.plusYears(getInsurance(insuranceID).getContractConditions().getPeriod());
		customer.setMaturityDate(date);
		this.customerDaoImpl.insert(customer);
		this.setCustomerList(customerDaoImpl.select());

		// Insurance와 Cusotmer를 연결하는 함수
		for (Customer selectCustomer : customerList) {
			if (selectCustomer.getAccount().getAccountNumber().equals(customer.getAccount().getAccountNumber())) {
				associationIC(insuranceID, selectCustomer.getCustomerID());
				System.out.println(selectCustomer.getCustomerID());
			}
		}

		System.out.println(customer.getCustomerName() + " 고객님의 가입을 승인하셨습니다.");
		insuranceSubscriber.remove(new AssociationSub(insuranceID, subscribers.getSubscribersID()));
		subscriberList.remove(subscribers);

	}


	public ArrayList<Insurance> getInsuranceList() {
		return insuranceList;
	}

	public void setInsuranceList(ArrayList<Insurance> insuranceList) {
		this.insuranceList = insuranceList;
	}

	public ArrayList<Subscribers> getSubscriberList() {
		return subscriberList;
	}

	public void setSubscriberList(ArrayList<Subscribers> subscriberList) {
		SalesListImpl.subscriberList = subscriberList;
	}

	public ArrayList<AssociationSub> getInsuranceSubscriber() {
		return insuranceSubscriber;
	}

	public void setInsuranceSubscriber(ArrayList<AssociationSub> insuranceSubscriber) {
		SalesListImpl.insuranceSubscriber = insuranceSubscriber;
	}

	public ArrayList<AssociationCus> getInsuranceCustomer() {
		return insuranceCustomer;
	}

	public void setInsuranceCustomer(ArrayList<AssociationCus> insuranceCustomer) {
		SalesListImpl.insuranceCustomer = insuranceCustomer;
	}

	public ArrayList<Customer> getCustomerList() {
		return customerList;
	}

	public void setCustomerList(ArrayList<Customer> customerList) {
		SalesListImpl.customerList = customerList;
	}

}