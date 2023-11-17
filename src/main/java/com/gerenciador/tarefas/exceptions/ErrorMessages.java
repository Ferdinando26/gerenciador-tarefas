package com.gerenciador.tarefas.exceptions;

public class ErrorMessages {


    // REGISTER TASK ERRORS
    public static final String REGISTER_TITLE_REQUIRED = "{register.task.request.title.required}";

    public static final String REGISTER_CREATOR_REQUIRED = "{register.task.request.creator.required}";

    public static final String REGISTER_TOO_LARGE_DESCRIPTION = "{register.task.request.description.limit}";

    public static final String REGISTER_ESTIMATED_TIME_REQUIRED = "{register.task.request.estimatedTimeAmount.required}";


    // UPDATE TASK ERRORS

    public static final String UPDATE_TITLE_REQUIRED = "{update.task.request.title.required}";

    public static final String UPDATE_TOO_LARGE_DESCRIPTION = "{update.task.request.description.limit}";

    public static final String UPDATE_ESTIMATED_TIME_REQUIRED = "{update.task.request.estimatedTimeAmount.required}";


    // STATUS ERRORS

    public static final String CHANGE_FINISHED_STATUS_NOT_ALLOWED_EXCEPTION = "It is not allowed to change the task status if it is as FINISHED";

    public static final String CHANGE_CREATED_TO_FINISHED_STATUS_NOT_ALLOWED_EXCEPTION = "It is not allowed to change the task status to FINISHED if it is as CREATED";

    public static final String CHANGE_BLOCKED_TO_FINISHED_STATUS_NOT_ALLOWED_EXCEPTION = "It is not allowed to change the task status to FINISHED if it is as BLOCKED";


    // EXISTING TASK

    public static final String DESCRIPTION_AND_TITLE_ALREADY_EXISTS = "Already exists a task with the same title and description";

}
