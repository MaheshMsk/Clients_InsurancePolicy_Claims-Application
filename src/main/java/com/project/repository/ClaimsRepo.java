package com.project.repository;

import com.project.model.Claims;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClaimsRepo extends JpaRepository<Claims,Long> {
}
