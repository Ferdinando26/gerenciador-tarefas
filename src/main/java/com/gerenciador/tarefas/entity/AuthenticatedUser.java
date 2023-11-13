package com.gerenciador.tarefas.entity;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticatedUser {

    private String username;
    private String password;

}
