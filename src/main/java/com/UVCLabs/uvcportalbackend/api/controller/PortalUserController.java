package com.UVCLabs.uvcportalbackend.api.controller;

import com.UVCLabs.uvcportalbackend.domain.model.PortalUser;
import com.UVCLabs.uvcportalbackend.domain.repository.PortalUserRepository;
import com.UVCLabs.uvcportalbackend.domain.service.ManageUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class PortalUserController {

    @Autowired
    private PortalUserRepository portalUserRepository;
    @Autowired
    private ManageUserService manageUserService;

    @PostMapping("/create")//código 201 created
    @ResponseStatus(HttpStatus.CREATED)
    public PortalUser saveUser(@Valid @RequestBody PortalUser portalUser) {
        return manageUserService.saveUser(portalUser);
    }

    @GetMapping("/find/{clienteId}")
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

}
