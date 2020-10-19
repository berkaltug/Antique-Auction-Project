package com.scopic.antiqueauction.domain.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class RegistrationRequest {

    @NotBlank(message="username should not be blank")
    @Min(value=8,message = "username can not be shorter than 8 character")
    @Max(value=16,message="username can not be longer than 16 character")
    private String username;
    @NotBlank(message = "email should not be blank")
    @Email(message = "email is in the wrong format")
    private String email;
    @NotBlank(message = "password should not be blank")
    @Max(value=16,message="password can not be longer than 16 character")
    @Min(value=8,message = "password can not be shorter than 8 character")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
