package com.example.demo.repository;

import com.example.demo.entity.Citizen;
import com.example.demo.entity.Idea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IdeaRepository extends JpaRepository<Idea, Integer>, JpaSpecificationExecutor<Idea> {
    List<Idea> findBycontenderid(Citizen citizen);
}