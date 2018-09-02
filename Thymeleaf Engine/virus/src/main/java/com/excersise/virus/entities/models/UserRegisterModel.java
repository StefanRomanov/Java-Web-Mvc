package com.excersise.virus.entities.models;

import com.excersise.virus.util.GlobalConstants;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UserRegisterModel {
    private String username;
    private String password;
    private String repeatPassword;
    private String email;

    public UserRegisterModel() {
    }

    @NotEmpty
    @Length(min = GlobalConstants.USERNAME_MIN_LENGTH, max = GlobalConstants.PASSWORD_MAX_LENGTH)
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

    @NotEmpty
    @Length(min = GlobalConstants.PASSWORD_MIN_LENGTH, max = GlobalConstants.PASSWORD_MAX_LENGTH)
    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    @Email
    @NotEmpty
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
