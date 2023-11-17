package com.gerenciador.tarefas.service;

import com.gerenciador.tarefas.entity.Task;
import com.gerenciador.tarefas.exceptions.ExistingTaskException;
import com.gerenciador.tarefas.exceptions.NotAllowedToChangeStatusException;
import com.gerenciador.tarefas.exceptions.NotAllowedToExcludeException;
import com.gerenciador.tarefas.repositories.ITaskManagerRepository;
import com.gerenciador.tarefas.request.RegisterTaskRequest;
import com.gerenciador.tarefas.request.UpdateTaskRequest;
import com.gerenciador.tarefas.status.TaskStatusEnum;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static com.gerenciador.tarefas.exceptions.ErrorMessages.CHANGE_BLOCKED_TO_FINISHED_STATUS_NOT_ALLOWED_EXCEPTION;
import static com.gerenciador.tarefas.exceptions.ErrorMessages.CHANGE_CREATED_TO_FINISHED_STATUS_NOT_ALLOWED_EXCEPTION;
import static com.gerenciador.tarefas.exceptions.ErrorMessages.CHANGE_FINISHED_STATUS_NOT_ALLOWED_EXCEPTION;
import static com.gerenciador.tarefas.exceptions.ErrorMessages.DESCRIPTION_AND_TITLE_ALREADY_EXISTS;

@Service
@Transactional
public class TaskManagerService {

    @Autowired
    private ITaskManagerRepository iTaskManagerRepository;

    @Autowired
    private UserService userService;

    public Task addTask(RegisterTaskRequest request){

        Task taskValidation = iTaskManagerRepository.findByTitleOrDescription(request.getTitle(), request.getDescription());

        if (taskValidation != null){
            throw new ExistingTaskException(DESCRIPTION_AND_TITLE_ALREADY_EXISTS);
        }

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
        return this.iTaskManagerRepository.findByTitleContainingOrderByUpdateDateDesc(title, pageable);
    }

    public Page<Task> getAllTasks(Pageable pageable){
        return this.iTaskManagerRepository.findAllByOrderByUpdateDateDesc(pageable);
    }

    public Task updateTask(Long id, UpdateTaskRequest request){


        Task task = this.iTaskManagerRepository.findById(id).get();

        if (task.getStatus().equals(TaskStatusEnum.FINISHED))
            throw new NotAllowedToChangeStatusException(CHANGE_FINISHED_STATUS_NOT_ALLOWED_EXCEPTION);

        if (task.getStatus().equals(TaskStatusEnum.CREATED) && request.getStatus().equals(TaskStatusEnum.FINISHED))
            throw new NotAllowedToChangeStatusException(CHANGE_CREATED_TO_FINISHED_STATUS_NOT_ALLOWED_EXCEPTION);

        if (task.getStatus().equals(TaskStatusEnum.BLOCKED) && request.getStatus().equals(TaskStatusEnum.FINISHED))
            throw new NotAllowedToChangeStatusException(CHANGE_BLOCKED_TO_FINISHED_STATUS_NOT_ALLOWED_EXCEPTION);

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

        Task task = this.iTaskManagerRepository.findById(taskId).get();


        if (!TaskStatusEnum.CREATED.equals(task.getStatus())){
            throw new NotAllowedToExcludeException();
        }

        this.iTaskManagerRepository.deleteById(taskId);
    }

}
