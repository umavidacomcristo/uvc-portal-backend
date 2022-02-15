package com.UVCLabs.uvcportalbackend.domain.service;

import com.UVCLabs.uvcportalbackend.api.dto.requests.UserUpdateReqDTO;
import com.UVCLabs.uvcportalbackend.domain.exception.BusinessException;
import com.UVCLabs.uvcportalbackend.domain.models.User;
import com.UVCLabs.uvcportalbackend.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ManageUserService {

    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user){
        User userExists = userRepository.findByEmail(user.getEmail());
        if(userExists !=null && !userExists.equals(user)){
            throw new BusinessException("Already exists a registered user using this email address");
        }
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User updateUser(User user, UserUpdateReqDTO userDTO){
        if(user!= null && userDTO!= null){
            user.setFirstName(userDTO.getFirstName());
            user.setLastName(userDTO.getLastName());
            user.setIntro(userDTO.getIntro());
            return userRepository.save(user);
        }
        throw new BusinessException("User cannot be updated");
    }

    public void deleteUser(Long userId) {
        Optional<User> userExists = userRepository.findById(userId);
        if(!userExists.isPresent()){
            throw new BusinessException("User not found");
        }
        userRepository.deleteById(userId);
    }

}
