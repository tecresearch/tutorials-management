package com.hcl.turorial.service;

import com.hcl.turorial.dto.TutorialRequest;
import com.hcl.turorial.dto.TutorialResponse;
import com.hcl.turorial.model.Tutorial;

public interface TutorialService {
    public Tutorial getTutorialByTitle(String title);
    public TutorialResponse createTutorial(TutorialRequest tutorialRequest);

}
