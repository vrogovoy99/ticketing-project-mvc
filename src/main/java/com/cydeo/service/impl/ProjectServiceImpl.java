package com.cydeo.service.impl;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.UserDTO;
import com.cydeo.enums.Status;
import com.cydeo.service.ProjectService;
import com.cydeo.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ProjectServiceImpl extends AbstractMapService<ProjectDTO, String> implements ProjectService {

    TaskService taskService;

    public ProjectServiceImpl(TaskService taskService) {
        this.taskService = taskService;
    }

    @Override
    public ProjectDTO save(ProjectDTO object) {
        if (object.getProjectStatus()==null) {
            object.setProjectStatus(Status.OPEN);
        }
        return super.save(object.getProjectCode(),object);
    }

    @Override
    public ProjectDTO findById(String id) {
        return super.findById(id);
    }

    @Override
    public List<ProjectDTO> findAll() {
        return super.findAll();
    }

    @Override
    public void update(ProjectDTO object) {
//        instead of getting status from repository - use input type hidden on the form to pass it via post request.
//        if(object.getProjectStatus()==null){
//            object.setProjectStatus(super.findById(object.getProjectCode()).getProjectStatus());
//        }

        super.update(object.getProjectCode(), object);
    }

    @Override
    public void deleteById(String id) {
        super.deleteById(id);
    }

    @Override
    public void complete(ProjectDTO project) {
        project.setProjectStatus(Status.COMPLETE);
        super.save(project.getProjectCode(), project);
    }

    @Override
    public List<ProjectDTO> getCountedListOfProjectDTO(UserDTO manager) {

        List<ProjectDTO> projectList = findAll().stream()
                .filter(p->p.getAssignedManager().equals(manager))
                .map(project -> {

                    int completedTasks = (int) taskService.findAll().stream()
                            .filter(task -> task.getProject().equals(project) && task.getStatus().equals(Status.COMPLETE))
                            .count();

                    int unfinishedTasks  = (int) taskService.findAll().stream()
                            .filter(task -> task.getProject().equals(project) && task.getStatus() != Status.COMPLETE)
                            .count();

                    project.setCompleteTaskCounts(completedTasks);
                    project.setUnfinishedTaskCounts(unfinishedTasks);
                    return project;
                })
                .collect(Collectors.toList());

        return projectList;
    }

}
