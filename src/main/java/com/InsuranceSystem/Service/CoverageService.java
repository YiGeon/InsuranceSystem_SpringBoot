package com.InsuranceSystem.Service;

import com.InsuranceSystem.Coverage.Accident;
import com.InsuranceSystem.Coverage.DamageAssessed;
import com.InsuranceSystem.Customer.Customer;
import com.InsuranceSystem.Development.Fire;
import com.InsuranceSystem.Development.Insurance;
import com.InsuranceSystem.Development.Life;
import com.InsuranceSystem.Development.LossProportionality;
import com.InsuranceSystem.Sale.AssociationCus;

import java.util.List;

public interface CoverageService {
    public List<Customer> selectCus();
    public List<Customer> selectCusByName(String name);

    public List<AssociationCus> selectSubInfoByID(int id);

    public Customer selectCustomerByID(int id);

    public LossProportionality selectInsByID(int id);

    public Life selectLifeByID(int id);
    public Fire selectFireByID(int id);
    public LossProportionality selectLossByID(int id);

    public boolean submitReceipt(Accident accident);

    public List<Accident> selectAllAccident();

    public List<Accident> selectAllAssessedAccident();

    public int calReceivedAmount(Accident accident, LossProportionality insurance, Customer customer);

    public boolean update_Accident(int id);

    public boolean delete_Accident(int id);
}
