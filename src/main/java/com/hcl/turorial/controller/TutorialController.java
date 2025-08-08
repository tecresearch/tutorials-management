package com.hcl.turorial.controller;

import com.hcl.turorial.dto.TutorialRequest;
import com.hcl.turorial.dto.TutorialResponse;
import com.hcl.turorial.service.TutorialService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/platform/v1/tutorials")
public class TutorialController {
        @Autowired
        private TutorialService tutorialService;
        @Operation(summary = "Add tutorial with No dublicate title",
                description = "Add tutorial  in the system.")
        @PostMapping("/create")
        public ResponseEntity<TutorialResponse> createTutorial(@RequestBody @Valid TutorialRequest tutorialRequest) {
            return ResponseEntity.status(HttpStatus.CREATED).body(tutorialService.createTutorial(tutorialRequest));
        }
        @Operation(summary = "Get tutorial by id",
                description = "Get   tutorial available in the system.")
        @GetMapping("/get/{id}")
        public ResponseEntity<TutorialResponse> getTutorial(@PathVariable long id){
                return ResponseEntity.status(HttpStatus.OK).body(tutorialService.getTutorialById(id));
        }
        @Operation(summary = "Update tutorial by id",
                description = "Update   tutorial available in the system.")
        @PutMapping("/update/{id}")
        public ResponseEntity<TutorialResponse> updateTutorial(@PathVariable long id, @RequestBody TutorialRequest tutorialRequest) {
                return ResponseEntity.status(HttpStatus.OK).body(tutorialService.updateTutorial(id,tutorialRequest));
        }
        @Operation(summary = "Delete tutorial by id",
                description = "Delete   tutorial available in the system.")
        @DeleteMapping("delete/{id}")
        public ResponseEntity<Void> deleteTutorial(@PathVariable long id) {
                tutorialService.deleteTutorialById(id);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        @Operation(summary = "Delete all tutorials",
                description = "Delete  tutorials available in the system.")
        @DeleteMapping("/delete-all")
        public ResponseEntity<Void> deleteAllTutorials() {
                tutorialService.deleteAllTutorials();
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        @Operation(summary = "Get all tutorial ",
                description = "Retrieves a list of all tutorials available in the system.")
        @GetMapping("/get-all")
        public ResponseEntity<List<TutorialResponse>> getAllTutorials() {
                return ResponseEntity.status(HttpStatus.OK).body(tutorialService.getAllTutorials());

        }
        @Operation(summary = "Get only published",
                description = "Retrieves a list of all tutorials which was published.")
        @GetMapping("/published")
        public ResponseEntity<List<TutorialResponse>> getTutorialPublished() {
                return ResponseEntity.status(HttpStatus.OK).body(tutorialService.getTutorialPublished());
        }
        @Operation(summary = "Get tutorial by title",
                description = "Retrieves   tutorial available in the system.")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "Successfully retrieved tutorials"),
                @ApiResponse(responseCode = "404", description = "Tutorials not found"),
                @ApiResponse(responseCode = "500", description = "Internal server error")
        })
        @GetMapping("/get")
        public ResponseEntity<TutorialResponse> getTutorialByTitle(@RequestParam("title") String title) {
                return ResponseEntity.status(HttpStatus.OK).body(tutorialService.getTutorialByTitle(title));
        }
        @Operation(summary = "Publish your tutorial by id",
        description = "Changing the status of the tutorial status ")
        @PutMapping("/publish/{id}")
        public ResponseEntity<Map<String,Object>> publishTutorial(@PathVariable long id) {
                Map<String, Object> response = new HashMap<>();
                        response.put("status", tutorialService.tutorialPublishedById(id));
                        response.put("message", "Tutorial published successfully");
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
        }

        @Operation(summary = "UnPublish your tutorial by id",
                description = "Changing the status of the tutorial status ")
        @PutMapping("/unpublish/{id}")
        public ResponseEntity<Map<String,Object>> uNpublishTutorial(@PathVariable long id) {
                Map<String, Object> response = new HashMap<>();
                response.put("status", tutorialService.tutorialUnPublishedById(id));
                response.put("message", "Tutorial unpublished successfully");
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
        }


}
