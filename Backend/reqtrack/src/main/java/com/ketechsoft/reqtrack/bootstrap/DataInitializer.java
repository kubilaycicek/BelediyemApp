package com.ketechsoft.reqtrack.bootstrap;

import com.ketechsoft.reqtrack.dtos.*;
import com.ketechsoft.reqtrack.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class DataInitializer implements CommandLineRunner {

    private final UserTypeService userTypeService;
    private final UserService userService;

    private void loadData() {
        userTypeService.addUserType(new UserTypeDto("USER",
                "Standart Kullanici"));

        userService.register(new RegistrationDto("korteks",
                "korteks",
                "1",
                "korteks"));
    }

    @Override
    public void run(String... args) throws Exception {
        loadData();
    }
}
