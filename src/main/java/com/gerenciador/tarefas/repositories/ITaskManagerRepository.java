package com.gerenciador.tarefas.repositories;

import com.gerenciador.tarefas.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITaskManagerRepository extends JpaRepository<Task, Long> {

    Task findByTitleOrDescription(String title, String description);

    Page<Task> findByTitleContainingOrderByUpdateDateDesc(String title, Pageable pageable);

    Page<Task> findAllByOrderByUpdateDateDesc(Pageable pageable);
}
