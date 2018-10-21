package com.foliage.security.common;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class Test{
	
	String name = "[foliage-security] - TEST successful completed";
	
    @RequestMapping("/test")
    public String greeting() {
    	System.out.println("called " + this.name);
        return this.name;
    }
}