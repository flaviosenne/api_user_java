package com.api.user.requests;

import lombok.Data;

@Data
public class UserPutRequestBody {
    private Long id;
    private String name; 
    private String email; 
    private String password; 
}
