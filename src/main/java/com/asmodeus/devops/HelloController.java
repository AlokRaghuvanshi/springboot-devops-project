package com.asmodeus.devops;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
@RestController
@RequestMapping("/api")
public class HelloController {

    private final JdbcTemplate jdbcTemplate;
    
    public HelloController(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @GetMapping("/hello")
    public ResponseEntity<String> getHello(){
        return ResponseEntity.ok("Hello from our DevOps project!");
    }

    @GetMapping("/db-check")
    public ResponseEntity<String> checkDatabase(){
        return ResponseEntity.ok("connected to DB"+jdbcTemplate.queryForObject("SELECT DATABASE()",String.class));
    }
}