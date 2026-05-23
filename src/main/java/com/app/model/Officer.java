package com.app.model;

import jakarta.persistence.*;

@Entity
public class Officer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;
    @Column(nullable = false)
    private String department;
    @Column(nullable = false)
    private String designation;
    @Column(nullable = false)
    private String name;
    @OneToOne
    private  User user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Officer{" +
                "id=" + id +
                ", department='" + department + '\'' +
                ", designation='" + designation + '\'' +
                ", name='" + name + '\'' +
                ", user=" + user +
                '}';
    }
}
