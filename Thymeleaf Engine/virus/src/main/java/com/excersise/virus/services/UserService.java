package com.excersise.virus.services;

import com.excersise.virus.entities.models.UserLoginModel;
import com.excersise.virus.entities.models.UserRegisterModel;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpSession;

public interface UserService extends UserDetailsService {
    Boolean register(UserRegisterModel model);
}
