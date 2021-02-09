package com.api.user.requests;

import lombok.Data;

@Data
public class UserPostRequestBody {
    private String name;
    private String email;
    private String password;
}
