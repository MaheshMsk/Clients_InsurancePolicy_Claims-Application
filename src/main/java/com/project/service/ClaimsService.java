package com.project.service;

import com.project.model.Claims;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ClaimsService {
    public List<Claims>getAll();
}
