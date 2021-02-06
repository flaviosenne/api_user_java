package api.user.controllers;

import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import api.user.domain.User;
import api.user.services.UserService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "user")
@AllArgsConstructor
public class UserController {
    
    private final UserService userService;


    @GetMapping
    public ResponseEntity<List<User>> get(){
        return ResponseEntity.ok(this.userService.listAll());
    }

    @PostMapping
    public ResponseEntity<User> save(@RequestBody User user){

        return ResponseEntity.status(201).body(this.userService.saveUser(user));
    }
}
