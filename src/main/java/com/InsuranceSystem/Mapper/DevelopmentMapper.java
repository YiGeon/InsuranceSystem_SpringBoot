package com.InsuranceSystem.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.InsuranceSystem.Development.ContractConditions;
import com.InsuranceSystem.Development.Fire;
import com.InsuranceSystem.Development.Insurance;
import com.InsuranceSystem.Development.Life;
import com.InsuranceSystem.Development.LossProportionality;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface DevelopmentMapper {
	
	public int insert_Insurance(Insurance insurance);								// 상태값 추가해야함 alter table insurances add state tinyint(1) default 0;
	public int insert_Contractconditions(ContractConditions contractConditions);
	public int insert_Life(Life life);
	public int insert_Fire(Fire fire);
	public int insert_Loss(LossProportionality lossProportionality);
	
	public List<Life> select_Life();
	public List<Life> select_disapproval_Life();
	public int select_disapproval_Life_count();

	public List<Fire> select_Fire();
	public List<Fire> select_disapproval_Fire();
	public int select_disapproval_Fire_count();

	public List<LossProportionality> select_Loss();
	public List<LossProportionality> select_disapproval_Loss();
	public int select_disapproval_Loss_count();

	public ContractConditions select_Contractcondition(@Param("name")  String name);
	
	public boolean delete_Insurance(int insuranceID);			//보험 삭제
	
	public int approve_Insurance(@Param("name") String name);		//보험을 승인하게되면 해당 보험의 상태값이 1이 된다. 기본은 0
	
	public List<Insurance> showInsurance();						//이건 위의 보험들을 하나의 리스트에 담아서 구현해야할듯 SQL문으로 표현하기 좀 어렵다..
}
