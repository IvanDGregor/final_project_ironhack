package com.ironhack.edgeservice.controller;

import com.ironhack.edgeservice.service.AccountService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "Lead Controller")
@RestController
@RequestMapping("/")
public class AccountController {

    @Autowired
    private AccountService accountService;


}
