package com.gerenciador.tarefas;

import com.gerenciador.tarefas.entity.Role;
import com.gerenciador.tarefas.entity.User;
import com.gerenciador.tarefas.permissions.PermissionEnum;
import com.gerenciador.tarefas.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class GerenciadorTarefasApplication implements CommandLineRunner {

	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(GerenciadorTarefasApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		User user = new User();
		user.setUsername("admin");
		user.setPassword("123456");

		List<Role> roles = new ArrayList<>();

		Role adminRole = new Role();
		adminRole.setName(PermissionEnum.ADMIN);

		Role userRole = new Role();
		userRole.setName(PermissionEnum.USER);

		roles.add(adminRole);
		roles.add(userRole);

		user.setRoles(roles);

		userService.addUser(user);


	}
}
