package com.cydeo.controller;

import com.cydeo.dto.TaskDTO;
import com.cydeo.enums.Status;
import com.cydeo.service.ProjectService;
import com.cydeo.service.TaskService;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;

@Controller
@RequestMapping("/task")
public class TaskController {

    UserService userService;
    TaskService taskService;
    ProjectService projectService;

    public TaskController(UserService userService, TaskService taskService, ProjectService projectService) {
        this.userService = userService;
        this.taskService = taskService;
        this.projectService = projectService;
    }

    @GetMapping("/create")
    public String createTask(Model model) {
        model.addAttribute("projects", projectService.findAll());
        model.addAttribute("emoloyees", userService.findEmployees());
        model.addAttribute("tasks", taskService.findAll());
        model.addAttribute("task", new TaskDTO());
        return "/task/create";
    }
    @PostMapping("/create")
    public String addNewTask(TaskDTO task){
        taskService.save(task);
        return "redirect:/task/create";
    }
    @GetMapping("/update/{id}")
    public String updateTask(@PathVariable("id") Long id, Model model){
        model.addAttribute("projects", projectService.findAll());
        model.addAttribute("emoloyees", userService.findEmployees());
        model.addAttribute("task", taskService.findById(id));
        return "/task/update";
    }
    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable("id") Long id){
        taskService.deleteById(id);
        return "redirect:/task/create";
    }

     @GetMapping("/pending-tasks")
    public String pendingTask(Model model){
        model.addAttribute("tasks", taskService.findAllTasksByStatusIsNot(Status.COMPLETE));
        return "/task/pending-tasks";
    }
    @GetMapping("/status-update/{id}")
    public String updateTaskForm(@PathVariable("id") Long id, Model model){
        model.addAttribute("task", taskService.findById(id));
        model.addAttribute("statuses", Arrays.asList(Status.values()));
        return ("/task/status-update");
    }
    @PostMapping("/status-update/{id}")
    public String updateTaskForm1(@PathVariable("id") Long id, TaskDTO task){
//        taskService.findById(id).setStatus(task.getStatus());
        taskService.updateTaskStatus(task);
        return ("redirect:/task/pending-tasks");
    }

    @GetMapping("/archive")
    public String archiveTasks(Model model){
        model.addAttribute("tasks", taskService.findAllTasksByStatus(Status.COMPLETE));
        return "/task/archive";}

}
