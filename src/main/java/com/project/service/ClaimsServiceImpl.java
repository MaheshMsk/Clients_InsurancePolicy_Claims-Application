package com.project.service;

import com.project.model.Claims;
import com.project.repository.ClaimsRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ClaimsServiceImpl implements ClaimsService{

    @Autowired
    private ClaimsRepo repo;
    @Override
    public List<Claims> getAll() {
        return null;
    }
}
