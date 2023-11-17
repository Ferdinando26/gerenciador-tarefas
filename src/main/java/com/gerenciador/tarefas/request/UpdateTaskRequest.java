package com.gerenciador.tarefas.request;

import com.gerenciador.tarefas.status.TaskStatusEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import static com.gerenciador.tarefas.exceptions.ErrorMessages.UPDATE_ESTIMATED_TIME_REQUIRED;
import static com.gerenciador.tarefas.exceptions.ErrorMessages.UPDATE_TITLE_REQUIRED;
import static com.gerenciador.tarefas.exceptions.ErrorMessages.UPDATE_TOO_LARGE_DESCRIPTION;

@Getter
@Setter
public class UpdateTaskRequest {

    @NotBlank(message = UPDATE_TITLE_REQUIRED)
    private String title;

    @Length(max = 150, message = UPDATE_TOO_LARGE_DESCRIPTION)
    private String description;

    private TaskStatusEnum status;

    private Long responsibleId;

    @NotNull(message = UPDATE_ESTIMATED_TIME_REQUIRED)
    private Integer estimatedTimeAmount;

    private Integer workedTimeAmount;
}
