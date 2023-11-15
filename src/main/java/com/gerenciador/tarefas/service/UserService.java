package com.gerenciador.tarefas.service;

import com.gerenciador.tarefas.entity.User;
import com.gerenciador.tarefas.repositories.IRoleRepository;
import com.gerenciador.tarefas.repositories.IUserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    @Autowired
    private IUserRepository iUserRepository;

    @Autowired
    private IRoleRepository iRoleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User addUser(User user) {

        user.setRoles(user.getRoles()
                .stream()
                .map(role -> iRoleRepository.findByName(role.getName()))
                .toList());

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return this.iUserRepository.save(user);
    }

    public User updateUser(User user) {

        user.setRoles(user.getRoles()
                .stream()
                .map(role -> iRoleRepository.findByName(role.getName()))
                .toList());

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return this.iUserRepository.save(user);
    }

    public void removeUser(User user) {
        this.iUserRepository.deleteById(user.getId());
    }

    public List<User> getAllUsers() {
        return this.iUserRepository.findAll();
    }


    public Optional<User> findById(Long userId){

        return this.iUserRepository.findById(userId);

    }

}
