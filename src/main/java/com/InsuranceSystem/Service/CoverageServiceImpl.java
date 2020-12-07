package com.InsuranceSystem.Service;

import com.InsuranceSystem.Coverage.Accident;
import com.InsuranceSystem.Coverage.AccidentCause;
import com.InsuranceSystem.Coverage.DamageAssessed;
import com.InsuranceSystem.Customer.Customer;
import com.InsuranceSystem.Development.Fire;
import com.InsuranceSystem.Development.Insurance;
import com.InsuranceSystem.Development.Life;
import com.InsuranceSystem.Development.LossProportionality;
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
    public LossProportionality selectInsByID(int id) {
        return developmentMapper.select_approval_Ins_Name_by_id(id);
    }

    @Override
    public Life selectLifeByID(int id) {
        return developmentMapper.select_Life_insuranceID(id);
    }

    @Override
    public Fire selectFireByID(int id) {
        return developmentMapper.select_Fire_insuranceID(id);
    }

    @Override
    public LossProportionality selectLossByID(int id) {
        return developmentMapper.select_Loss_insuranceID(id);
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
    public List<Accident> selectAllAssessedAccident() {
        List<Accident> accidentList = accidentMapper.select_AssessedAccident();
        if (accidentList.size() == 0) {
            return Collections.emptyList();
        }
        return accidentList;
    }

    @Override
    public int calReceivedAmount(Accident accident, LossProportionality insurance, Customer customer) {
        if (insurance.getInsuranceType().equals(Insurance.insuranceType.Life)) {
            return insurance.getGuaranteeAmount();
        } else if (insurance.getInsuranceType().equals(Insurance.insuranceType.Fire)) {
            return insurance.getGuaranteeAmount();
        } else if (insurance.getInsuranceType().equals(Insurance.insuranceType.Lossproportionality)) {
            return accident.getYieldAmount() * insurance.getLimitRate() / 100;
        }
        return 0;
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
