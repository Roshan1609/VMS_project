package com.app.dao_impl;

import com.app.dao.PolicyDao;
import com.app.enums.PolicyStatus;
import com.app.exception.InvalidOwnerShipException;
import com.app.exception.ResourceNotFoundException;
import com.app.model.Customer;
import com.app.model.Policy;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class PolicyDaoImpl implements PolicyDao {
    @PersistenceContext
    private EntityManager entityManager;

    private CustomerDaoImpl customerDao;
    @Autowired
    public PolicyDaoImpl(CustomerDaoImpl customerDao) {
        this.customerDao = customerDao;
    }

    @Override
    public void insert(Policy policy, String username) {
        Customer customer=customerDao.getCustomerByname(username);
        policy.setCustomer(customer);
        policy.setPolicyStatus(PolicyStatus.ACTIVE);

        entityManager.persist(policy);
    }

    @Override
    public List<Policy> getAll(String username) {
        TypedQuery<Policy> query=entityManager.createQuery("select p from Policy p where p.customer.user.username=:username", Policy.class);
        query.setParameter("username",username);
        return query.getResultList();
    }

    @Override
    public Policy getById(int id, String username) {

        Policy policy=entityManager.find(Policy.class,id);
        if(policy==null){
            throw new ResourceNotFoundException("Invalid ID given");
        }

        if(!(policy.getCustomer().getUser().getUsername().equals(username))){
            throw new InvalidOwnerShipException("You do not have this id");
        }
        return policy;
    }

    @Override
    public void update(Policy policy) {
    entityManager.merge(policy);
    }

    @Override
    public void delete(int id1 ,String username) {
        Query query=entityManager.createQuery("delete from Policy p where p.customer.user.username=:username and p.id=:id");
        query.setParameter("username",username);
        query.setParameter("id",id1);
        query.executeUpdate();

    }
}
