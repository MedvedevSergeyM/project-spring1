package com.javarush.todolist.controller;

import com.javarush.todolist.domain.Task;
import com.javarush.todolist.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import lombok.extern.log4j.Log4j2;

import java.util.List;

@Log4j2
@Controller
@RequestMapping("/")
public class TasksController {

    private static final int LIMIT = 5;

    private final TaskService taskService;

    public TasksController(TaskService taskService) {
        this.taskService = taskService;
    }

    @RequestMapping(path = "/", method = RequestMethod.POST, consumes={"application/json"})
    public void create(Model model, @RequestBody TaskInfo info) {
        log.info("CREATE TaskInfo = " + info.getDescription() + ", " + info.getStatus());
        taskService.create(info.getDescription(), info.getStatus());
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.POST)
    public void edit(Model model, @PathVariable Integer id, @RequestBody TaskInfo info) {
        taskService.edit(id, info.getDescription(), info.getStatus());
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void delete(Model model, @PathVariable Integer id) {
        taskService.delete(id);
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String get(Model model,
                      @RequestParam(value = "page", required = false, defaultValue = "1") int page) {
        int totalPages = (int) Math.ceil((double)taskService.getCount() / LIMIT);
        List<Task> tasks = taskService.getAll((page - 1) * LIMIT, LIMIT);
        model.addAttribute("tasks", tasks);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        return "tasks";
    }
}
