package com.hcl.turorial.controller;

import com.hcl.turorial.dto.TutorialRequest;
import com.hcl.turorial.dto.TutorialResponse;
import com.hcl.turorial.service.TutorialService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/platform/tutorial/v1")
public class TutorialController {
        @Autowired
        private TutorialService tutorialService;
        @PostMapping("/create")
        public ResponseEntity<TutorialResponse> createTutorial(@RequestBody @Valid TutorialRequest tutorialRequest) {
            return ResponseEntity.status(HttpStatus.CREATED).body(tutorialService.createTutorial(tutorialRequest));
        }
}
