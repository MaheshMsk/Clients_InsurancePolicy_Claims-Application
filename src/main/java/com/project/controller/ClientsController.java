package com.project.controller;

import com.project.model.Clients;
import com.project.repository.ClientsRepo;
import com.project.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ClientsController {

    @Autowired
    private ClientService service;

    @Autowired
    private ClientsRepo repo;
    @PostMapping("/createClients")
    public ResponseEntity<List<Clients>> createClients(@RequestBody Clients clients){
        Clients client=new Clients();
        System.out.println(client.getName());
        try{
            client.setName(clients.getName());
            client.setAddress(clients.getAddress());
            client.setContact(clients.getContact());
            client.setDateOfBirth(clients.getDateOfBirth());
            repo.save(clients);
            System.out.println("Creation of Clients Successfully done");
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping("/getAllClients")
    public ResponseEntity<List<Clients>> getAllClients(){
        List<Clients> clients=service.getAll();
        if(clients.size()==0){
            System.out.println("Data Not Found");
        }else{
            return new ResponseEntity<>(clients,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/getClientById/{id}")
    public ResponseEntity<Clients> getClientById(@PathVariable Long id){
        try{
            Optional<Clients> clientData=repo.findById(id);
            if(clientData.isPresent()){
                return new ResponseEntity<>(clientData.get(),HttpStatus.OK);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PutMapping("/updateClientById/{id}")
    public ResponseEntity<Clients> updateClientById(@PathVariable Long id,@RequestBody Clients newClient){
        Optional<Clients> oldClient=repo.findById(id);
        try{
            if(oldClient.isPresent()){
                Clients updatedData=oldClient.get();
                updatedData.setName(newClient.getName());
                updatedData.setContact(newClient.getContact());
                updatedData.setAddress(newClient.getAddress());
                updatedData.setDateOfBirth(newClient.getDateOfBirth());
                Clients clientsObj=repo.save(updatedData);
                return new ResponseEntity<>(clientsObj,HttpStatus.OK);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/deleteClientById/{id}")
    public ResponseEntity<Clients> deleteClientById(@PathVariable Long id){
        try{
            repo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
