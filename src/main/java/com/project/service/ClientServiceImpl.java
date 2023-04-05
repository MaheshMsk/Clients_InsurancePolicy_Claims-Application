package com.project.service;

import com.project.model.Clients;
import com.project.repository.ClientsRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ClientServiceImpl implements ClientService{

        @Autowired
    private ClientsRepo repo;

        @Override
    public List<Clients> getAll(){
            return repo.findAll();
        }
}
