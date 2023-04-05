package com.project.service;

import com.project.model.Clients;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClientService {

    public List<Clients> getAll();
}
