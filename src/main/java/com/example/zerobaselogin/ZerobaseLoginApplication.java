package com.example.zerobaselogin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@SpringBootApplication
public class ZerobaseLoginApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZerobaseLoginApplication.class, args);
    }

}
