package com.project.service;

import com.project.model.InsurancePolicy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface InsurancePolicyService {
    public List<InsurancePolicy> getAll();
}
