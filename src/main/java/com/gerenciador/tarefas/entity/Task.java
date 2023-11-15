package com.gerenciador.tarefas.entity;

import com.gerenciador.tarefas.status.TaskStatusEnum;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalTime;

@Entity
@Table(name = "tasks")
@Data
@Getter
@Setter
@Builder
public class Task implements Serializable {

    private static final long serialVersionUID = 1L;

    public Task(){

    }

    public Task(Long id, String title, String description, TaskStatusEnum status, User responsible, User creator, int estimatedTimeAmount, Integer workedTimeAmount, LocalTime registerDate, LocalTime updateDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.responsible = responsible;
        this.creator = creator;
        this.estimatedTimeAmount = estimatedTimeAmount;
        this.workedTimeAmount = workedTimeAmount;
        this.registerDate = registerDate;
        this.updateDate = updateDate;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TaskStatusEnum status;

    @Column
    private User responsible;

    @Column(nullable = false)
    private User creator;

    @Column(nullable = false)
    private int estimatedTimeAmount;

    @Column
    private Integer workedTimeAmount;

    @Column
    @CreationTimestamp
    private LocalTime registerDate;

    @Column
    @UpdateTimestamp
    private LocalTime updateDate;
}
