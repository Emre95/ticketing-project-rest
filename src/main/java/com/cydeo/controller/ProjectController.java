package com.cydeo.controller;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.ResponseWrapper;
import com.cydeo.service.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/api/v1/project")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    @RolesAllowed({"Manager"})
    public ResponseEntity<ResponseWrapper> listAll() {

        return ResponseEntity.ok(new ResponseWrapper("Projects are successfully retrieved", projectService.listAllProjects(), HttpStatus.OK));
    }

    @GetMapping("/{projectCode}")
    @RolesAllowed({"Manager"})
    public ResponseEntity<ResponseWrapper> findByProjectCode(@PathVariable("projectCode") String projectCode) {

        return ResponseEntity.ok(new ResponseWrapper("Project is successfully retrieved", projectService.getByProjectCode(projectCode), HttpStatus.OK));
    }

    @PostMapping
    @RolesAllowed({"Manager"})
    public ResponseEntity<ResponseWrapper> create(@RequestBody ProjectDTO projectDTO) {

        projectService.save(projectDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseWrapper("Project is successfully created", HttpStatus.CREATED));
    }

    @PutMapping
    @RolesAllowed({"Manager"})
    public ResponseEntity<ResponseWrapper> update(@RequestBody ProjectDTO projectDTO) {

        projectService.update(projectDTO);

        return ResponseEntity.ok(new ResponseWrapper("Project is successfully updated", HttpStatus.OK));
    }

    @PutMapping("/manager/complete/{projectCode}")
    @RolesAllowed({"Manager"})
    public ResponseEntity<ResponseWrapper> complete(@PathVariable("projectCode") String projectCode) {

        projectService.complete(projectCode);

        return ResponseEntity.ok(new ResponseWrapper("Project is successfully completed", HttpStatus.OK));
    }

    @DeleteMapping("/{projectCode}")
    @RolesAllowed({"Manager"})
    public ResponseEntity<ResponseWrapper> delete(@PathVariable("projectCode") String projectCode) {

        projectService.delete(projectCode);

        return ResponseEntity.ok(new ResponseWrapper("Project is successfully deleted", HttpStatus.OK));
    }

    @GetMapping("/manager/project-status")
    @RolesAllowed({"Manager"})
    public ResponseEntity<ResponseWrapper> getProjectByManager() {

        return ResponseEntity.ok(new ResponseWrapper("Projects are successfully retrieved", projectService.listAllProjectDetails(), HttpStatus.OK));
    }

}
