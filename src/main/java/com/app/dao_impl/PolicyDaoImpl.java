package com.app.dao_impl;

import com.app.dao.PolicyDao;
import com.app.enums.PolicyStatus;
import com.app.exception.InvalidOwnershipException;
import com.app.exception.ResourceNotFoundException;
import com.app.model.Customer;
import com.app.model.Policy;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
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
    public void setCustomerDao(CustomerDaoImpl customerDao) {
        this.customerDao = customerDao;
    }

    @Override
    public List<Policy> getAll(String customerUsername) {
        TypedQuery<Policy> query=entityManager.createQuery("select p from Policy p where p.customer.user.username=:username", Policy.class);
        query.setParameter("username",customerUsername);
        return query.getResultList();
    }

    @Override
    public void insert(Policy policy, String customerUsername) {
        Customer customer=customerDao.getCustomerByusername(customerUsername);
        policy.setCustomer(customer);
        policy.setPolicyStatus(PolicyStatus.ACTIVE);
        entityManager.persist(policy);

    }

    @Override
    public Policy getById(int id, String customerUsername) {

        Policy policy=entityManager.find(Policy.class,id);
        if(policy==null){
            throw  new ResourceNotFoundException("Invalid ID given...");
        }
        if(!(policy.getCustomer().getUser().getUsername().equals(customerUsername)))
        {
            throw new InvalidOwnershipException("You do not have access to this ID..");
        }
        return policy;
    }

    @Override
    public void update(Policy policy) {
        entityManager.merge(policy);

    }

    @Override
    public void deleteId(int id1) {
        Query query=entityManager.createQuery("delete from Policy p where p.id=:id");
        query.setParameter("id",id1);
        query.executeUpdate();

    }
}
