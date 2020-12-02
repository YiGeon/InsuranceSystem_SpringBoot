package com.InsuranceSystem.Service;

import com.InsuranceSystem.Customer.Customer;
import com.InsuranceSystem.Development.Life;
import com.InsuranceSystem.Sale.AssociationCus;

import java.util.List;

public interface UWService {
    public List<AssociationCus> selectSubInfo();

    public Customer selectCusByID(int id);

    public Life selectInsByID(int id);

    public boolean approveCus(int id);
}
