package com.InsuranceSystem.Service;

import com.InsuranceSystem.Customer.Customer;
import com.InsuranceSystem.Development.Life;
import com.InsuranceSystem.Mapper.CustomerMapper;
import com.InsuranceSystem.Mapper.SubInfoMapper;
import com.InsuranceSystem.Sale.AssociationCus;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UWServiceImpl implements UWService {
    private final SubInfoMapper subInfoMapper;
    private final CustomerMapper customerMapper;

    public UWServiceImpl(SubInfoMapper subInfoMapper, CustomerMapper customerMapper) {
        this.subInfoMapper = subInfoMapper;
        this.customerMapper = customerMapper;
    }


    @Override
    public List<AssociationCus> selectSubInfo() {
        List<AssociationCus> subInfoList = Collections.emptyList();

        int infoTotalCount = subInfoMapper.select_count_disapproval_SubInfo();

        if (infoTotalCount > 0) {
            subInfoList = subInfoMapper.select_disapproval_SubInfo();

        }
        return subInfoList;
    }

    @Override
    public Customer selectCusByID(int id) {
        return subInfoMapper.select_Customer_by_id(id);
    }

    @Override
    public Life selectInsByID(int id) {
        return subInfoMapper.select_Ins_by_id(id);
    }

    @Override
    public boolean approveCus(int id) {
        int queryResult = 0;
        queryResult = customerMapper.approve_Customer(id);
        return (queryResult == 1) ? true : false;
    }


}
