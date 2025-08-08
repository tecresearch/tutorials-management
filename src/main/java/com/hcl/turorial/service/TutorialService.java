package com.hcl.turorial.service;

import com.hcl.turorial.dto.TutorialRequest;
import com.hcl.turorial.dto.TutorialResponse;
import com.hcl.turorial.model.Tutorial;

import java.util.List;

public interface TutorialService {
    public TutorialResponse getTutorialByTitle(String title);
    public TutorialResponse createTutorial(TutorialRequest tutorialRequest);
    public TutorialResponse getTutorialById(Long id);
    public TutorialResponse updateTutorial(Long id, TutorialRequest tutorialRequest);
    public void deleteTutorialById(Long id);
    public void deleteAllTutorials();
    public List<TutorialResponse> getAllTutorials();
    public List<TutorialResponse> getTutorialPublished();
    public Boolean tutorialPublishedById(Long id);
    public Boolean tutorialUnPublishedById(Long id);

}
