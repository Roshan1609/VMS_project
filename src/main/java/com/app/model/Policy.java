package com.app.model;


import com.app.enums.PackageName;
import com.app.enums.PolicyStatus;
import com.app.enums.ReviewStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
@Entity
public class Policy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private PolicyStatus policyStatus;

    @Enumerated(EnumType.STRING)
    private PackageName packageName;

    private Double premiumAmount;

    @CreationTimestamp
    private LocalDate startDate;

    @CreationTimestamp
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    private ReviewStatus reviewStatus;

    private String reviewRemarks;

    @ManyToOne
    private Officer officer;

    @ManyToOne
    private Customer customer;

    public Policy() {
    }

    public Policy(PackageName packageName, Double premiumAmount ,ReviewStatus reviewStatus, String reviewRemarks) {
        this.packageName = packageName;
        this.premiumAmount = premiumAmount;
        this.reviewStatus = reviewStatus;
        this.reviewRemarks = reviewRemarks;
    }


    public Officer getOfficer() {
        return officer;
    }

    public void setOfficer(Officer officer) {
        this.officer = officer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    @ManyToOne
    private Vehicle vehicle;

    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Policy{" +
                "id=" + id +
                ", policyStatus=" + policyStatus +
                ", packageName=" + packageName +
                ", premiumAmount=" + premiumAmount +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", reviewStatus=" + reviewStatus +
                ", reviewRemarks='" + reviewRemarks + '\'' +
                '}';
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PolicyStatus getPolicyStatus() {
        return policyStatus;
    }

    public void setPolicyStatus(PolicyStatus policyStatus) {
        this.policyStatus = policyStatus;
    }

    public PackageName getPackageName() {
        return packageName;
    }

    public void setPackageName(PackageName packageName) {
        this.packageName = packageName;
    }

    public Double getPremiumAmount() {
        return premiumAmount;
    }

    public void setPremiumAmount(Double premiumAmount) {
        this.premiumAmount = premiumAmount;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public ReviewStatus getReviewStatus() {
        return reviewStatus;
    }

    public void setReviewStatus(ReviewStatus reviewStatus) {
        this.reviewStatus = reviewStatus;
    }

    public String getReviewRemarks() {
        return reviewRemarks;
    }

    public void setReviewRemarks(String reviewRemarks) {
        this.reviewRemarks = reviewRemarks;
    }
}
