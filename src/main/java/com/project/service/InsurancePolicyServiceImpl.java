package com.project.service;

import com.project.model.InsurancePolicy;
import com.project.repository.InsurancePolicyRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class InsurancePolicyServiceImpl implements InsurancePolicyService{

    @Autowired
    private InsurancePolicyRepo repo;
    @Override
    public List<InsurancePolicy> getAll() {
        return null;
    }
}
