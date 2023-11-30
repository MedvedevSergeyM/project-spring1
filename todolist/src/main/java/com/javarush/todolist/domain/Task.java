package com.javarush.todolist.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

@Data
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(schema = "todo", name = "task")
public class Task extends BaseEntity {

    @Column(name = "description")
    String description;

    @Column(name = "status", columnDefinition = "int")
    @Enumerated(EnumType.ORDINAL)
    Status status;
}
