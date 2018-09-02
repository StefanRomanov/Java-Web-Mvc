package com.excersise.virus.entities.models;

import com.excersise.virus.util.GlobalConstants;
import org.apache.tomcat.jni.Global;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

public class UserLoginModel {
    private String username;
    private String password;

    public UserLoginModel() {
    }

    @NotEmpty
    @Length(min = GlobalConstants.USERNAME_MIN_LENGTH, max = GlobalConstants.USERNAME_MAX_LENGTH)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @NotEmpty
    @Length(min = GlobalConstants.PASSWORD_MIN_LENGTH, max = GlobalConstants.PASSWORD_MAX_LENGTH)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
