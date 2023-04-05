package com.project.controller;

import com.project.model.InsurancePolicy;
import com.project.repository.InsurancePolicyRepo;
import com.project.service.InsurancePolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class InsurancePolicyController {
    @Autowired
    private InsurancePolicyRepo repo;

    @Autowired
    private InsurancePolicyService service;

    @PostMapping("/createInsurancePolicy")
    public ResponseEntity<List<InsurancePolicy>> createInsurancePolicy(@RequestBody InsurancePolicy insurancePolicy){
        InsurancePolicy iPolicy=new InsurancePolicy();
        System.out.println(insurancePolicy.getPolicyNumber());
        try{
            iPolicy.setPolicyType(insurancePolicy.getPolicyType());
            iPolicy.setCoverageAmount(insurancePolicy.getCoverageAmount());
            iPolicy.setPremiumAmount(insurancePolicy.getPremiumAmount());
            iPolicy.setStartDate(insurancePolicy.getStartDate());
            iPolicy.setEndDate(insurancePolicy.getEndDate());
            repo.save(insurancePolicy);
            System.out.println("creating insurancePolicy is Done");
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.CREATED);

    }
    @GetMapping("/getAllInsurancePolicies")
    public ResponseEntity<List<InsurancePolicy>> getAllInsurancePolicies(){
        List<InsurancePolicy> insurancePolicyList=service.getAll();
        if(insurancePolicyList.size()==0){
            System.out.println("no data found");
        }else{
            return new ResponseEntity<>(insurancePolicyList,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/getPolicyByNumber/{policyNumber}")
    public ResponseEntity<InsurancePolicy> getPolicyByNumber(@PathVariable Long policyNumber){
       try{
           Optional<InsurancePolicy> insurancePolicyOptional=repo.findById(policyNumber);
           if(insurancePolicyOptional.isPresent()){
               return new ResponseEntity<>(insurancePolicyOptional.get(),HttpStatus.OK);
           }
       }catch (Exception e){
           e.printStackTrace();
       }
       return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/updatePolicyByNumber/{policyNumber}")
    public ResponseEntity<InsurancePolicy> updatePolicyByNumber(@PathVariable Long policyNumber,@RequestBody InsurancePolicy newpolicy){
    Optional<InsurancePolicy> oldPolicy=repo.findById(policyNumber);
    try{
        if(oldPolicy.isPresent()){
            InsurancePolicy updatedPolicy=oldPolicy.get();
            updatedPolicy.setPolicyType(newpolicy.getPolicyType());
            updatedPolicy.setCoverageAmount(newpolicy.getCoverageAmount());
            updatedPolicy.setPremiumAmount(newpolicy.getPremiumAmount());
            updatedPolicy.setStartDate(newpolicy.getStartDate());
            updatedPolicy.setEndDate(newpolicy.getEndDate());
            InsurancePolicy policyObj=repo.save(updatedPolicy);
            return new ResponseEntity<>(policyObj,HttpStatus.OK);
        }
    }catch (Exception e){
        e.printStackTrace();
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/deletePolicyByNumber/{policyNumber}")
    public ResponseEntity<InsurancePolicy> deletePolicyByNumber(@PathVariable Long policyNumber){
        try{
            repo.deleteById(policyNumber);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
