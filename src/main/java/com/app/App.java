package com.app;

import com.app.config.AppConfig;
import com.app.dao.PolicyDao;
import com.app.daoImpl.PolicyDaoImpl;
import com.app.enums.PackageName;
import com.app.enums.PolicyStatus;
import com.app.enums.ReviewStatus;
import com.app.exception.ResourceNotFoundException;
import com.app.model.Policy;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;

import java.time.LocalDate;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(AppConfig.class);
        Scanner sc=new Scanner(System.in);
        PolicyDao policyDao=context.getBean(PolicyDaoImpl.class);
        while (true)
        {
            System.out.println("1. Add Policy");
            System.out.println("2. Delete Policy by Id");
            System.out.println("3. All Policies ");
            System.out.println("4. Get Policy by id");
            System.out.println("5. Update Policy ");
            System.out.println("0. Exit");
            int op=sc.nextInt();
            if(op==0){
                break;
            }
            switch (op)
            {
                case 1:

                    policyDao.insert(new Policy(LocalDate.of(2026, 1, 1),
                            LocalDate.of(2027, 1, 1),
                            PackageName.PREMIUM,
                            PolicyStatus.ACTIVE,
                            25000.0,
                            "All documents verified",
                            ReviewStatus.APPROVED,
                            1,
                            1,
                            1));
                    break;
                case 2:
                    System.out.println("Enter the id to delete: ");
                    int id= sc.nextInt();
                    try{
                        policyDao.deletebyId(id);
                    }
                    catch(ResourceNotFoundException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    policyDao.getAll().forEach(System.out::println);
                    break;
                case 4:
                    System.out.println("Enter the ID to Policy");
                    int id1= sc.nextInt();
                    try{
                        Policy policy=policyDao.getById(id1);
                        System.out.println(policy);
                    }
                    catch (EmptyResultDataAccessException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 5:
                    System.out.println("Enter the ID to update");
                    int id2=sc.nextInt();
                    try{
                        Policy policy=policyDao.getById(id2);
                        System.out.println(policy);
                        System.out.println("Enter Review remarks :");
                        sc.nextLine();
                        String review=sc.nextLine();
                        policy.setReviewRemarks(review);
                        policyDao.update(policy);
                    }
                    catch (EmptyResultDataAccessException e){
                        System.out.println(e.getMessage());
                    }
                    break;
            }

        }
        context.close();
    }
}
