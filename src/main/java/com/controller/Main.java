package com.controller;

import com.config.HibernateConfig;
import com.enums.PackageName;
import com.enums.PolicyStatus;
import com.enums.ReviewStatus;
import com.exception.InvalidOwnershipException;
import com.exception.ResourceNotFoundException;
import com.model.Policy;
import com.model.User;
import com.service.AuthenticateService;
import com.service.PolicyService;
import org.hibernate.Session;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Session session = HibernateConfig.getSessionFactory().openSession();
        System.out.println("Working");
        Scanner sc = new Scanner(System.in);
        PolicyService policyService = new PolicyService(session);
        AuthenticateService authenticateService=new AuthenticateService(session);

        System.out.println("-----Vehicle Insurance Policy : LOGIN ------");
        System.out.println("Enter the username: ");
        String username=sc.next();
        System.out.println("Enter the password: ");
        String password=sc.next();
        try{
            User user=authenticateService.login(username,password);
            switch (user.getRole().toString())
            {
                case "CUSTOMER":
                    System.out.println("----CUSTOMER MENU -----");
                    while(true) {
                        System.out.println("1. Add Policy");
                        System.out.println("2. Delete Policy by id");
                        System.out.println("3. Fetch all Policies");
                        System.out.println("4. Update Policy");
                        System.out.println("0. Exit ");
                        int op = sc.nextInt();
                        if (op == 0)
                            break;
                        switch (op){
                            case 1:
                                Policy policy=new Policy();
                                sc.nextLine();
                                System.out.println("Enter the Policy Status: ");
                                policy.setPolicyStatus(PolicyStatus.valueOf(sc.nextLine()));
                                System.out.println("Enter the Package Name: ");
                                policy.setPackageName(PackageName.valueOf(sc.nextLine()));
                                System.out.println("Enter the Premium Amount: ");
                                policy.setPremiumAmount(sc.nextDouble());
                                sc.nextLine();
                                System.out.println("Enter the Review Status: ");
                                policy.setReviewStatus(ReviewStatus.valueOf(sc.nextLine()));
                                System.out.println("Enter the Review Remarks: ");
                                policy.setReviewRemarks(sc.nextLine());
                                policyService.addPolicy(policy,username);
                                System.out.println("Policy Added.....");
                                break;
                            case 2:
                                System.out.println("Enter the Policy ID to delete: ");
                                int id=sc.nextInt();
                                try{
                                    policyService.deletePolicyById(id,username);
                                    System.out.println("Policy deleted Deleted");
                                }catch(ResourceNotFoundException | InvalidOwnershipException e){
                                    System.out.println(e.getMessage());
                                }
                                break;
                        }
                    }
                    break;
                case "OFFICER":
                    break;
                case "ADMIN":
                    break;
            }
        }catch (ResourceNotFoundException e ){

        }



        session.close();
    }
}
