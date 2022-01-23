package com.example.demo.repository;

import com.example.demo.entity.Citizen;
import com.example.demo.entity.Follower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface FollowerRepository extends JpaRepository<Follower, Integer>, JpaSpecificationExecutor<Follower> {
   List<Follower> findAllFollowerBycontenderid(Citizen citizen);
}