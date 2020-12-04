package com.InsuranceSystem.Service;

import java.util.List;

import com.InsuranceSystem.Development.Fire;
import com.InsuranceSystem.Development.Insurance;
import com.InsuranceSystem.Development.Life;
import com.InsuranceSystem.Development.LossProportionality;

public interface ApprovalService {
    public List<Life> selectLife();
    public List<Fire> selectFire();
    public List<LossProportionality> selectLoss();
    public boolean approveIns(String name);
   }
