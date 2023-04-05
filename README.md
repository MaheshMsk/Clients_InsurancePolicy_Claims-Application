
InsureMyTeamClients
-

A brief description of what this project does and who it's for
creatting clients for a firm which deals with  creating InsurancePolicies .
Provides chances to modify clients details as well as policies and claims of policies .


In this project the code will deploy how it work and how all process done on back-end side(server side).

Prerequisites
-
1.Java

2.Spring Boot

3.Maven

4.H2 Database

Tools
-
1.Eclipse or IntelliJ IDEA (or any preferred IDE) with embedded Maven

2.Maven (version >= 3.6.0)

3.Postman (or any RESTful API testing tool)

Build and Run application
-
Goto  src/main/java/com.project.Client/ClientApplication  and click on run button where we can see the project running on console that shows Tomcat started on port(s): 8080 (http) with context path ''








## Code Snippets
1.Maven Dependencies
-
Need to add below dependencies to enable H2 DB related config in pom.xml.

<dependency>
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
<dependency>
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-starter-web</artifactId>
</dependency>
<dependency>
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-devtools</artifactId>
<scope>runtime</scope>
<optional>true</optional>
</dependency>
<dependency>
<groupId>com.h2database</groupId>
<artifactId>h2</artifactId>
<scope>runtime</scope>
</dependency>

2.Properties file
-
Reading H2 DB related properties from application.properties file and configuring JPA connection factory for H2 database.

src/main/resources/application.properties

spring.datasource.url=jdbc:h2:mem:msk

spring.datasource.driverClassName=org.h2.Driver

spring.datasource.username=sa

spring.datasource.password=

spring.h2.console.enabled=true

spring.jpa.database-platform=org.hibernate.dialect.H2Dialect

3.Model class
-
Below are the model classes which we will store in H2 DB and perform CRUD operations.

**Clients.java**

package com.project.model;

import jakarta.persistence.*;

import java.util.List;

@Entity

@Table(name="clients")

public class Clients {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String address;
    private String contact;
    private String dateOfBirth;

    @OneToMany(mappedBy = "clients",fetch = FetchType.LAZY)
    private InsurancePolicy policy;

    public Clients() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return "Clients{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", contact='" + contact + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                '}';
    }
}


**Claims.java**

package com.project.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity

@Table(name="claims")

public class Claims {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long claimNumber;
    private String claimDescription;
    private Date claimDate;
    private String claimStatus;

    @OneToMany(mappedBy = "claims",fetch = FetchType.LAZY)
    private InsurancePolicy policy;

    public Claims() {
    }

    public Long getClaimNumber() {
        return claimNumber;
    }

    public void setClaimNumber(Long claimNumber) {
        this.claimNumber = claimNumber;
    }

    public String getClaimDescription() {
        return claimDescription;
    }

    public void setClaimDescription(String claimDescription) {
        this.claimDescription = claimDescription;
    }

    public Date getClaimDate() {
        return claimDate;
    }

    public void setClaimDate(Date claimDate) {
        this.claimDate = claimDate;
    }

    public String getClaimStatus() {
        return claimStatus;
    }

    public void setClaimStatus(String claimStatus) {
        this.claimStatus = claimStatus;
    }

    @Override
    public String toString() {
        return "Claims{" +
                "claimNumber=" + claimNumber +
                ", claimDescription='" + claimDescription + '\'' +
                ", claimDate=" + claimDate +
                ", claimStatus='" + claimStatus + '\'' +
                '}';
    }
}

**InsurancePolicy.java**

package com.project.model;

import jakarta.persistence.*;

import java.util.Date;

import java.util.List;

@Entity

@Table(name="insurancePolicy")

public class InsurancePolicy {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long policyNumber;
    private String policyType;
    private double coverageAmount;
    private double premiumAmount;
    private Date startDate;
    private Date endDate;
    @OneToMany(targetEntity = Clients.class,cascade = CascadeType.ALL)
    @JoinTable(name="clients",
                    joinColumns = {@JoinColumn(name="id",referencedColumnName = "id"),
                    },
        inverseJoinColumns = {
            @JoinColumn(name="id",referencedColumnName = "id")
        })
    private List<Clients> clientsList;

    @OneToMany(targetEntity = Claims.class,cascade = CascadeType.ALL)
    @JoinTable(name="claims",
    joinColumns = {@JoinColumn(name="claimNumber",referencedColumnName = "claimNumber"),
    },
    inverseJoinColumns = {@JoinColumn(name="claimNumber",referencedColumnName = "claimNumber")
    }
    )
    private List<Claims> claims;

    public InsurancePolicy() {
    }

    public Long getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(Long policyNumber) {
        this.policyNumber = policyNumber;
    }

    public String getPolicyType() {
        return policyType;
    }

    public void setPolicyType(String policyType) {
        this.policyType = policyType;
    }

    public double getCoverageAmount() {
        return coverageAmount;
    }

    public void setCoverageAmount(double coverageAmount) {
        this.coverageAmount = coverageAmount;
    }

    public double getPremiumAmount() {
        return premiumAmount;
    }

