package com.javarush.todolist.service.impl;

import com.javarush.todolist.dao.TaskDAO;
import com.javarush.todolist.domain.Status;
import com.javarush.todolist.domain.Task;
import com.javarush.todolist.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskDAO taskDAO;

    public TaskServiceImpl(TaskDAO taskDAO) {
        this.taskDAO = taskDAO;
    }

    @Override
    public void create(String description, Status status) {
        Task task = new Task();
        task.setDescription(description);
        task.setStatus(status);
        taskDAO.create(task);
    }

    @Override
    public void edit(int id, String description, Status status) {
        Task task = taskDAO.getById(id);
        task.setDescription(description);
        task.setStatus(status);
        taskDAO.update(task);
    }

    @Override
    public void delete(int id) {
        Task task = taskDAO.getById(id);
        taskDAO.delete(task);
    }

    @Override
    public List<Task> getAll(int offset, int count) {
        return taskDAO.getAll(offset, count);
    }

    @Override
    public int getCount() {
        return taskDAO.getCount();
    }
}
