package com.project.controller;

import com.project.model.Claims;
import com.project.repository.ClaimsRepo;
import com.project.service.ClaimsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ClaimsController {
    @Autowired
    private ClaimsRepo repo;
    @Autowired
    private ClaimsService service;

    @PostMapping("/createClaims")
    public ResponseEntity<List<Claims>> createClaims(@RequestBody Claims claims){
        Claims claim=new Claims();
        System.out.println(claims.getClaimNumber());
        try{
            claim.setClaimStatus(claims.getClaimStatus());
            claim.setClaimDescription(claims.getClaimDescription());
            claim.setClaimDate(claims.getClaimDate());
            repo.save(claims);
            System.out.println("Claims created");
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping("/getAllClaims")
    public ResponseEntity<List<Claims>> getAllClaims(){
        List<Claims> claimsList=service.getAll();
        if(claimsList.size()==0){
            System.out.println("no data found");
        }else{
            return new ResponseEntity<>(claimsList,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/getClaimsByNumber/{claimNumber}")
    public ResponseEntity<Claims> getClaimsByNumber(@PathVariable Long claimNumber){
        try{
            Optional<Claims> claimsOptional=repo.findById(claimNumber);
            if(claimsOptional.isPresent()){
                return new ResponseEntity<>(claimsOptional.get(),HttpStatus.OK);
            }
        }catch (Exception  e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/updateClaimsByNumber/{claimNumber}")
    public ResponseEntity<Claims> updateClaimsByNumber(@PathVariable Long claimNumber,@RequestBody Claims newClaims){
    Optional<Claims> oldClaim=repo.findById(claimNumber);
    try{
        if(oldClaim.isPresent()){
            Claims updateClaim=oldClaim.get();
            updateClaim.setClaimDescription(newClaims.getClaimDescription());
            updateClaim.setClaimStatus(newClaims.getClaimStatus());
            updateClaim.setClaimDate(newClaims.getClaimDate());
            Claims claimsObj=repo.save(updateClaim);
            return new ResponseEntity<>(claimsObj,HttpStatus.OK);
        }
    }catch (Exception e){
        e.printStackTrace();
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/deleteClaimsByNumber/{claimNumber}")
    public ResponseEntity<Claims> deleteClaimsByNumber(@PathVariable Long claimNumber){
        try{
            repo.deleteById(claimNumber);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
