package com.javarush.todolist.dao;

import com.javarush.todolist.domain.Task;

import java.util.List;

public interface TaskDAO {

    void create(Task task);

    void update(Task task);

    void delete(Task task);

    Task getById(int id);

    List<Task> getAll(int offset, int count);

    int getCount();
}
