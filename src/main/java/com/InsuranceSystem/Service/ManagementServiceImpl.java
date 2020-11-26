package com.InsuranceSystem.Service;

import com.InsuranceSystem.Development.ContractConditions;
import com.InsuranceSystem.Development.Fire;
import com.InsuranceSystem.Development.Insurance;
import com.InsuranceSystem.Development.Life;
import com.InsuranceSystem.Mapper.DevelopmentMapper;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ManagementServiceImpl implements ManagementService {
    private final DevelopmentMapper developmentMapper;

    public ManagementServiceImpl(DevelopmentMapper developmentMapper) {
        this.developmentMapper = developmentMapper;
    }

    @Override
    public List<Life> selectIns() {
        List<Life> insuranceList = Collections.emptyList();

        int insTotalCount = developmentMapper.select_approval_Ins_count();

        if (insTotalCount > 0) {
            insuranceList = developmentMapper.select_approval_Ins();
            for (int i = 0; i < insuranceList.size(); i++) {
                ContractConditions cc = developmentMapper.select_Contractcondition(insuranceList.get(i).getInsuranceName());
                insuranceList.get(i).setContractConditions(cc);
            }
        }
        return insuranceList;
    }

    @Override
    public boolean deleteIns(String name) {
        int queryResult = 0;
        queryResult = developmentMapper.delete_Insurance(name);


        return (queryResult == 1) ? true : false;
    }


}
