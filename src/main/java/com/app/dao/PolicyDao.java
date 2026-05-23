package com.app.dao;

import com.app.model.Policy;

import java.util.List;

public interface PolicyDao {
    List<Policy> getAll(String customerUsername);
    void insert(Policy policy, String customerUsername);
    Policy getById(int id,String customerUsername);
    void update(Policy policy);

    void deleteId(int id1);
}
