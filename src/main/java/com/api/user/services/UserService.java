package com.api.user.services;

import java.util.List;


import com.api.user.domain.User;
import com.api.user.repositores.UserRepository;
import com.api.user.requests.UserPostRequestBody;
import com.api.user.requests.UserPutRequestBody;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService  {
    
    private final UserRepository userRepository;
    
    public List<User> listAll(){
        return userRepository.findAll();
    }

    public User findByIdOrThrowBadRequestException(Long id){
        return  userRepository.findById(id)
        .orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found"));
    }

    public List<User> findByName(String name){
        List<User> users = userRepository.findByName(name);
        return users;
    }

    public User save(UserPostRequestBody user){
        return userRepository.save(User.builder()
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword()).build());
    }

    public void delete(Long id){
        userRepository.delete(this.findByIdOrThrowBadRequestException(id));
    }

    public void release(Long id, UserPutRequestBody user){
        User userExist = this.findByIdOrThrowBadRequestException(id);
        userRepository.save(User.builder()
                .id(userExist.getId())
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword()).build());

    }

}
