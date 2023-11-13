package com.gerenciador.tarefas.repositories;

import com.gerenciador.tarefas.entity.Role;
import com.gerenciador.tarefas.permissions.PermissionEnum;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleRepository extends JpaRepository<Role, Long> {

    Role findByName (PermissionEnum name);

}
