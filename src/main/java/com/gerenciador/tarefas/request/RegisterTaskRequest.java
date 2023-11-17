package com.gerenciador.tarefas.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import static com.gerenciador.tarefas.exceptions.ErrorMessages.REGISTER_CREATOR_REQUIRED;
import static com.gerenciador.tarefas.exceptions.ErrorMessages.REGISTER_ESTIMATED_TIME_REQUIRED;
import static com.gerenciador.tarefas.exceptions.ErrorMessages.REGISTER_TITLE_REQUIRED;
import static com.gerenciador.tarefas.exceptions.ErrorMessages.REGISTER_TOO_LARGE_DESCRIPTION;

@Getter
@Setter
public class RegisterTaskRequest {




    @NotBlank(message = REGISTER_TITLE_REQUIRED)
    private String title;

    @Length(max = 150, message = REGISTER_TOO_LARGE_DESCRIPTION)
    private String description;

    @NotNull(message = REGISTER_CREATOR_REQUIRED)
    private Long creatorId;

    @NotNull(message = REGISTER_ESTIMATED_TIME_REQUIRED)
    private Integer estimatedTimeAmount;

}
