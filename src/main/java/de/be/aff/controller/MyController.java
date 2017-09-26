package de.be.aff.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    //get request example http://localhost:8080/profile/1
    @GetMapping("/{id}")
    public String get(@PathVariable int id)
    {//IllegalArgumentException will be thrown even if id is not integer

        if(id<0)
            throw new IllegalArgumentException("lower than zero not allowed");
        else
            return id+"";
    }
}
