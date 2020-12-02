package com.InsuranceSystem.Service;

import com.InsuranceSystem.Customer.Customer;
import com.InsuranceSystem.Development.ContractConditions;
import com.InsuranceSystem.Development.Fire;
import com.InsuranceSystem.Development.Life;
import com.InsuranceSystem.Development.LossProportionality;
import com.InsuranceSystem.Mapper.CustomerMapper;
import com.InsuranceSystem.Mapper.DevelopmentMapper;
import com.InsuranceSystem.Mapper.SubInfoMapper;
import com.InsuranceSystem.Sale.AssociationCus;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class SaleServiceImpl implements SaleService {
    private final DevelopmentMapper developmentMapper;
    private final CustomerMapper customerMapper;
    private final SubInfoMapper subInfoMapper;

    public SaleServiceImpl(DevelopmentMapper developmentMapper, CustomerMapper customerMapper, SubInfoMapper subInfoMapper) {
        this.developmentMapper = developmentMapper;
        this.customerMapper = customerMapper;
        this.subInfoMapper = subInfoMapper;
    }

    @Override
    public List<Life> selectLife() {
        List<Life> lifeList = Collections.emptyList();

        int lifeTotalCount = developmentMapper.select_Life_count();

        if (lifeTotalCount > 0) {
            lifeList = developmentMapper.select_Life();
            for (int i = 0; i < lifeList.size(); i++) {
                ContractConditions cc = developmentMapper.select_Contractcondition(lifeList.get(i).getInsuranceName());
                lifeList.get(i).setContractConditions(cc);
            }
        }
        return lifeList;
    }

    @Override
    public List<Fire> selectFire() {
        List<Fire> fireList = Collections.emptyList();

        int fireTotalCount = developmentMapper.select_Fire_count();

        if (fireTotalCount > 0) {
            fireList = developmentMapper.select_Fire();
            for (int i = 0; i < fireList.size(); i++) {
                ContractConditions cc = developmentMapper.select_Contractcondition(fireList.get(i).getInsuranceName());
                fireList.get(i).setContractConditions(cc);
            }
        }
        return fireList;
    }

    @Override
    public List<LossProportionality>  selectLoss() {
        List<LossProportionality> lossList =Collections.emptyList();

        int lossTotalCount = developmentMapper.select_Loss_count();

        if (lossTotalCount > 0) {
            lossList = developmentMapper.select_Loss();
            for (int i = 0; i < lossList.size(); i++) {
                ContractConditions cc = developmentMapper.select_Contractcondition(lossList.get(i).getInsuranceName());
                lossList.get(i).setContractConditions(cc);
            }
        }
        return lossList;
    }

    @Override
    public boolean registerCustomer(Customer params) {
        int queryResult = 0;
        queryResult = customerMapper.insert_Customer(params);
        return (queryResult == 1) ? true : false;
    }

    @Override
    public boolean registerIns(AssociationCus associationCus) {
        int queryResult = 0;
        queryResult = subInfoMapper.insert_subInfo(associationCus);
        return (queryResult == 1) ? true : false;
    }

    @Override
    public String selectCustID(String name, String residentNo) {
        String customerID = null;
        customerID = customerMapper.select_Customer_by_name_residentNo(name, residentNo);
        return customerID;
    }
}
