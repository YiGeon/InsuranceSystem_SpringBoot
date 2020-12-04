package com.InsuranceSystem.Service;

import com.InsuranceSystem.Coverage.Accident;
import com.InsuranceSystem.Coverage.DamageAssessed;
import com.InsuranceSystem.Customer.Customer;
import com.InsuranceSystem.Development.Life;
import com.InsuranceSystem.Mapper.AccidentMapper;
import com.InsuranceSystem.Mapper.CustomerMapper;
import com.InsuranceSystem.Mapper.DevelopmentMapper;
import com.InsuranceSystem.Mapper.SubInfoMapper;
import com.InsuranceSystem.Sale.AssociationCus;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CoverageServiceImpl implements CoverageService {
    private final CustomerMapper customerMapper;
    private final SubInfoMapper subInfoMapper;
    private final DevelopmentMapper developmentMapper;
    private final AccidentMapper accidentMapper;

    public CoverageServiceImpl(CustomerMapper customerMapper, SubInfoMapper subInfoMapper, DevelopmentMapper developmentMapper, AccidentMapper accidentMapper) {
        this.customerMapper = customerMapper;
        this.subInfoMapper = subInfoMapper;
        this.developmentMapper = developmentMapper;
        this.accidentMapper = accidentMapper;
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

    @Override
    public List<AssociationCus> selectSubInfoByID(int id) {
        List<AssociationCus> subInfoList = subInfoMapper.select_subInfo_by_id(id);
        if (subInfoList.size() == 0) {
            return Collections.emptyList();
        }
        return subInfoList;
    }

    @Override
    public Customer selectCustomerByID(int id) {
        return customerMapper.select_ApprovedCustomer_by_id(id);
    }

    @Override
    public Life selectInsByID(int id) {
        return developmentMapper.select_approval_Ins_Name_by_id(id);
    }

    @Override
    public boolean submitReceipt(Accident accident) {
        int queryResult = 0;

        queryResult = accidentMapper.insert_Accident(accident);
        return (queryResult == 1) ? true : false;
    }

    @Override
    public List<Accident> selectAllAccident() {
        List<Accident> accidentList = accidentMapper.select_Accident();
        if (accidentList.size() == 0) {
            return Collections.emptyList();
        }
        return accidentList;
    }

    @Override
    public boolean update_Accident(int id) {
        int queryResult = 0;
        queryResult = accidentMapper.update_Accident(id);
        return (queryResult == 1) ? true : false;
    }

    @Override
    public boolean delete_Accident(int id) {
        int queryResult = 0;
        queryResult = accidentMapper.delete_Accident(id);
        return (queryResult == 1) ? true : false;
    }


}
