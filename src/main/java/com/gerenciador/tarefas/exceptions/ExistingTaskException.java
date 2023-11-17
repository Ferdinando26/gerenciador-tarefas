package com.gerenciador.tarefas.exceptions;

public class ExistingTaskException extends RuntimeException{

    public ExistingTaskException(){
        super();
    }

    public ExistingTaskException(String message){
        super(message);
    }
}
