package com.project.repository;

import com.project.model.InsurancePolicy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InsurancePolicyRepo extends JpaRepository<InsurancePolicy,Long> {
}
