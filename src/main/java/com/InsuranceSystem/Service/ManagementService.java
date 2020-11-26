package com.InsuranceSystem.Service;

import com.InsuranceSystem.Development.Insurance;
import com.InsuranceSystem.Development.Life;

import java.util.List;

public interface ManagementService {
    public List<Life> selectIns();
    public boolean deleteIns(String name);
}
