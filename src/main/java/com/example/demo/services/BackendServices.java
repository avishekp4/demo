package com.example.demo.services;

import com.example.demo.entity.Idea;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class BackendServices {
      @Async
      public void updateBoard(Idea idea){

      }
}
