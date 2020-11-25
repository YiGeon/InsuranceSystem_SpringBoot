package com.InsuranceSystem.Service;

import com.InsuranceSystem.Development.Fire;
import com.InsuranceSystem.Development.Life;
import com.InsuranceSystem.Development.LossProportionality;
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
        int queryResult = 0;

        params.getContractConditions().setInsuranceName(params.getInsuranceName());
        queryResult = developmentMapper.insert_Insurance(params);

        queryResult = queryResult + developmentMapper.insert_Contractconditions(params.getContractConditions());
        queryResult = queryResult +developmentMapper.insert_Life(params);

        return (queryResult == 3) ? true : false;
    }

    @Override
    public boolean registerFire(Fire params) {
        int queryResult = 0;
        params.getContractConditions().setInsuranceName(params.getInsuranceName());
        queryResult = developmentMapper.insert_Insurance(params);

        queryResult = queryResult + developmentMapper.insert_Contractconditions(params.getContractConditions());
        queryResult = queryResult + developmentMapper.insert_Fire(params);

        return (queryResult == 3) ? true : false;
    }

    @Override
    public boolean registerLoss(LossProportionality params) {
        int queryResult = 0;
        params.getContractConditions().setInsuranceName(params.getInsuranceName());
        queryResult = developmentMapper.insert_Insurance(params);

        queryResult = queryResult + developmentMapper.insert_Contractconditions(params.getContractConditions());
        queryResult = queryResult +developmentMapper.insert_Loss(params);

        return (queryResult == 3) ? true : false;
    }
}
