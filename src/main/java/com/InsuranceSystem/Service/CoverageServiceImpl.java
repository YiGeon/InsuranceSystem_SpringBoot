package com.InsuranceSystem.Service;

import com.InsuranceSystem.Customer.Customer;
import com.InsuranceSystem.Mapper.CustomerMapper;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CoverageServiceImpl implements CoverageService {
    private final CustomerMapper customerMapper;

    public CoverageServiceImpl(CustomerMapper customerMapper) {
        this.customerMapper = customerMapper;
    }

    @Override
    public List<Customer> selectCus() {
        List<Customer> customerList = customerMapper.select_ApprovedCustomer();
        if (customerList.size() == 0) {
            return Collections.emptyList();
        }
        return customerList;
    }

    @Override
    public List<Customer> selectCusByName(String name) {
        List<Customer> customerList = customerMapper.select_ApprovedCustomer_by_name(name);
        if (customerList.size() == 0) {
            return Collections.emptyList();
        }
        return customerList;
    }
}
