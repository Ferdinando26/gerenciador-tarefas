package com.gerenciador.tarefas.response;

import com.gerenciador.tarefas.status.TaskStatusEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@Builder
public class GetTaskResponse {

    private Long id;
    private String title;
    private String description;
    private TaskStatusEnum status;
    private String responsible;
    private String creator;
    private int estimatedTimeAmount;
    private Integer workedTimeAmount;
    private LocalTime registerDate;
    private LocalTime updateDate;

}
