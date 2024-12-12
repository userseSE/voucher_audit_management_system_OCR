package com.design.spot_check_voucher.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShutdownController {

    @Autowired
    private ApplicationContext applicationContext;

    @PostMapping("/shutdown")
    public ResponseEntity<String> shutdown() {
        ConfigurableApplicationContext ctx = (ConfigurableApplicationContext) applicationContext;
        ctx.close();
        return new ResponseEntity<>("网站已关闭", HttpStatus.OK);
    }
}

