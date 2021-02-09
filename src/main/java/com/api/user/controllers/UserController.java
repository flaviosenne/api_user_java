package com.api.user.controllers;

import com.api.user.utils.DateUtil;

import java.time.LocalDateTime;
import java.util.List;

import com.api.user.domain.User;
import com.api.user.requests.UserPostRequestBody;
import com.api.user.requests.UserPutRequestBody;
import com.api.user.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("users")
@Log4j2
public class UserController {
    @Autowired
    private DateUtil dateUtil;
    
    @Autowired
    private UserService userService;
    
  
    @GetMapping
    public ResponseEntity<List<User>> list(){
        log.info(this.dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return new ResponseEntity<>(this.userService.listAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<User> listById(@PathVariable Long id){
        log.info(this.dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return ResponseEntity.ok(this.userService.findByIdOrThrowBadRequestException(id));
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody UserPostRequestBody user){
        return ResponseEntity.status(201).body(userService.save(user));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id,  @RequestBody UserPutRequestBody user){
        userService.release(id, user);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
    
}
