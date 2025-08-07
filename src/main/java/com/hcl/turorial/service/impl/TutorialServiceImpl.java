package com.hcl.turorial.service.impl;

import com.hcl.turorial.common.exception.DuplicateResourceException;
import com.hcl.turorial.common.exception.ResourceNotFoundException;
import com.hcl.turorial.dto.TutorialRequest;
import com.hcl.turorial.dto.TutorialResponse;
import com.hcl.turorial.model.Tutorial;
import com.hcl.turorial.repository.TutorialRepository;
import com.hcl.turorial.service.TutorialService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TutorialServiceImpl implements TutorialService {
    @Autowired
    private TutorialRepository tutorialRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public Tutorial getTutorialByTitle(String title) {
        return tutorialRepository.getTutorialByTitle(title);
    }
    @Override
    public TutorialResponse createTutorial(TutorialRequest tutorialRequest) {
        if(tutorialRepository.existsByTitle(tutorialRequest.getTitle())){
            throw new DuplicateResourceException("Duplicate entry "+"{ title: "+tutorialRequest.getTitle()+"}");
        }
        Tutorial tutorial = new Tutorial();
        tutorial.setTitle(tutorialRequest.getTitle());
        tutorial.setDescription(tutorialRequest.getDescription());
        tutorial.setStatus(tutorialRequest.getStatus());
        this.tutorialRepository.save(tutorial);
        return modelMapper.map(getTutorialByTitle(tutorialRequest.getTitle()), TutorialResponse.class);

    }
}
