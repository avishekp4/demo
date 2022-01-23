package com.example.demo.services;

import com.example.demo.entity.Citizen;
import com.example.demo.entity.Follower;
import com.example.demo.entity.Idea;
import com.example.demo.entity.Idearating;
import com.example.demo.repository.CitizenRepository;
import com.example.demo.repository.FollowerRepository;
import com.example.demo.repository.IdeaRepository;
import com.example.demo.repository.IdearatingRepository;
import com.example.demo.request.Contender;
import com.example.demo.request.IdeaRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
    @Autowired
    private BackendServices backendServices;

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


    public void getFollowerOfCitizen(int id) {

    }

    public List<Follower> getFollowerOfContender(Integer id) {
        Citizen citizen = citizenRepository.findById(id).orElse(null);
        if (citizen != null)
            return followerRepository.findAllFollowerBycontenderid(citizen);
        return new ArrayList<>();
    }

    public Idea getIdeaForId(int id) {
        return ideaRepository.findById(id).orElse(new Idea());
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
        if (idearating.getRating() < 5)
            backendServices.updateIdea(idearating);
    }

    public List<Idea> getAllIdeaOfContender(int id) {
        Citizen citizen = citizenRepository.findById(id).orElse(null);
        List<Idea> ideaList = new ArrayList<>();
        if (citizen != null)
            ideaList.addAll(ideaRepository.findBycontenderid(citizen));
        return ideaList;
    }

    public List<Idearating> getAllIdeaReatingForIdea(int ideaId) {
        Idea idea = ideaRepository.getById(ideaId);
        return idearatingRepository.findAllByidea(idea);
    }

    public Integer averageRating(int ideaId) {
        Idea idea = ideaRepository.getById(ideaId);
        List<Idearating> idearatingList = idearatingRepository.findAllByidea(idea);
        int totalVoter = idearatingList.size();
        if (totalVoter == 0) {
            return 0;
        }
        int sum = 0;
        for (Idearating idearating : idearatingList
        ) {
            sum += idearating.getRating();
        }
        return sum / totalVoter;
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

    public int finalRatingContender(int contenderId) {
        List<Idea> ideaList = getAllIdeaOfContender(contenderId);
        int finalRating = 0;
        for (Idea idea : ideaList
        ) {
            finalRating += averageRating(idea.getId());
        }
        return finalRating;
    }

    public List<Contender> getEboadStatus() {
        List<Citizen> citizenList = citizenRepository.findAllByisContender(true);
        List<Contender> contenders = new ArrayList<>();
        for (Citizen citizen : citizenList) {
            Contender contender = new Contender();
            List<Map<String, Object>> ideasAndRating = new ArrayList<>();
            contender.setContender(citizen.getName());
            List<Idea> ideaList = getAllIdeaOfContender(citizen.getId());
            int totalRating = 0;
            for (Idea idea : ideaList) {
                Map<String, Object> mp = new HashMap<>();
                mp.put("idea", idea.getIdeaString());
                int averageRating = averageRating(idea.getId());
                totalRating += averageRating;
                mp.put("averageRating", averageRating);
                ideasAndRating.add(mp);
            }
            contender.setFollowerNo(followerRepository.findAllFollowerBycontenderid(citizen).size());
            contender.setFinalRating(totalRating);
            contender.setIdeaRating(ideasAndRating);
            contenders.add(contender);
        }
        return contenders;
    }
}
