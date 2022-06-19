package com.UVCLabs.uvcportalbackend.api.controller;

import com.UVCLabs.uvcportalbackend.UvcPortalBackendApplication;
import com.UVCLabs.uvcportalbackend.api.dto.requests.UserRegisterReqDTO;
import com.UVCLabs.uvcportalbackend.api.dto.requests.UserUpdateReqDTO;
import com.UVCLabs.uvcportalbackend.api.dto.response.UserResponseDTO;
import com.UVCLabs.uvcportalbackend.domain.models.User;
import com.UVCLabs.uvcportalbackend.domain.repository.UserRepository;
import com.UVCLabs.uvcportalbackend.domain.service.ManageUserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1")
public class UserController {
    private static final Logger LOGGER= LoggerFactory.getLogger(UvcPortalBackendApplication.class);
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ManageUserService manageUserService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("admin/users/listAll")
    public List<UserResponseDTO> listAll(){
        return toUserCollectionDTO(userRepository.findAll());
    }

    @PostMapping("/users/create")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDTO saveUser(@Valid @RequestBody UserRegisterReqDTO userRegisterReqDTO) {
        User user = manageUserService.saveUser(toDomain(userRegisterReqDTO));
        return toUserDTOResponse(user);
    }

    @GetMapping("admin/users/find/{userId}")
    public ResponseEntity<UserResponseDTO> findUser(@PathVariable Long userId) {
        Optional<User> user = userRepository.findById(userId);
        LOGGER.info("Searching user with id {}", userId);
        if(user.isPresent()) {
            return ResponseEntity.ok( toUserDTOResponse(user.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("admin/users/{userId}")
    public ResponseEntity<UserResponseDTO> updateUser(@Valid  @PathVariable Long userId, @RequestBody UserUpdateReqDTO userUpdateReqDTO){
        Optional<User> user = userRepository.findById(userId);
        if(!user.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        LOGGER.info("Updating user with id {}", userId);
        User updatedUser = manageUserService.updateUser(user.get(), userUpdateReqDTO);
        LOGGER.info("User updated successfully");
        return ResponseEntity.ok(toUserDTOResponse(updatedUser));
    }

    @DeleteMapping("admin/users/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId){
        if(!userRepository.existsById(userId)) {
            return ResponseEntity.notFound().build();
        }
        manageUserService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

    public UserResponseDTO toUserDTOResponse(User com) {
        return modelMapper.map(com, UserResponseDTO.class);
    }

    private User toDomain(UserRegisterReqDTO userRegisterReqDTO) {
        User user = modelMapper.map(userRegisterReqDTO, User.class);
        user.setLastLogin(null);
        user.setRegisteredAt(LocalDateTime.now());
        return user;
    }

    private List<UserResponseDTO> toUserCollectionDTO(List<User> users){
        return users.stream()
                .map(user -> toUserDTOResponse(user))
                .collect(Collectors.toList());
    }

}
