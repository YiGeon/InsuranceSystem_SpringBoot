package com.InsuranceSystem.Service;

import com.InsuranceSystem.Development.ContractConditions;
import com.InsuranceSystem.Development.Fire;
import com.InsuranceSystem.Development.Insurance;
import com.InsuranceSystem.Development.Life;
import com.InsuranceSystem.Development.LossProportionality;
import com.InsuranceSystem.Mapper.DevelopmentMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.boot.devtools.logger.DevToolsLogFactory;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ApprovalServiceImpl implements ApprovalService  {
    private final DevelopmentMapper developmentMapper;

    public ApprovalServiceImpl(DevelopmentMapper developmentMapper) {
        this.developmentMapper = developmentMapper;
    }

    @Override
    public List<Life> selectLife() {
        List<Life> lifeList = Collections.emptyList();

        int lifeTotalCount = developmentMapper.select_disapproval_Life_count();

        if (lifeTotalCount > 0) {
            lifeList = developmentMapper.select_disapproval_Life();
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

        int fireTotalCount = developmentMapper.select_disapproval_Fire_count();

        if (fireTotalCount > 0) {
            fireList = developmentMapper.select_disapproval_Fire();
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

        int lossTotalCount = developmentMapper.select_disapproval_Loss_count();

        if (lossTotalCount > 0) {
            lossList = developmentMapper.select_disapproval_Loss();
            for (int i = 0; i < lossList.size(); i++) {
                ContractConditions cc = developmentMapper.select_Contractcondition(lossList.get(i).getInsuranceName());
                lossList.get(i).setContractConditions(cc);
            }
        }
        return lossList;
    }

    @Override
    public boolean approveIns(String name) {
        int queryResult = 0;
        queryResult = developmentMapper.approve_Insurance(name);
        return (queryResult == 1) ? true : false;
    }


}
