package com.example.demo.services;

import com.example.demo.entity.Citizen;
import com.example.demo.entity.Follower;
import com.example.demo.entity.Idea;
import com.example.demo.entity.Idearating;
import com.example.demo.repository.CitizenRepository;
import com.example.demo.repository.FollowerRepository;
import com.example.demo.repository.IdeaRepository;
import com.example.demo.repository.IdearatingRepository;
import com.example.demo.request.IdeaRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EboardService {
    @Autowired
    CitizenRepository citizenRepository;
    @Autowired
    IdeaRepository ideaRepository;
    @Autowired
    IdearatingRepository idearatingRepository;
    @Autowired
    FollowerRepository followerRepository;

    public void nominateContender(int id) {
        Citizen citizen = citizenRepository.getById(id);
        citizen.setIsContender(true);
        citizenRepository.save(citizen);
    }

    public List<Idea> getAllIdeaOfContender(int id) {
        Optional<Citizen> citizen = citizenRepository.findById(id);
        Citizen citizen1;
        citizen1 = citizen.orElse(null);
        List<Idea> ideaList = new ArrayList<>();
        if(citizen1!= null)
             ideaList.addAll(ideaRepository.findBycontenderid(citizen1));
        return ideaList;
    }

    public void getFollowerOfCitizen(int id) {

    }

    public List<Integer> getFollowerOfContender(Integer id) {
        Citizen citizen = citizenRepository.getById(id);
        return followerRepository.getAllFollowerByContender(id);
    }

    public Idea getIdeaForId(int id) {
        Optional<Idea> ideaOptional = ideaRepository.findById(id);
        return ideaOptional.orElseGet(Idea::new);
    }

    public void addIdea(IdeaRequest request) {
        Idea idea = new Idea();
        System.out.println("Current post " + request.getIdea() + " id " + request.getCitizenId());
        idea.setIdeaString(request.getIdea());
        Citizen citizen = citizenRepository.getById(request.getCitizenId());
        if (citizen.getIsContender())
            idea.setContenderid(citizen);
        ideaRepository.save(idea);
    }

    public void rateIdea(int ideaId, int citizenId, int rating) {
        Idea idea = ideaRepository.getById(ideaId);
        Citizen citizen = citizenRepository.getById(citizenId);
        Idearating idearating = new Idearating();
        idearating.setIdea(idea);
        idearating.setCitizen(citizen);
        idearating.setRating(rating);
        idearatingRepository.save(idearating);

    }

    public void makeFollower(int contid, int citid) {
        Citizen contender = citizenRepository.getById(contid);
        Citizen citizen = citizenRepository.getById(citid);
        Follower follower = new Follower();
        follower.setFollowerid(citizen);
        follower.setContenderid(contender);
        followerRepository.save(follower);
    }
}
