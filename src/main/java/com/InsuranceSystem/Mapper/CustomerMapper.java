package com.InsuranceSystem.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.InsuranceSystem.Customer.Account;
import com.InsuranceSystem.Customer.Customer;

@Mapper
public interface CustomerMapper {
	
	public int insert_Customer(Customer customer);									//이것도 마찬가지로 상태값을 DB에 추가해야한다. 가입 승인이 된 고객은 1로 표시 안된 고객은 0으로 표시함
																					//alter table customers add state tinyint(1) default 0;
	public int insert_Customer_Account(Account account);							//계좌인데 솔직히 그렇게 필요가 있나 생각이 듭니다. 빼도될것같긴한데 귀찮으시면 걍 빼겠습니다.
	
	public List<Customer> select_Customer();										//state = 0;
	
	public List<Customer> select_ApprovedCustomer();								//state = 1;

	public String select_Customer_by_id_residentNo(@Param("name") String name, @Param("residentNo") String residentNo);
	
	public Customer select_ApprovedCustomer_insuranceID(@Param("customerID") int customerID);
	
	public int delete_Customer(Customer customer);
	
	public int update_Customer(Customer customer);
	
	public int approve_Customer(Customer customer);								//고객을 승인하면 1로 표현 승인하지 않은 고객은 0으로 표현
}
