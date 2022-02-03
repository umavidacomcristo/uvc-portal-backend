package com.UVCLabs.uvcportalbackend.domain.repository;

import com.UVCLabs.uvcportalbackend.domain.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByFirstName(String firstName);
    List<User> findByFirstNameContaining(String firstName);
    User findByEmail(String email);

}
