package com.UVCLabs.uvcportalbackend.api.controller;

import com.UVCLabs.uvcportalbackend.api.models.PortalUserRegisterInputDTO;
import com.UVCLabs.uvcportalbackend.api.models.PortalUserRegisterResponseDTO;
import com.UVCLabs.uvcportalbackend.domain.models.PortalUser;
import com.UVCLabs.uvcportalbackend.domain.repository.PortalUserRepository;
import com.UVCLabs.uvcportalbackend.domain.service.ManageUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class PortalUserController {
    //TODO: implementar exception handler para validação
    @Autowired
    private PortalUserRepository portalUserRepository;
    @Autowired
    private ManageUserService manageUserService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/listAll")
    public List<PortalUser> listAll(){
        return portalUserRepository.findAll();
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public PortalUserRegisterResponseDTO saveUser(@Valid @RequestBody PortalUserRegisterInputDTO portalUserRegisterInputDTO) {
        PortalUser portalUser = manageUserService.saveUser(toDomain(portalUserRegisterInputDTO));
        return toDTOResponse(portalUser);
    }
    //TODO: retornar PortalUserRegisterResponseDTO
    @GetMapping("/find/{userId}")
    public ResponseEntity<PortalUser> buscar(@PathVariable Long userId) {
        Optional<PortalUser> user = portalUserRepository.findById(userId);
        System.out.println("Buscando usuário" + userId);
        if(user.isPresent()) {
            return ResponseEntity.ok(user.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{userId}")
    public ResponseEntity<PortalUser> updateUser(@Valid  @PathVariable Long clienteId, @RequestBody PortalUser portalUser){
        if(!portalUserRepository.existsById(clienteId)) {
            return ResponseEntity.notFound().build();
        }

        portalUser.setId(clienteId);
        PortalUser updatedPortalUser = manageUserService.saveUser(portalUser);
        return ResponseEntity.ok(updatedPortalUser);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId){
        if(!portalUserRepository.existsById(userId)) {
            return ResponseEntity.notFound().build();
        }
        manageUserService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

    public PortalUserRegisterResponseDTO toDTOResponse(PortalUser com) {
        return modelMapper.map(com, PortalUserRegisterResponseDTO.class);
    }

    private PortalUser toDomain(PortalUserRegisterInputDTO portalUserRegisterInputDTO) {
        PortalUser portalUser = modelMapper.map(portalUserRegisterInputDTO, PortalUser.class);
        portalUser.setLastLogin(null);
        portalUser.setRegisteredAt(LocalDateTime.now());
        return portalUser;
    }

}
