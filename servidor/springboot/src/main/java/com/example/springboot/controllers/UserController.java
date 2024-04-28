package com.example.springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.repositories.UserRepository;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

}
