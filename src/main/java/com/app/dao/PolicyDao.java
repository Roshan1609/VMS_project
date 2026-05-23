package com.app.dao;

import com.app.model.Policy;

import java.util.List;

public interface PolicyDao {
    void insert(Policy policy, String username);

    List<Policy> getAll(String username);

    Policy getById(int id, String username);

    void update(Policy policy);

    void delete(int id1,String username);
}
