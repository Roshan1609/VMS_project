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
        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(AppConfig.class);
        AuthDao authDao=context.getBean(AuthDaoImpl.class);
        PolicyDao policyDao=context.getBean(PolicyDao.class);
        Scanner sc = new Scanner(System.in);
        System.out.println("----------Vehicle : LOGIN---------");
        System.out.println("Enter Username ");
        String username = sc.next();
        System.out.println("Enter Password ");
        String password = sc.next();
        try{
            User user=authDao.login(username,password);
            System.out.println(user);
            switch (user.getRole().toString())
            {
                case "CUSTOMER":
                    System.out.println("Welcome " + username);
                    while(true)
                    {
                        System.out.println("1. Add Policy");
                        System.out.println("2. Delete Policy by id");
                        System.out.println("3. Fetch all Policies");
                        System.out.println("4. Update Policy");
                        System.out.println("0. Exit ");
                        int op = sc.nextInt();
                        if (op == 0)
                            break;
                        switch (op) {
                            case 1:
                                sc.nextLine();
                                System.out.println("Enter the Data's to Insert into Policy....");
                                System.out.println("Enter the Package Name: ");
                                String packagename=sc.nextLine();
                                System.out.println("Enter the Premium Amount: ");
                                Double amount=sc.nextDouble();
                                sc.nextLine();
                                System.out.println("Enter the Review Remarks :");
                                String remarks=sc.nextLine();
                                System.out.println("Enter the Review Status: ");
                                String reviewStatus=sc.nextLine();

                                policyDao.insert(new Policy(PackageName.valueOf(packagename),amount,remarks,ReviewStatus.valueOf(reviewStatus)),username);
                                System.out.println("Policy Added......");
                                break;
                            case 2:
                                sc.nextLine();
                                System.out.println("Enter the Policy ID to delete :");
                                int id1= sc.nextInt();
                                Policy policy1=policyDao.getById(id1,username);

                                policyDao.deleteId(id1);
                                System.out.println("Policy deleted.....");
                                break;
                            case 3:
                                System.out.println("-----All Policies-----");
                                policyDao.getAll(username).forEach(System.out::println);
                                break;
                            case 4:
                                sc.nextLine();
                                System.out.println("Enter the ID to update: ");
                                int id=sc.nextInt();
                                Policy policy=policyDao.getById(id,username);
                                sc.nextLine();
                                System.out.println("Enter the Package Name: ");
                                packagename=sc.nextLine();
                                System.out.println("Enter the Premium Amount: ");
                                amount=sc.nextDouble();
                                sc.nextLine();
                                System.out.println("Enter the Review Remarks :");
                                remarks=sc.nextLine();
                                System.out.println("Enter the Review Status: ");
                                reviewStatus=sc.nextLine();

                                policy.setPackageName(PackageName.valueOf(packagename));
                                policy.setPremiumAmount(amount);
                                policy.setReviewRemarks(remarks);
                                policy.setReviewStatus(ReviewStatus.valueOf(reviewStatus));

                                policyDao.update(policy);
                                System.out.println("Policy Updated....");


                                break;

                        }
                    }
            }

        }
        catch(NoResultException e)
        {
            System.out.println("Invalid Credentials!!!");

        }


    }
}
