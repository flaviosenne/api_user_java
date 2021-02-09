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
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(path = "/find")
    public ResponseEntity<List<User>> findByName(@RequestParam String name){
        return ResponseEntity.status(200).body(userService.findByName(name));
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
