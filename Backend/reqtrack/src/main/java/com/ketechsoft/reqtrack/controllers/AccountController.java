package com.ketechsoft.reqtrack.controllers;

import com.ketechsoft.reqtrack.dtos.RegistrationResultDto;
import com.ketechsoft.reqtrack.models.User;
import com.ketechsoft.reqtrack.repositories.UserRepository;
import com.ketechsoft.reqtrack.dtos.LoginDto;
import com.ketechsoft.reqtrack.dtos.RegistrationDto;
import com.ketechsoft.reqtrack.dtos.TokenDto;
import com.ketechsoft.reqtrack.security.JwtTokenUtil;
import com.ketechsoft.reqtrack.services.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/token")
public class AccountController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserRepository userRepository;
    private final UserServiceImpl userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<TokenDto> login(@RequestBody LoginDto request) throws AuthenticationException {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        final User user = userRepository.findByTcNumber(request.getUsername());
        final String token = jwtTokenUtil.generateToken(user);
        final long id = user.getId();
        return ResponseEntity.ok(new TokenDto(id,user.getTcNumber(), token));
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<RegistrationResultDto> register(@RequestBody RegistrationDto registrationDto) throws AuthenticationException {
        return ResponseEntity.ok(userService.register(registrationDto));
    }
}
