package com.example.demo.repository;

import com.example.demo.entity.Citizen;

import java.util.List;

public interface CitizenRepository extends org.springframework.data.jpa.repository.JpaRepository<com.example.demo.entity.Citizen, Integer> ,org.springframework.data.jpa.repository.JpaSpecificationExecutor<com.example.demo.entity.Citizen> {

    List<Citizen> findAllByisContender(Boolean flag);
}