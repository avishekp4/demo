package com.example.demo.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "follower")
public class Follower implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "contenderid", nullable = false)
    private Citizen contenderid;

    @ManyToOne(optional = false)
    @JoinColumn(name = "followerid", nullable = false)
    private Citizen followerid;

    public Citizen getFollowerid() {
        return followerid;
    }

    public void setFollowerid(Citizen followerid) {
        this.followerid = followerid;
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