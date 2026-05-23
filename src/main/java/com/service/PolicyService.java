package com.service;

import com.enums.PolicyStatus;
import com.exception.ResourceNotFoundException;
import com.model.Customer;
import com.model.Policy;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class PolicyService {
    private Session session;
    private CustomerService customerService;

    public PolicyService(Session session) {

        this.session=session;
        customerService=new CustomerService(session);
    }

    public void insert(Policy policy) {
        Transaction tx=session.beginTransaction();
        session.persist(policy);
        tx.commit();
    }

    public void deleteByid(int id) {
        Transaction tx=session.beginTransaction();
        Policy policy=session.find(Policy.class,id);
        if(policy==null){
            tx.commit();
            throw  new ResourceNotFoundException("Invalid ID entered");
        }
        session.createMutationQuery("delete from Policy where id=:id")
                .setParameter("id",id)
                .executeUpdate();
        tx.commit();
    }

    public List<Policy> getAllTickets() {
        Transaction tx=session.beginTransaction();
        List<Policy> list=session.createQuery("from Policy ",Policy.class).list();
        tx.commit();
        return  list;
    }

    public Policy getbyId(int id) {
        Transaction tx=session.beginTransaction();
        Policy policy=session.find(Policy.class,id);
        if(policy==null){
            tx.commit();
            throw new ResourceNotFoundException("Invalid ID Entered");
        }
        tx.commit();
        return policy;
    }

    public void addPolicy(Policy policy, String username) {

            Customer customer=customerService.getCustomerByUsername(username);
            policy.setCustomer(customer);
            policy.setPolicyStatus(PolicyStatus.ACTIVE);
        Transaction tx=session.beginTransaction();
            session.persist(policy);
            tx.commit();



    }

    public void deletePolicyById(int id, String username) {
        Transaction tx=session.beginTransaction();
        Policy policy=session.find(Policy.class,id);
        tx.commit();
        if(policy==null){
            throw  new ResourceNotFoundException("Invalid ID is given");
        }
        Customer customer=customerService.getCustomerByUsername(username);

        if(policy.getCustomer().getId()!=customer.getId()){
            throw new ResourceNotFoundException("Customer value does not match the Policy ID");
        }
        tx=session.beginTransaction();
        session.remove(policy);
        tx.commit();
    }
}
