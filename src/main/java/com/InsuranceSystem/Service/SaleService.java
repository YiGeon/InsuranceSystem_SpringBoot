package com.InsuranceSystem.Service;

import com.InsuranceSystem.Customer.Customer;
import com.InsuranceSystem.Development.ContractConditions;
import com.InsuranceSystem.Development.Fire;
import com.InsuranceSystem.Development.Life;
import com.InsuranceSystem.Development.LossProportionality;
import com.InsuranceSystem.Sale.AssociationCus;

import java.util.List;

public interface SaleService {

    public List<Life> selectLife();
    public List<Fire> selectFire();
    public List<LossProportionality> selectLoss();
    public boolean registerCustomer(Customer params);
    public boolean registerIns(AssociationCus associationCus);

    public String selectCustID(String name, String residentNo);
	public boolean updateDate(Customer customer);
	public ContractConditions selectContractConditions(int id);
	
	public Life select_Life_insuranceID(int insuranceID);
	public Fire select_Fire_insuranceID(int insuranceID);
	public LossProportionality select_Loss_insuranceID(int insuranceID);

}
