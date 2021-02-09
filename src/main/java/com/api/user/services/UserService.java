package com.api.user.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import com.api.user.domain.User;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService  {
    // private final UserRepository userRepository;
    private static List<User> users;
    
    static {
        users = new ArrayList<>(List.of(new User(1L, "joao", "joao@email", "1234"), new User
        (2L, "joao flavio", "joao@email", "1234")));
    }
    public List<User> listAll(){
        return users;
    }

    public User findById(Long id){
        return users.stream()
        .filter(user -> user.getId().equals(id))
        .findFirst().orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found"));
    }

    public User save(User user){
        user.setId(ThreadLocalRandom.current().nextLong(3, 100000));
        users.add(user);
        return user;
    }

    public void delete(Long id){
        users.remove(this.findById(id));
    }
    public void release(Long id, User user){
        this.delete(id);
        user.setId(id);
        users.add(user);

    }

}
