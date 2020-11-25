package com.InsuranceSystem.Service;

import com.InsuranceSystem.Development.Fire;
import com.InsuranceSystem.Development.Life;
import com.InsuranceSystem.Development.LossProportionality;

public interface DevelopService {

    public boolean registerLife(Life params);
    public boolean registerFire(Fire params);
    public boolean registerLoss(LossProportionality params);
}
