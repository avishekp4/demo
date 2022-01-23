package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ideas")
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class Idea implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "contenderid")
    private Citizen contenderid;

    @Column(name = "idea_string")
    private String ideaString;

    public String getIdeaString() {
        return ideaString;
    }

    public void setIdeaString(String ideaString) {
        this.ideaString = ideaString;
    }

    public Citizen getContenderid() {
        return contenderid;
    }

    public void setContenderid(Citizen contenderid) {
        this.contenderid = contenderid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}