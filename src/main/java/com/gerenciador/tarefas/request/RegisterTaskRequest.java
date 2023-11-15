package com.gerenciador.tarefas.request;

import com.gerenciador.tarefas.status.TaskStatusEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterTaskRequest {


    private String title;

    private String description;

    private Long creatorId;

    private int estimatedTimeAmount;

}
