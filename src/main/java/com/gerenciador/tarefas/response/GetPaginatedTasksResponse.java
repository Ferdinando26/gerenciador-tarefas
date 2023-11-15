package com.gerenciador.tarefas.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class GetPaginatedTasksResponse {

    private int currentPage;
    private Long totalItems;
    private int totalPages;
    private List<GetTaskResponse> tasks;
}
