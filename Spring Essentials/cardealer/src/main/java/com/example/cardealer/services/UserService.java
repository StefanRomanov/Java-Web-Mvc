package com.example.cardealer.services;

import com.example.cardealer.entities.models.dto.UserRegisterModel;

public interface UserService {
    Boolean addUser(UserRegisterModel model);
    Boolean confrimLogin(UserRegisterModel model);
}
