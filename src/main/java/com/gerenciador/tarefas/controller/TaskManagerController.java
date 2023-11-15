package com.gerenciador.tarefas.controller;


import com.gerenciador.tarefas.entity.Task;
import com.gerenciador.tarefas.request.RegisterTaskRequest;
import com.gerenciador.tarefas.request.UpdateTaskRequest;
import com.gerenciador.tarefas.response.GetPaginatedTasksResponse;
import com.gerenciador.tarefas.response.GetTaskResponse;
import com.gerenciador.tarefas.response.RegisterTaskResponse;
import com.gerenciador.tarefas.response.UpdateTaskResponse;
import com.gerenciador.tarefas.service.TaskManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task-manager")
public class TaskManagerController {

    @Autowired
    private TaskManagerService taskManagerService;

    @PostMapping
    public ResponseEntity<RegisterTaskResponse> addTask(@RequestBody RegisterTaskRequest request) {
        Task addedTask = taskManagerService.addTask(request);

        RegisterTaskResponse response = RegisterTaskResponse
                .builder()
                .id(addedTask.getId())
                .title(addedTask.getTitle())
                .description(addedTask.getDescription())
                .creator(addedTask.getCreator().getUsername())
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<GetPaginatedTasksResponse> getTasks(
            @RequestParam (required = false)String title,
            @RequestParam (defaultValue = "0")int page,
            @RequestParam (defaultValue = "3")int size){

        Page<Task> paginatedTasks;

        if (title == null){
            paginatedTasks = this.taskManagerService.getAllTasks(PageRequest.of(page, size));
        } else {
            paginatedTasks = this.taskManagerService.getTaskByTitle(title, PageRequest.of(page, size));
        }

        List<GetTaskResponse> tasks = paginatedTasks
                .getContent()
                .stream()
                .map(task -> {
                    return GetTaskResponse
                            .builder()
                            .id(task.getId())
                            .title(task.getTitle())
                            .description(task.getDescription())
                            .responsible(task.getResponsible() !=null ? task.getResponsible().getUsername() : "UNASSIGNED")
                            .creator(task.getCreator().getUsername())
                            .status(task.getStatus())
                            .estimatedTimeAmount(task.getEstimatedTimeAmount())
                            .workedTimeAmount(task.getWorkedTimeAmount())
                            .registerDate(task.getRegisterDate())
                            .updateDate(task.getUpdateDate())
                            .build();
                })
                .toList();

        GetPaginatedTasksResponse response = GetPaginatedTasksResponse
                .builder()
                .currentPage(paginatedTasks.getNumber())
                .totalPages(paginatedTasks.getTotalPages())
                .totalItems(paginatedTasks.getTotalElements())

                .tasks(tasks)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public void removeTask(@PathVariable Long id){

        taskManagerService.removeTask(id);

    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UpdateTaskResponse> updateTask(@PathVariable Long id, @RequestBody UpdateTaskRequest request) {
        Task updatedTask = taskManagerService.updateTask(id, request);

        UpdateTaskResponse response = UpdateTaskResponse
                .builder()
                .id(updatedTask.getId())
                .title(updatedTask.getTitle())
                .description(updatedTask.getDescription())
                .creator(updatedTask.getCreator().getUsername())
                .estimatedTimeAmount(updatedTask.getEstimatedTimeAmount())
                .workedTimeAmount(updatedTask.getWorkedTimeAmount())
                .responsible(updatedTask.getResponsible().getUsername())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
