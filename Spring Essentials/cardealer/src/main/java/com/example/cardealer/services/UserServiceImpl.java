package com.example.cardealer.services;

import com.example.cardealer.entities.User;
import com.example.cardealer.entities.models.dto.UserRegisterModel;
import com.example.cardealer.repos.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Boolean addUser(UserRegisterModel model) {
        if(!model.getPassword().equals(model.getConfirmPassword())){
            return false;
        }
        User user = this.modelMapper.map(model,User.class);
        this.userRepository.saveAndFlush(user);

        return true;
    }

    @Override
    public Boolean confrimLogin(UserRegisterModel model) {
        User user = this.userRepository.getFirstByUsernameAndPassword(model.getUsername(),model.getPassword());
        return user != null;
    }
}
