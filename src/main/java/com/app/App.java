package com.app;

import com.app.config.AppConfig;
import com.app.dao.AuthDao;
import com.app.dao.PolicyDao;
import com.app.dao_impl.AuthDaoImpl;
import com.app.enums.PackageName;
import com.app.enums.ReviewStatus;
import com.app.exception.ResourceNotFoundException;
import com.app.model.Policy;
import com.app.model.User;
import jakarta.persistence.NoResultException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        System.out.println("Working...");

        AuthDao authDao = context.getBean(AuthDaoImpl.class);
        PolicyDao policyDao = context.getBean(PolicyDao.class);
        Scanner sc = new Scanner(System.in);
        System.out.println("----------Vehicle Insurance: LOGIN---------");
        System.out.println("Enter Username ");
        String username = sc.next();
        System.out.println("Enter Password ");
        String password = sc.next();
        try {
            User user = authDao.login(username, password);
            System.out.println(user);
            switch (user.getRole().toString()) {
                case "CUSTOMER":
                    System.out.println("Welcome " + username);
                    while (true) {
                        System.out.println("1. Add Policy");
                        System.out.println("2. Delete Policy by id");
                        System.out.println("3. Fetch all Policies");
                        System.out.println("4. Update policy");
                        System.out.println("0. Exit ");
                        int op = sc.nextInt();
                        if (op == 0)
                            break;
                        switch (op) {
                            case 1:
                                sc.nextLine();
                                System.out.println("Enter the packageName: ");
                                String packageName = sc.nextLine();
                                System.out.println("Enter the premiumAmount: ");
                                Double premiumAmount = sc.nextDouble();
                                sc.nextLine();
                                System.out.println("Enter the reviewStatus: ");
                                String reviewStatus = sc.nextLine();
                                System.out.println("Enter the review Remarks: ");
                                String reviewRemarks = sc.nextLine();
                                policyDao.insert(new Policy(PackageName.valueOf(packageName), premiumAmount, ReviewStatus.valueOf(reviewStatus), reviewRemarks), username);
                                System.out.println("Policy Added....");
                                break;
                            case 2:
                                System.out.println("Enter the Policy ID to delete: ");
                                int id1=sc.nextInt();
                                sc.nextLine();
                                policyDao.delete(id1,username);
                                System.out.println("Policy Deleted.....");
                                break;
                            case 3:
                                System.out.println("-----All Policies-----");
                                policyDao.getAll(username).forEach(System.out::println);
                                break;
                            case 4:
                                System.out.println("Enter the ID to update: ");
                                int id = sc.nextInt();
                                try {
                                    Policy policy = policyDao.getById(id, username);
                                    sc.nextLine();
                                    System.out.println("Enter the packageName: ");
                                    packageName = sc.nextLine();
                                    System.out.println("Enter the premiumAmount: ");
                                    premiumAmount = sc.nextDouble();
                                    sc.nextLine();
                                    System.out.println("Enter the reviewStatus: ");
                                    reviewStatus = sc.nextLine();
                                    System.out.println("Enter the review Remarks: ");
                                    reviewRemarks = sc.nextLine();
                                    policy.setPackageName(PackageName.valueOf(packageName));
                                    policy.setPremiumAmount(premiumAmount);
                                    policy.setReviewStatus(ReviewStatus.valueOf(reviewStatus));
                                    policy.setReviewRemarks(reviewRemarks);

                                    policyDao.update(policy);
                                    System.out.println("Policy updated....");


                                } catch (IllegalArgumentException e) {
                                    throw new RuntimeException(e);
                                }
                        }

                    }
            }


        } catch (NoResultException e) {
            System.out.println("Invalid Credentials");
        }

    }
}