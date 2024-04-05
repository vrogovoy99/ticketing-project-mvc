package com.cydeo.service.impl;

import com.cydeo.dto.TaskDTO;
import com.cydeo.enums.Status;
import com.cydeo.service.TaskService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TaskServiceImpl extends AbstractMapService<TaskDTO,Long> implements TaskService {
    @Override
    public TaskDTO save(TaskDTO object) {


        if (object.getId()==null){
            if(super.findAll().isEmpty()){
                object.setId(1L);
            }
            else{
                object.setId(super.findAll().stream().map(TaskDTO::getId).max(Long::compare).get() + 1);
            }
            object.setStatus(Status.OPEN);
            object.setAssignedDate(LocalDate.now());
        }
        else {
            if (object.getStatus()==null){
                object.setStatus(super.findById(object.getId()).getStatus());
            }
            if (object.getAssignedDate()==null){
                object.setAssignedDate(super.findById(object.getId()).getAssignedDate());
            }
        }

        return super.save(object.getId(), object);
    }

    @Override
    public TaskDTO findById(Long id) {
        return super.findById(id);
    }

    @Override
    public List<TaskDTO> findAll() {
        return super.findAll();
    }

    @Override
    public void update(TaskDTO object) {
        super.update(object.getId(), object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}
