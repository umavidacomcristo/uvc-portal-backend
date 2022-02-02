package com.UVCLabs.uvcportalbackend.domain.service;

import com.UVCLabs.uvcportalbackend.domain.exception.BusinessException;
import com.UVCLabs.uvcportalbackend.domain.model.PortalUser;
import com.UVCLabs.uvcportalbackend.domain.repository.PortalUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ManageUserService {

    @Autowired
    private PortalUserRepository portalUserRepository;

    public PortalUser saveUser(PortalUser portalUser){
        PortalUser portalUserExists = portalUserRepository.findByEmail(portalUser.getEmail());
        if(portalUserExists !=null && !portalUserExists.equals(portalUser)){
            throw new BusinessException("Already exists a registered user using this email address");
        }

        return portalUserRepository.save(portalUser);
    }

    public void deleteUser(Long userId) {
        Optional<PortalUser> userExists = portalUserRepository.findById(userId);
        if(!userExists.isPresent()){
            throw new BusinessException("User not found");
        }
        portalUserRepository.deleteById(userId);
    }

}
