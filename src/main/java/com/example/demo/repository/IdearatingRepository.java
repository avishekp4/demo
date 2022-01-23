package com.example.demo.repository;

import com.example.demo.entity.Idearating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IdearatingRepository extends JpaRepository<Idearating, Integer>, JpaSpecificationExecutor<Idearating> {
}