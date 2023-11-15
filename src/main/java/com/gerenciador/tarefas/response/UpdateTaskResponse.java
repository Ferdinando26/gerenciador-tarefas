package com.gerenciador.tarefas.response;

import com.gerenciador.tarefas.status.TaskStatusEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UpdateTaskResponse {


    private Long id;
    private String title;
    private String description;
    private String creator;
    private String status;
    private String responsible;
    private int estimatedTimeAmount;
    private Integer workedTimeAmount;

}
