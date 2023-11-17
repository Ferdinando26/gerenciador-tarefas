package com.gerenciador.tarefas.exceptions;

public class NotAllowedToChangeStatusException extends RuntimeException{

    public NotAllowedToChangeStatusException(){
        super();
    }

    public NotAllowedToChangeStatusException(String message){
        super(message);
    }

}
