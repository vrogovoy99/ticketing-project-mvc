package com.cydeo.bootstrap;

import com.cydeo.dto.RoleDTO;
import com.cydeo.service.RoleService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataGenerator implements CommandLineRunner {

    RoleService roleService; //use interface rather than a class to define variable. Classes can change over time.

    public DataGenerator(RoleService roleService) { //since Component RoleServiceImpl is created, spring will plug it in for RoleService interface - loose coupling!!!!
        this.roleService = roleService;
    }

    @Override
    public void run(String... args) throws Exception {
        RoleDTO adminRole = new RoleDTO(1L, "Admin");
        RoleDTO managerRole = new RoleDTO(2L, "Manager");
        RoleDTO employeeRole = new RoleDTO(3L, "Employee");

        //add data to role map
        roleService.save(adminRole);
        roleService.save(managerRole);
        roleService.save(employeeRole);




    }
}
