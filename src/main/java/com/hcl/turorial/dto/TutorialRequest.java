package com.hcl.turorial.dto;

public class TutorialRequest {
    private String title;
    private String description;
    private  Boolean status;

public TutorialRequest(){}
    public TutorialRequest(String title, String description, Boolean status) {
        this.title = title;
        this.description = description;
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
