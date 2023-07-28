package com.cydeo.controller;

import com.cydeo.dto.ResponseWrapper;
import com.cydeo.dto.TaskDTO;
import com.cydeo.enums.Status;
import com.cydeo.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/api/v1/task")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }


    @GetMapping
    @RolesAllowed({"Manager"})
    public ResponseEntity<ResponseWrapper> listAll() {

        return ResponseEntity.ok(new ResponseWrapper("Tasks are successfully retrieved", taskService.listAllTasks(), HttpStatus.OK));
    }

    @GetMapping("/{id}")
    @RolesAllowed({"Manager"})
    public ResponseEntity<ResponseWrapper> findById(@PathVariable Long id) {

        return ResponseEntity.ok(new ResponseWrapper("Task is successfully retrieved", taskService.findById(id), HttpStatus.OK));
    }

    @PostMapping
    @RolesAllowed({"Manager"})
    public ResponseEntity<ResponseWrapper> create(@RequestBody TaskDTO taskDTO) {

        taskService.save(taskDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseWrapper("Task is successfully created", HttpStatus.CREATED));
    }

    @PutMapping
    @RolesAllowed({"Manager"})
    public ResponseEntity<ResponseWrapper> update(@RequestBody TaskDTO taskDTO) {

        taskService.update(taskDTO);

        return ResponseEntity.ok(new ResponseWrapper("Task is successfully updated", HttpStatus.OK));
    }

    @DeleteMapping("/{id}")
    @RolesAllowed({"Manager"})
    public ResponseEntity<ResponseWrapper> delete(@PathVariable Long id) {

        taskService.delete(id);

        return ResponseEntity.ok(new ResponseWrapper("Task is successfully deleted", HttpStatus.OK));
    }

    @GetMapping("/employee/pending-tasks")
    @RolesAllowed({"Employee"})
    public ResponseEntity<ResponseWrapper> employeePendingTasks() {

        return ResponseEntity.ok(new ResponseWrapper("Tasks are successfully retrieved", taskService.listAllTasksByStatusIsNot(Status.COMPLETE), HttpStatus.OK));
    }

    @GetMapping("/employee/update")
    @RolesAllowed({"Employee"})
    public ResponseEntity<ResponseWrapper> employeeUpdateTasks(@RequestBody TaskDTO taskDTO) {

        taskService.update(taskDTO);

        return ResponseEntity.ok(new ResponseWrapper("Tasks are successfully retrieved", HttpStatus.OK));
    }

    @GetMapping("/employee/archived-tasks")
    @RolesAllowed({"Employee"})
    public ResponseEntity<ResponseWrapper> employeeArchivedTasks() {

        return ResponseEntity.ok(new ResponseWrapper("Tasks are successfully retrieved", taskService.listAllTasksByStatus(Status.COMPLETE), HttpStatus.OK));
    }

}
