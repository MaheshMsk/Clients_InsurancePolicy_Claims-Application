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
