package com.example.demo.repository;

public interface CitizenRepository extends org.springframework.data.jpa.repository.JpaRepository<com.example.demo.entity.Citizen, Integer> ,org.springframework.data.jpa.repository.JpaSpecificationExecutor<com.example.demo.entity.Citizen> {
}