package com.api.user.repositores;

import java.util.List;

import com.api.user.domain.User;

public interface UserRepository {
    List<User> listAll();
}
