package com.example.demo.request;


import java.util.List;
import java.util.Map;

public class Contender {
    public String getContender() {
        return contender;
    }

    public void setContender(String contender) {
        this.contender = contender;
    }

    public int getFollowerNo() {
        return followerNo;
    }

    public void setFollowerNo(int followerNo) {
        this.followerNo = followerNo;
    }

    public List<Map<String, Object>> getIdeaRating() {
        return ideaRating;
    }

    public void setIdeaRating(List<Map<String, Object>> ideaRating) {
        this.ideaRating = ideaRating;
    }

    String contender;
    int followerNo;

    public int getFinalRating() {
        return finalRating;
    }

    public void setFinalRating(int finalRating) {
        this.finalRating = finalRating;
    }

    int finalRating;
    List<Map<String, Object>> ideaRating;
}
