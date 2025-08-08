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

import java.util.ArrayList;
import java.util.List;

@Service
public class TutorialServiceImpl implements TutorialService {
    @Autowired
    private TutorialRepository tutorialRepository;
    @Autowired
    private ModelMapper modelMapper;

    /**
     *
     * @param title fetched tutorial
     * @return tutorial
     */
    @Override
    public TutorialResponse getTutorialByTitle(String title) {
        if(!tutorialRepository.existsByTitle(title)){
            throw new ResourceNotFoundException("Tutorial with title "+title+" not found!");
        }
        return modelMapper.map(tutorialRepository.getTutorialByTitle(title),TutorialResponse.class);
    }

    /**
     *
     * @param tutorialRequest dto object first convert to tutorial and persists
     * @return TutorialResponse
     *
     */

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
        return getTutorialByTitle(tutorialRequest.getTitle());

    }

    /**
     *
     * @param id from user
     * @return tutorial object
     */
    @Override
    public TutorialResponse getTutorialById(Long id) {
           Tutorial tutorial= tutorialRepository.findById(id)
                   .orElseThrow(()->new ResourceNotFoundException("Tutorial with id "+id+" not found!"));
           return  modelMapper.map(tutorial, TutorialResponse.class);
    }

    /**
     *
     * @param id from user
     * @param tutorialRequest from user to be updated
     * @return response to the user
     */
    @Override
    public TutorialResponse updateTutorial(Long id ,TutorialRequest tutorialRequest){
         Tutorial oldTutorial=tutorialRepository.findById(id)
                 .orElseThrow(()->new ResourceNotFoundException("Tutorial with id "+id+" not found!"));
         oldTutorial.setTitle(tutorialRequest.getTitle());
         oldTutorial.setDescription(tutorialRequest.getDescription());
         oldTutorial.setStatus(tutorialRequest.getStatus());
         return modelMapper.map(tutorialRepository.save(oldTutorial),TutorialResponse.class);

    }

    /**
     *
     * @param id to be deleted
     */
    @Override
    public void deleteTutorialById(Long id){
        if(tutorialRepository.existsById(id)){
            tutorialRepository.deleteById(id);
        }else{
            throw new ResourceNotFoundException("Tutorial with id "+id+" not found!");
        }
    }

    /**
     * Delete all if exists
     */
    @Override
    public void deleteAllTutorials(){
        if(!tutorialRepository.findAll().isEmpty()){
            tutorialRepository.deleteAll();
        }else{
            throw new ResourceNotFoundException("No Tutorial found!");
        }
    }
    /**
     * fetch all
     */
    @Override
    public List<TutorialResponse> getAllTutorials() {
       if(tutorialRepository.findAll().isEmpty()){
            throw new ResourceNotFoundException("No Tutorial found!");
       }
       List<Tutorial> tutorials = tutorialRepository.findAll();
       List<TutorialResponse>  tutorialResponses=new ArrayList<>();
       for(Tutorial tutorial: tutorials){
           tutorialResponses.add(modelMapper.map(tutorial, TutorialResponse.class));
       }

       return tutorialResponses;
    }

    /**
     *
     * @return only published
     */
    @Override
    public List<TutorialResponse> getTutorialPublished() {
        if(tutorialRepository.findAll().isEmpty()){
            throw new ResourceNotFoundException("No Tutorial Published found!");
        }
        List<Tutorial> publishedTutorials = tutorialRepository.findAllByStatus(true);
        List<TutorialResponse>  tutorialResponses=new ArrayList<>();
        for(Tutorial tutorial: publishedTutorials){
            tutorialResponses.add(modelMapper.map(tutorial, TutorialResponse.class));
        }
        return tutorialResponses;

    }


}
