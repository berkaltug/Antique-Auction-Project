package com.scopic.antiqueauction.controller;

import com.scopic.antiqueauction.domain.converter.UserConverter;
import com.scopic.antiqueauction.domain.request.RegistrationRequest;
import com.scopic.antiqueauction.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid RegistrationRequest request){
        userService.save(UserConverter.convert(request));
        return new ResponseEntity<String>("Registration is successful",HttpStatus.ACCEPTED);
    }
}
