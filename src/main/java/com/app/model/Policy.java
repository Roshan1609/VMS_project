package com.app.model;

import com.app.enums.PackageName;
import com.app.enums.PolicyStatus;
import com.app.enums.ReviewStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.Date;
@Entity
public class Policy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;
    @CreationTimestamp
    private Instant endDate;

    @Enumerated(EnumType.STRING)
    private PackageName packageName;

    @Enumerated(EnumType.STRING)
    private PolicyStatus policyStatus;
    @Column(nullable = false)
    private  double premiumAmount;
    @Column(nullable = false)
    private String reviewRemarks;

    @Enumerated(EnumType.STRING)
    private ReviewStatus reviewStatus;
    @CreationTimestamp
    private Instant startDate;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private Officer officer;

    @ManyToOne
    private Vehicle vehicle;

    public Policy() {
    }

    public Policy(PackageName packageName, double premiumAmount, String reviewRemarks, ReviewStatus reviewStatus) {
        this.packageName = packageName;
        this.premiumAmount = premiumAmount;
        this.reviewRemarks = reviewRemarks;
        this.reviewStatus = reviewStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Instant getEndDate() {
        return endDate;
    }

    public void setEndDate(Instant endDate) {
        this.endDate = endDate;
    }

    public PackageName getPackageName() {
        return packageName;
    }

    public void setPackageName(PackageName packageName) {
        this.packageName = packageName;
    }

    public PolicyStatus getPolicyStatus() {
        return policyStatus;
    }

    public void setPolicyStatus(PolicyStatus policyStatus) {
        this.policyStatus = policyStatus;
    }

    public double getPremiumAmount() {
        return premiumAmount;
    }

    public void setPremiumAmount(double premiumAmount) {
        this.premiumAmount = premiumAmount;
    }

    public String getReviewRemarks() {
        return reviewRemarks;
    }

    public void setReviewRemarks(String reviewRemarks) {
        this.reviewRemarks = reviewRemarks;
    }

    public ReviewStatus getReviewStatus() {
        return reviewStatus;
    }

    public void setReviewStatus(ReviewStatus reviewStatus) {
        this.reviewStatus = reviewStatus;
    }

    public Instant getStartDate() {
        return startDate;
    }

    public void setStartDate(Instant startDate) {
        this.startDate = startDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Officer getOfficer() {
        return officer;
    }

    public void setOfficer(Officer officer) {
        this.officer = officer;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public String toString() {
        return "Policy{" +
                "id=" + id +
                ", endDate=" + endDate +
                ", packageName=" + packageName +
                ", policyStatus=" + policyStatus +
                ", premiumAmount=" + premiumAmount +
                ", reviewRemarks='" + reviewRemarks + '\'' +
                ", reviewStatus=" + reviewStatus +
                ", startDate=" + startDate +
                ", customer=" + customer +
                ", officer=" + officer +
                ", vehicle=" + vehicle +
                '}';
    }
}