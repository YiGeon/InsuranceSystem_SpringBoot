package com.InsuranceSystem.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.InsuranceSystem.Coverage.Accident;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AccidentMapper {
	public int insert_Accident(Accident accident);		//상태값 추가해야합니다. alter table accidents add state tinyint(1) default 0;
										//원래 사고가 발생했을때 접수한게 accident이고 그 사고를 인수심사해서 승인한게 DamageAssessed인데 그냥 상태값으로 하나로만 해도 될것같네요..
	public int delete_Accident(@Param("id") int id);
	
	public int update_Accident(@Param("id") int id);
	
	public List<Accident> select_Accident();
	
	public List<Accident> select_AssessedAccident();
	
}
