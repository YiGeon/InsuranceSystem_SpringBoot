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
        int queryResult = 0;
        queryResult = developmentMapper.insert_Life(params);
        return (queryResult == 1) ? true : false;
    }
}
