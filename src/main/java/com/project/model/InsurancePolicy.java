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
