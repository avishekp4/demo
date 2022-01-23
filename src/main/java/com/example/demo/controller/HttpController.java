package com.example.demo.controller;

import com.example.demo.entity.Follower;
import com.example.demo.entity.Idea;
import com.example.demo.request.Contender;
import com.example.demo.request.IdeaRequest;
import com.example.demo.services.EboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")

public class HttpController {
    @Autowired
    private EboardService eboardService;

    @GetMapping("nominate/{id}")
    public void nominateContender(@PathVariable("id") int id) {
        eboardService.nominateContender(id);
    }

    @GetMapping("revokenomination/{id}")
    public void revokenomination(@PathVariable("id") int id) {
        eboardService.revokenomination(id);
    }

    @GetMapping("idea/{id}")
    public Idea getIdeaForId(@PathVariable("id") int id) {
        return eboardService.getIdeaForId(id);
    }

    @PostMapping("idea")
    public void addIdea(@RequestBody IdeaRequest request) {
        eboardService.addIdea(request);
    }

    @PutMapping("idea/{id}")
    public Idea updateIdea(@PathVariable("id") int id, @RequestBody Idea newIdea) {
        return eboardService.updateIdea(newIdea);
    }

    @DeleteMapping("idea/{id}")
    void deleteIdea(@PathVariable("id") int id) {
        eboardService.deleteIdea(id);
    }

    @GetMapping("idea/contender/{id}")
    public List<Idea> getAllIdeaOfContender(@PathVariable("id") int id) {
        return eboardService.getAllIdeaOfContender(id);
    }

    @GetMapping("makefollower")
    public void makeFollower(@RequestParam("contid") int contid, @RequestParam("citid") int citid) {
        eboardService.makeFollower(contid, citid);
    }

    @GetMapping("follower/{id}")
    public List<Follower> getFollowerOfContender(@PathVariable("id") int id) {
        return eboardService.getFollowerOfContender(id);
    }

    @GetMapping("follower/citizen/{id}")
    public void getFollowerOfCitizen(@PathVariable("id") int id) {
        eboardService.getFollowerOfCitizen(id);
    }

    @GetMapping("rate/{ideaId}/{citizenId}")
    public void rateIdea(@PathVariable("ideaId") int ideaId, @PathVariable("citizenId") int citizenId, @RequestParam("rating") int rating) {
        eboardService.rateIdea(ideaId, citizenId, rating);
    }

    @GetMapping("finalrating/{contId}")
    public int finalRatingContender(@PathVariable("contId") int contId) {
        return eboardService.finalRatingContender(contId);
    }

    @GetMapping("averagerating/{ideaId}")
    public int avarageRating(@PathVariable("ideaId") int ideaID) {
        return eboardService.averageRating(ideaID);
    }

    @GetMapping("status")
    public List<Contender> getEboardStatus() {
        return eboardService.getEboadStatus();
    }
}
