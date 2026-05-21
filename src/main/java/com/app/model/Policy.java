package com.app.model;

import com.app.enums.PackageName;
import com.app.enums.PolicyStatus;
import com.app.enums.ReviewStatus;

import java.time.LocalDate;
import java.util.Date;

public class Policy {
    int id;
    LocalDate endDate;
    PackageName packageName;
    PolicyStatus policyStatus;
    Double premiumAmount;
    String reviewRemarks;
    ReviewStatus reviewStatus;
    LocalDate startDate;
    int customer_id;
    int officer_id;
    int vehicle_id;

    public Policy() {
    }
    public Policy(LocalDate startDate,
                  LocalDate endDate,
                  PackageName packageName,
                  PolicyStatus policyStatus,
                  double premiumAmount,
                  String reviewRemarks,
                  ReviewStatus reviewStatus,
                  int customer_id,
                  int officer_id,
                  int vehicle_id) {

        this.startDate = startDate;
        this.endDate = endDate;
        this.packageName = packageName;
        this.policyStatus = policyStatus;
        this.premiumAmount = premiumAmount;
        this.reviewRemarks = reviewRemarks;
        this.reviewStatus = reviewStatus;
        this.customer_id = customer_id;
        this.officer_id = officer_id;
        this.vehicle_id = vehicle_id;
    }
    public Policy(int id,LocalDate startDate,
                  LocalDate endDate,
                  PackageName packageName,
                  PolicyStatus policyStatus,
                  double premiumAmount,
                  String reviewRemarks,
                  ReviewStatus reviewStatus,
                  int customer_id,
                  int officer_id,
                  int vehicle_id) {

        this.id=id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.packageName = packageName;
        this.policyStatus = policyStatus;
        this.premiumAmount = premiumAmount;
        this.reviewRemarks = reviewRemarks;
        this.reviewStatus = reviewStatus;
        this.customer_id = customer_id;
        this.officer_id = officer_id;
        this.vehicle_id = vehicle_id;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
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

    public Double getPremiumAmount() {
        return premiumAmount;
    }

    public void setPremiumAmount(Double premiumAmount) {
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getOfficer_id() {
        return officer_id;
    }

    public void setOfficer_id(int officer_id) {
        this.officer_id = officer_id;
    }

    public int getVehicle_id() {
        return vehicle_id;
    }

    public void setVehicle_id(int vehicle_id) {
        this.vehicle_id = vehicle_id;
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
                ", customer_id=" + customer_id +
                ", officer_id=" + officer_id +
                ", vehicle_id=" + vehicle_id +
                '}';
    }
}
