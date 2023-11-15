package com.gerenciador.tarefas.request;

import com.gerenciador.tarefas.entity.User;
import com.gerenciador.tarefas.status.TaskStatusEnum;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
public class UpdateTaskRequest {


    private String title;
    private String description;
    private TaskStatusEnum status;
    private Long responsibleId;
    private int estimatedTimeAmount;
    private Integer workedTimeAmount;


}
