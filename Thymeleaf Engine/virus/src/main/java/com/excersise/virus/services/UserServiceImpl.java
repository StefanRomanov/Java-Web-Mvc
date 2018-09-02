package com.excersise.virus.services;

import com.excersise.virus.entities.User;
import com.excersise.virus.entities.UserRole;
import com.excersise.virus.entities.models.UserRegisterModel;
import com.excersise.virus.factories.UserRoleFactory;
import com.excersise.virus.repos.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;
    private ModelMapper modelMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRoleService userRoleService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, BCryptPasswordEncoder bCryptPasswordEncoder, UserRoleService userRoleService) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRoleService = userRoleService;
    }


    @Override
    public Boolean register(UserRegisterModel model) {
        if(!model.getPassword().equals(model.getRepeatPassword())){
            return false;
        }

        User user = this.modelMapper.map(model,User.class);

        user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));

        if(this.userRepository.findAll().size() == 0){
            user.getAuthorities().add(this.userRoleService.findRoleByName("ADMIN"));
        } else {
            user.getAuthorities().add(this.userRoleService.findRoleByName("USER"));
        }

        User result = this.userRepository.saveAndFlush(user);

        return result != null;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.getByUsername(username);
    }
}
