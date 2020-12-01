package com.InsuranceSystem.Service;

import com.InsuranceSystem.Customer.Customer;

import java.util.List;

public interface CoverageService {
    public List<Customer> selectCus();
    public List<Customer> selectCusByName(String name);
}
