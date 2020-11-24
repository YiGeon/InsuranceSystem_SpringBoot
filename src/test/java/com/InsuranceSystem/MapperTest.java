package com.InsuranceSystem;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.util.CollectionUtils;

import com.InsuranceSystem.Development.ContractConditions;
import com.InsuranceSystem.Development.ContractConditions.payCycle;
import com.InsuranceSystem.Development.Fire;
import com.InsuranceSystem.Development.Insurance;
import com.InsuranceSystem.Development.Insurance.insuranceType;
import com.InsuranceSystem.Development.Life;
import com.InsuranceSystem.Development.LossProportionality;
import com.InsuranceSystem.Mapper.DevelopmentMapper;

@SpringBootTest
public class MapperTest {

	@Autowired
	private DevelopmentMapper developmentMapper;

	@Test
	public void testInsert() {
		Insurance insurance = new Fire();
		ContractConditions contractConditions = new ContractConditions();
		insurance.setInsuranceName("테스트용 보험");
		insurance.setInsuranceType(insuranceType.Fire);
		contractConditions.setInsuranceName("테스트용 보험");
		contractConditions.setPayCycle(payCycle.month);
		insurance.setContractConditions(contractConditions);

		boolean tmp = developmentMapper.insert_Insurance(insurance);
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
