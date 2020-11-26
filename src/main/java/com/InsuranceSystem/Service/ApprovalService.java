package com.InsuranceSystem.Service;

import com.InsuranceSystem.Development.Fire;
import com.InsuranceSystem.Development.Life;
import com.InsuranceSystem.Development.LossProportionality;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ApprovalService {
    public List<Life> selectLife();
    public List<Fire> selectFire();
    public List<LossProportionality> selectLoss();
    public boolean approveIns(String name);
}
