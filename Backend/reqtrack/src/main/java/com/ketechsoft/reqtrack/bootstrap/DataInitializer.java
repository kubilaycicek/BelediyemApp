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

        RegistrationDto registrationDto =new RegistrationDto();
        registrationDto.setEmail("korteks");
        registrationDto.setNameSurname("korteks");
        registrationDto.setPassword("1");
        registrationDto.setUsername("korteks");
        registrationDto.setTcNumber("korteks");

        userService.register(registrationDto);
    }

    @Override
    public void run(String... args) throws Exception {
        loadData();
    }
}
