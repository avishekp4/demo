package com.example.demo.repository;

import com.example.demo.entity.Citizen;
import com.example.demo.entity.Follower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FollowerRepository extends JpaRepository<Follower, Integer>, JpaSpecificationExecutor<Follower> {
@Query(value = "Select followerid from follower where contenderid = ?1",nativeQuery = true)
    List<Integer> getAllFollowerByContender(Integer contenderid);
}