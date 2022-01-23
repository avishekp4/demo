package com.example.demo.repository;

import com.example.demo.entity.Idea;
import com.example.demo.entity.Idearating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IdearatingRepository extends JpaRepository<Idearating, Integer>, JpaSpecificationExecutor<Idearating> {
    List<Idearating> findAllByidea(Idea idea);

    int deleteAllByidea(Idea idea);
}