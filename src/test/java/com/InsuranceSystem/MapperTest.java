package com.InsuranceSystem;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.CollectionUtils;

import com.InsuranceSystem.Customer.Customer;
import com.InsuranceSystem.Customer.Customer.BuildingClass;
import com.InsuranceSystem.Customer.Customer.Job;
import com.InsuranceSystem.Customer.Customer.MedicalHistory;
import com.InsuranceSystem.Development.ContractConditions;
import com.InsuranceSystem.Development.ContractConditions.payCycle;
import com.InsuranceSystem.Development.Fire;
import com.InsuranceSystem.Development.Insurance;
import com.InsuranceSystem.Development.Insurance.insuranceType;
import com.InsuranceSystem.Development.Life;
import com.InsuranceSystem.Development.LossProportionality;
import com.InsuranceSystem.Mapper.CustomerMapper;
import com.InsuranceSystem.Mapper.DevelopmentMapper;
import com.InsuranceSystem.Mapper.SubInfoMapper;

@SpringBootTest
public class MapperTest {

	@Autowired
	private DevelopmentMapper developmentMapper;

	@Autowired
	private CustomerMapper customerMapper;

	@Autowired
	private SubInfoMapper subInfoMapper;

	@Test
	public void testInsert_customer() {
		Customer customer = new Customer();
		customer.setCustomerName("테스트4");
		customer.setGender(true);
		customer.setPhoneNo("01010100110");
		customer.setResidentNo("인천광역시 계양구");
		customer.setAge(24);
		customer.setPremium(1);
		customer.setEmail("cksgh3422@nate.com");
		customer.setAccountNumber("계좌번호는 중복되면 안되네요 이거 좀 애매한뎈ㅋzzzzzzz");
		customer.setAddInsuranceDate(LocalDate.now());
		customer.setMaturityDate(null);
		customer.setAccidentHistory(true);
		customer.setJob(Job.student);
		customer.setMedicalHistory(MedicalHistory.fracture);
		customer.setBuildingClass(BuildingClass.one_grade);

		customerMapper.insert_Customer(customer);

	}

	@Test
	public void select_Customer() {
		List<Customer> customerList = customerMapper.select_Customer();
		if (CollectionUtils.isEmpty(customerList) == false) {
			for (Customer customer : customerList) {
				System.out.println("승인 안된 보험 : " + customer.getCustomerName());
			}
		} else {
			System.out.println("안나오지롱ㅋ");
		}
		
		List<Customer> customerList2 = customerMapper.select_ApprovedCustomer();
		if (CollectionUtils.isEmpty(customerList2) == false) {
			for (Customer customer : customerList2) {
				System.out.println("승인 된 보험 : " + customer.getCustomerName());
			}
		} else {
			System.out.println("안나오지롱ㅋ");
		}
	}
	
	@Test
	public void approve_Customer() {
		List<Customer> list = customerMapper.select_Customer();
		Customer customer = list.get(0);
		customerMapper.approve_Customer(customer);
	}
	
	@Test
	public void delete_Customer() {
		List<Customer> list = customerMapper.select_Customer();
		Customer customer = list.get(0);
		customerMapper.delete_Customer(customer);
	}

	@Test
	public void testInsert() {
		Insurance insurance = new Fire();
		ContractConditions contractConditions = new ContractConditions();
		insurance.setInsuranceName("테스트용 보험");
		insurance.setInsuranceType(insuranceType.Fire);
		contractConditions.setInsuranceName("테스트용 보험");
		contractConditions.setPayCycle(payCycle.month);
		insurance.setContractConditions(contractConditions);

		int tmp = developmentMapper.insert_Insurance(insurance);
		System.out.println("결과는 :" + tmp);
	}

