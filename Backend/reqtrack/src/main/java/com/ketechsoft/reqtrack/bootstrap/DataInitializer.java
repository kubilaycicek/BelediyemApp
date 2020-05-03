package com.ketechsoft.reqtrack.bootstrap;


import com.ketechsoft.reqtrack.dtos.*;
import com.ketechsoft.reqtrack.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class DataInitializer implements CommandLineRunner {

    private final CategoryService categoryService;
    private final DepartmentService departmentService;
    private final UserTypeService userTypeService;
    private final ComplaintStatusService complaintStatusService;
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
    }

    @Override
    public void run(String... args) throws Exception {
        loadData();
    }
}
