package com.gerenciador.tarefas.service;

import com.gerenciador.tarefas.entity.Task;
import com.gerenciador.tarefas.repositories.ITaskManagerRepository;
import com.gerenciador.tarefas.request.RegisterTaskRequest;
import com.gerenciador.tarefas.request.UpdateTaskRequest;
import com.gerenciador.tarefas.status.TaskStatusEnum;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class TaskManagerService {

    @Autowired
    private ITaskManagerRepository iTaskManagerRepository;

    @Autowired
    private UserService userService;

    public Task addTask(RegisterTaskRequest request){

        Task task = Task.builder()
                .estimatedTimeAmount(request.getEstimatedTimeAmount())
                .status(TaskStatusEnum.CREATED)
                .title(request.getTitle())
                .description(request.getDescription())
                .creator(userService.findById(request.getCreatorId()).get())
                .build();

       return this.iTaskManagerRepository.save(task);

    }

    public Page<Task> getTaskByTitle(String title, Pageable pageable){
        return this.iTaskManagerRepository.findByTitleContaining(title, pageable);
    }

    public Page<Task> getAllTasks(Pageable pageable){
        return this.iTaskManagerRepository.findAll(pageable);
    }

    public Task updateTask(Long id, UpdateTaskRequest request){

        Task task = this.iTaskManagerRepository.findById(id).get();

        task.setEstimatedTimeAmount(request.getEstimatedTimeAmount());
        task.setStatus(request.getStatus());
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setResponsible(userService.findById(request.getResponsibleId()).get());
        task.setWorkedTimeAmount(request.getWorkedTimeAmount());

        this.iTaskManagerRepository.save(task);
        return task;
    }


    public void removeTask (Long taskId){
        this.iTaskManagerRepository.deleteById(taskId);
    }

}
