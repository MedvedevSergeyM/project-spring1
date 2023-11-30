package com.javarush.todolist.controller;

import com.javarush.todolist.domain.Status;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TaskInfo {
    String description;
    Status status;
}
