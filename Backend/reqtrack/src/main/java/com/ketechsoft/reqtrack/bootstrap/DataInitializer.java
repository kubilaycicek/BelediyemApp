package com.ketechsoft.reqtrack.bootstrap;


import com.ketechsoft.reqtrack.dtos.*;
import com.ketechsoft.reqtrack.models.Category;
import com.ketechsoft.reqtrack.models.ComplaintStatus;
import com.ketechsoft.reqtrack.models.Department;
import com.ketechsoft.reqtrack.repositories.CategoryRepository;
import com.ketechsoft.reqtrack.repositories.ComplaintStatusRepository;
import com.ketechsoft.reqtrack.repositories.DepartmentRepository;
import com.ketechsoft.reqtrack.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class DataInitializer implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final DepartmentRepository departmentRepository;
    private final UserTypeService userTypeService;
    private final ComplaintStatusRepository complaintStatusRepository;
    private final UserService userService;

    private void loadData() {
        userTypeService.addUserType(new UserTypeDto("USER",
                "Standart Kullanici"));

        UserDto rgsDto =new UserDto();
        rgsDto.setName("korteks");
        rgsDto.setSurname("korteks");
        rgsDto.setTcNumber("1");
        rgsDto.setPassword("1");
        rgsDto.setEmail("korteks");
        rgsDto.setPhone("5544062486");
        rgsDto.setUserTypeDto(userTypeService.getById(1));

        userService.addUser(rgsDto);

        Department department = new Department();
        department.setName("Department-1");
        department.setDescription("Test");
        departmentRepository.save(department);

        Category category =new Category();
        category.setName("Category -1 ");
        category.setDescription("Test");
        category.setDepartment(department);
        categoryRepository.save(category);

        ComplaintStatus complaintStatus = new ComplaintStatus();
        complaintStatus.setName("Status - 1");
        complaintStatus.setDescription("Test");
        complaintStatusRepository.save(complaintStatus);




    }

    @Override
    public void run(String... args) throws Exception {
        loadData();
    }
}
