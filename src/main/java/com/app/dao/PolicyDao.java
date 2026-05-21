package com.app.dao;

import com.app.exception.ResourceNotFoundException;
import com.app.model.Policy;

import java.util.List;

public interface PolicyDao {
    void insert(Policy policy);
    List<Policy> getAll();
    Policy getById(int id) throws ResourceNotFoundException;
    void deletebyId(int id) throws  ResourceNotFoundException;
    void update(Policy policy) throws ResourceNotFoundException;

}
