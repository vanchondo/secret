package com.vanchondo.secret;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@EnableEncryptableProperties
@ServletComponentScan
public class Application {
    
    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
    }
}
