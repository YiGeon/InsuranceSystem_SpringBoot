package com.InsuranceSystem.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.InsuranceSystem.Development.ContractConditions;
import com.InsuranceSystem.Development.Fire;
import com.InsuranceSystem.Development.Insurance;
import com.InsuranceSystem.Development.Life;
import com.InsuranceSystem.Development.LossProportionality;

@Mapper
public interface DevelopmentMapper {
	
	public int insert_Insurance(Insurance insurance);								// 상태값 추가해야함 alter table insurances add state tinyint(1) default 0;
	public int insert_Contractconditions(ContractConditions contractConditions);
	public int insert_Life(Life life);
	public int insert_Fire(Fire fire);
	public int insert_Loss(LossProportionality lossProportionality);
	
	public Life select_Insurance_insuranceID(@Param("insuranceID") int insuranceID);
	
	public List<Life> select_Life();					// 승인된 보험
	public List<Life> select_disapproval_Life();		// 승인되지 않은 보험
	public Life select_Life_insuranceID(@Param("insuranceID") int insuranceID);
	public int select_disapproval_Life_count();
	public int select_Life_count();

	public List<Fire> select_Fire();					// 승인된 보험
	public List<Fire> select_disapproval_Fire();		// 승인되지 않은 보험
	public Fire select_Fire_insuranceID(@Param("insuranceID") int insuranceID);
	public int select_disapproval_Fire_count();
	public int select_Fire_count();

	public List<LossProportionality> select_Loss();					// 승인된 보험
	public List<LossProportionality> select_disapproval_Loss();		// 승인되지 않은 보험
	public LossProportionality select_Loss_insuranceID(@Param("insuranceID") int insuranceID);
	public int select_disapproval_Loss_count();
	public int select_Loss_count();

	public ContractConditions select_Contractcondition(@Param("name")  String name);
	
	public ContractConditions select_Contractcondition_insuranceID(@Param("id") int id);
	
	public int delete_Insurance(@Param("name") String name);			//보험 삭제
	public List<Life> select_approval_Ins();							//승인된 보험
	public int select_approval_Ins_count();									//승인된 보험 갯수

	public Life select_approval_Ins_Name_by_id(@Param("id") int id);
	
	public int approve_Insurance(@Param("name") String name);		//보험을 승인하게되면 해당 보험의 상태값이 1이 된다. 기본은 0
	
	public List<Insurance> showInsurance();						//이건 위의 보험들을 하나의 리스트에 담아서 구현해야할듯 SQL문으로 표현하기 좀 어렵다..
}
