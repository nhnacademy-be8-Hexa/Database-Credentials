package com.nhnacademy.screattest.controller;

import com.nhnacademy.screattest.service.NativeQueryDatabaseService;
import com.nhnacademy.screattest.service.SecureKeyManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

    @Autowired
    private final SecureKeyManagerService secureKeyManagerService;


    @Autowired
    private final NativeQueryDatabaseService nativeQueryDatabaseService;

    public TestController(SecureKeyManagerService secureKeyManagerService, NativeQueryDatabaseService nativeQueryDatabaseService) {
        this.secureKeyManagerService = secureKeyManagerService;
        this.nativeQueryDatabaseService = nativeQueryDatabaseService;
    }

    @GetMapping("/hello")
    @ResponseBody
    public String test(){

        return secureKeyManagerService.fetchSecretFromKeyManager();

    }

    @GetMapping("/hello2")
    @ResponseBody
    public String test2(){

        return nativeQueryDatabaseService.getAllTables().toString();

    }
}
