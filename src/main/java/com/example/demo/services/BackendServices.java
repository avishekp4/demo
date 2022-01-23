package com.example.demo.services;

import com.example.demo.entity.Citizen;
import com.example.demo.entity.Idea;
import com.example.demo.entity.Idearating;
import com.example.demo.repository.IdeaRepository;
import com.example.demo.repository.IdearatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BackendServices {
      @Autowired
      IdearatingRepository idearatingRepository;
      @Autowired
      IdeaRepository ideaRepository;
      @Async
      @Transactional
      public void updateIdea(Idearating ideaRating){
            List<Idearating> idearatingList= idearatingRepository.findAllByidea(ideaRating.getIdea());
            int countRating = 0;
            for (Idearating idearating: idearatingList
                 ) {
                  if(ideaRating.getRating()<5){
                        countRating+=1;
                  }
            }
            if(countRating>=3){
                  idearatingRepository.deleteAllByidea(ideaRating.getIdea());
                  ideaRepository.deleteById(ideaRating.getIdea().getId());
            }
      }
}
