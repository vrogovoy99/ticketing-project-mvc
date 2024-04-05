package com.cydeo.controller;

import com.cydeo.dto.TaskDTO;
import com.cydeo.service.ProjectService;
import com.cydeo.service.TaskService;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String pendingTask(){
        return "/task/pending-tasks";
    }

}