    public void setPremiumAmount(double premiumAmount) {
        this.premiumAmount = premiumAmount;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "InsurancePolicy{" +
                "policyNumber=" + policyNumber +
                ", policyType='" + policyType + '\'' +
                ", coverageAmount=" + coverageAmount +
                ", premiumAmount=" + premiumAmount +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}

4.CRUD operation for Clients
-
In Clients.java class, we have exposed 5 endpoints for basic CRUD operations

* GET All Clients
* GET by ID
* POST to store Clients in DB
* PUT to update Clients Hero
* DELETE by ID

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

In ClientServiceImpl.java, we are autowiring above interface using @Autowired annotation and doing CRUD operation.

In ClaimsController.java class, we have exposed 5 endpoints for basic CRUD operations with spring reactive feature

* GET All Claims
* GET by ID
* POST to store Claims in DB
* PUT to update Claims
* DELETE by ID

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

In ClientsRepo.java, we are extending JpaRepository<Class, ID> interface which enables CRUD related methods.

public interface ClientsRepo extends JpaRepository<Clients,Long> {

}


In InsurancePolicyController.java class, we have exposed 5 endpoints for basic CRUD operations

* GET All InsurancePolicies
* GET by ID
* POST to store InsurancePolicies in DB
* PUT to update InsurancePolicies
* DELETE by ID

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




## API Ports
After successfull Complpetion  running of project open PostMan app and search for the output as follows:
* Open PostMan Application and as follows:

1.To create Clients
-
* Select POST in new connection option and in url section write url of connection as http://localhost:8080/api/ceateClients 

* After that click on body option below the url path section select option raw and make input format as JSON
and enter the fields that your are trying to store in the databassse

* JSON input as {
    "name": "msk",
    "contact": "123456789",
    "address": "hyd",
    "dateOfBirth": "10/10/1997"
}

(NOTE: here in above example the fields are given as in my project as follows make sure that what are the fields that your are mentioning in the project of your give exactly without any error ,if is there any mistake it shows error like {
    "timestamp": "2023-04-05T06:18:10.476+00:00",
    "status": 404,
    "error": "Not Found",
    "message": "No message available",
    "path": "/api/ceateClients"
})


2.Get All Clients
-
* After successfully adding Clients into database we need to fetch all the clients at a time so we have already written method for it with the help of url we can fetch all the Clients details ,the url as follows http://localhost:8080/api/getAllClients before this we need change POST method into GET

* And the output is as follows
{
    "id":"1",
    "name": "msk",
    "contact": "123456789",
    "address": "hyd",
    "dateOfBirth": "10/10/1997"
}


{
    "id":"2",
    "name": "abc",
    "contact": "0123456789",
    "address": "kmm",
    "dateOfBirth": "10/10/1999"
}

{
    "id":"3",
    "name": "xyz",
    "contact": "1234567890",
    "address": "tndr",
    "dateOfBirth": "10/10/2020"
}

3.Get Client By ClientID
-
* For this also we wrote a method in our controller class and the url of this method as follows http://localhost:8080/api/getClientById/2 and output as follows it will return the cleint with ClientID '2'
{
    "id":"2",
    "name": "abc",
    "contact": "0123456789",
    "address": "kmm",
    "dateOfBirth": "10/10/1999"
}

4.Update Client By ClientID
-
* For this also url follows as http://localhost:8080/api/updateClientById/2  for this we need to change GET option inot PUT option for updating a value ,and if we want change all fields except id and it will like as follows
from this to :-
{
    "id":"1",
    "name": "msk",
    "contact": "123456789",
    "address": "hyd",
    "dateOfBirth": "10/10/1997"
}

to this :-
{
    "id":"1",
    "name": "def",
    "contact": "456789123",
    "address": "hyd",
    "dateOfBirth": "10/10/1995"
}

5.Delete Client by ClientID
-
* We dont need a client in our database we can remove them by using delete option we provided in our project that as follows http://localhost:8080/api/deleteClientById/2 here also we need change  PUT option into DELETE option 

* After deleting of a client tha database looks like this 
{
    "id":"1",
    "name": "msk",
    "contact": "123456789",
    "address": "hyd",
    "dateOfBirth": "10/10/1997"
}

{
    "id":"3",
    "name": "xyz",
    "contact": "1234567890",
    "address": "tndr",
    "dateOfBirth": "10/10/2020"
}

(NOTE : Here we deleted client with id '2' and the client number of that clients(i.e.,2) will not assign to any other new or old client, because Client is primary key and it can't be assign to mmore than one field if it happens the whole databse will collapse)

* As of the clients what we done same process will take place in the creation ,fecthing,updating,and deleting a InsurancePolicy,and Claimming that policy 

*The only difference is the port numbers and method that we are creating in the controller class of respective methods 

And the url are as follows
-
For InsurancePolicy
-
* creating :POST:  http://localhost:8080/api/createInsurancePolicy
* Geting all the policies : GET : http://localhost:8080/api/getAllInsurancePolicies
* Geting policy by id : GET : http://localhost:8080/api/getPolicyByNumber/2
* update policy by id : PUT : http://localhost:8080/api/updatePolicyByNumber/2
* delete policy by id : DELETE : http://localhost:8080/api/deletePolicyByNumber/2

For claims
-
* creating : POST : http://localhost:8080/api/createClaims
* Getting all claims : GET : http://localhost:8080/api/getAllClaims
* Getting claim bby id : GET : http://localhost:8080/api/getClaimsByNumber/1
* updating claim by id : PUT : http://localhost:8080/api/updateClaimsByNumber/2
* delete claim by id : DELETE : http://localhost:8080/api/deleteClaimsByNumber/2


(NOTE : Check the all the url's that working properly if any error  or output is not showing clarify that error and check in database console here iam using h2-databse and i have checked it on http://localhost:8080/h2-console for action done in POSTMAN application)