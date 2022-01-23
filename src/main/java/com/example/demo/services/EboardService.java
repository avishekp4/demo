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

    public void revokenomination(int id) {
        Citizen citizen = citizenRepository.getById(id);
        citizen.setIsContender(false);
        citizenRepository.save(citizen);
    }

    public List<Idea> getAllIdeaOfContender(int id) {
        Citizen citizen = citizenRepository.findById(id).orElse(null);
        List<Idea> ideaList = new ArrayList<>();
        if (citizen != null)
            ideaList.addAll(ideaRepository.findBycontenderid(citizen));
        return ideaList;
    }

    public void getFollowerOfCitizen(int id) {

    }

    public List<Follower> getFollowerOfContender(Integer id) {
        Citizen citizen = citizenRepository.findById(id).orElse(null);
        if (citizen != null)
            return followerRepository.findAllFollowerBycontenderid(citizen);
        return new ArrayList<>();
    }

    public Idea getIdeaForId(int id) {
        Optional<Idea> ideaOptional = ideaRepository.findById(id);
        return ideaOptional.orElseGet(Idea::new);
    }

    public void addIdea(IdeaRequest request) {
        Idea idea = new Idea();
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

    public Idea updateIdea(Idea newIdea) {
        return ideaRepository.findById(newIdea.getId())
                .map(idea -> {
                    idea.setIdeaString(newIdea.getIdeaString());
                    return ideaRepository.save(idea);
                })
                .orElseGet(() -> ideaRepository.save(newIdea));
    }

    public void deleteIdea(int id) {
        ideaRepository.deleteById(id);
    }
}