	@Test
	public void testSelect() {
		List<Insurance> insuranceList = new ArrayList<Insurance>();

		List<Life> lifeList = developmentMapper.select_Life();
		List<Fire> fireList = developmentMapper.select_Fire();
		List<LossProportionality> lossList = developmentMapper.select_Loss();
		List<ContractConditions> contractList = developmentMapper.select_Contractcondition();

		if (CollectionUtils.isEmpty(lifeList) == false) {
			for (Life tmp : lifeList) {
				Life life = new Life();
				for (ContractConditions ctmp : contractList) {
					if (tmp.getInsuranceName().equals(ctmp.getInsuranceName())) {
						ContractConditions contractConditions = new ContractConditions();
						contractConditions.setInsuranceName(ctmp.getInsuranceName());
						contractConditions.setPayMethod(ctmp.getPayMethod());
						contractConditions.setPayCycle(ctmp.getPayCycle());
						contractConditions.setPayment(ctmp.getPayment());
						contractConditions.setPeriod(ctmp.getPeriod());
						life.setContractConditions(contractConditions);
					}
				}
				System.out.println(tmp.getInsuranceName());
				life.setInsuranceID(tmp.getInsuranceID());
				life.setInsuranceName(tmp.getInsuranceName());
				life.setExplanation(tmp.getExplanation());
				life.setSalesRate(tmp.getSalesRate());
				life.setInsuranceType(insuranceType.Life);

				life.setAgeDividendRate(tmp.getAgeDividendRate());
				life.setCaseHistoryDividendRate(tmp.getCaseHistoryDividendRate());
				life.setGuaranteeAmount(tmp.getGuaranteeAmount());
				life.setTariff(tmp.getTariff());
				life.setPayCount(tmp.getPayCount());

				System.out.println(life.getInsuranceName());
				insuranceList.add(life);
			}
		}
		if (CollectionUtils.isEmpty(fireList) == false) {
			for (Fire tmp : fireList) {
				Fire fire = new Fire();
				for (ContractConditions ctmp : contractList) {
					if (tmp.getInsuranceName().equals(ctmp.getInsuranceName())) {
						ContractConditions contractConditions = new ContractConditions();
						contractConditions.setInsuranceName(ctmp.getInsuranceName());
						contractConditions.setPayMethod(ctmp.getPayMethod());
						contractConditions.setPayCycle(ctmp.getPayCycle());
						contractConditions.setPayment(ctmp.getPayment());
						contractConditions.setPeriod(ctmp.getPeriod());
						fire.setContractConditions(contractConditions);
					}
				}

				fire.setInsuranceID(tmp.getInsuranceID());
				fire.setInsuranceName(tmp.getInsuranceName());
				fire.setExplanation(tmp.getExplanation());
				fire.setSalesRate(tmp.getSalesRate());
				fire.setInsuranceType(insuranceType.Fire);

				fire.setBuildingClassRate(tmp.getBuildingClassRate());
				fire.setGuaranteeAmount(tmp.getGuaranteeAmount());
				fire.setTariff(tmp.getTariff());
				fire.setPayCount(tmp.getPayCount());

				insuranceList.add(fire);
			}
		}
		if (CollectionUtils.isEmpty(lossList) == false) {
			for (LossProportionality tmp : lossList) {
				LossProportionality lossProportionality = new LossProportionality();
				for (ContractConditions ctmp : contractList) {
					if (tmp.getInsuranceName().equals(ctmp.getInsuranceName())) {
						ContractConditions contractConditions = new ContractConditions();
						contractConditions.setInsuranceName(ctmp.getInsuranceName());
						contractConditions.setPayMethod(ctmp.getPayMethod());
						contractConditions.setPayCycle(ctmp.getPayCycle());
						contractConditions.setPayment(ctmp.getPayment());
						contractConditions.setPeriod(ctmp.getPeriod());
						lossProportionality.setContractConditions(contractConditions);
					}
				}

				lossProportionality.setInsuranceID(tmp.getInsuranceID());
				lossProportionality.setInsuranceName(tmp.getInsuranceName());
				lossProportionality.setExplanation(tmp.getExplanation());
				lossProportionality.setSalesRate(tmp.getSalesRate());
				lossProportionality.setInsuranceType(insuranceType.LossProportionality);

				lossProportionality.setLimitRate(tmp.getLimitRate());
				lossProportionality.setGuaranteeAmount(tmp.getGuaranteeAmount());
				lossProportionality.setTariff(tmp.getTariff());
				lossProportionality.setPayCount(tmp.getPayCount());

				insuranceList.add(lossProportionality);
			}
		}

		for (Insurance insurance : insuranceList) {
			System.out.println("결과는 :" + insurance.getInsuranceName());
		}
	}

	@Test
	public void deleteTest() {
		developmentMapper.delete_Insurance(14);
	}

	@Test
	public void updateTest() {
		List<Life> insuranceList = developmentMapper.select_Life();
		Insurance insurance = insuranceList.get(0);
		developmentMapper.approve_Insurance(insurance);
	}
}
