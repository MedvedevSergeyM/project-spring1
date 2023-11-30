package com.javarush.todolist.service;

import com.javarush.todolist.domain.Status;
import com.javarush.todolist.domain.Task;

import java.util.List;

public interface TaskService {
    void create(String description, Status status);
    void edit(int id, String description, Status status);
    void delete(int id);
    List<Task> getAll(int offset, int count);
    int getCount();
}
