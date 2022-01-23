package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "citizen")
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class Citizen implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getContender() {
        return isContender;
    }

    public void setContender(Boolean contender) {
        isContender = contender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "address")
    private String address;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "is_contender", nullable = false)
    private Boolean isContender = false;

    @Column(name = "mobile", nullable = false)
    private Integer mobile;

    @Column(name = "name", nullable = false)
    private String name;


    public Integer getMobile() {
        return mobile;
    }

    public void setMobile(Integer mobile) {
        this.mobile = mobile;
    }

    public Boolean getIsContender() {
        return isContender;
    }

    public void setIsContender(Boolean isContender) {
        this.isContender = isContender;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}