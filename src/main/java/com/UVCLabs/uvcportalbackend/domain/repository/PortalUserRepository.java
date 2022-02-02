package com.UVCLabs.uvcportalbackend.domain.repository;

import com.UVCLabs.uvcportalbackend.domain.model.PortalUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PortalUserRepository extends JpaRepository<PortalUser, Long> {

    List<PortalUser> findByFirstName(String firstName);
    List<PortalUser> findByFirstNameContaining(String firstName);
    PortalUser findByEmail(String email);

}
