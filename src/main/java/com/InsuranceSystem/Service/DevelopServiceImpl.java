package com.InsuranceSystem.Service;

import com.InsuranceSystem.Development.Life;
import com.InsuranceSystem.Mapper.DevelopmentMapper;
import org.springframework.stereotype.Service;

@Service
public class DevelopServiceImpl implements DevelopService {

    private final DevelopmentMapper developmentMapper;

    public DevelopServiceImpl(DevelopmentMapper developmentMapper) {
        this.developmentMapper = developmentMapper;
    }

    @Override
    public boolean registerLife(Life params) {
        int queryResult1 = 0;
        int queryResult2 = 0;
        int queryResult3 = 0;
        params.getContractConditions().setInsuranceName(params.getInsuranceName());
        queryResult1 = developmentMapper.insert_Insurance(params);

        queryResult2 = developmentMapper.insert_Contractconditions(params.getContractConditions());
        queryResult3 = developmentMapper.insert_Life(params);

        return (queryResult1 == 1) ? true : false;
    }
}
