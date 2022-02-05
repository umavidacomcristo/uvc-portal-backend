package com.UVCLabs.uvcportalbackend.api.controller;

import com.UVCLabs.uvcportalbackend.UvcPortalBackendApplication;
import com.UVCLabs.uvcportalbackend.api.models.requests.UserRegisterReqDTO;
import com.UVCLabs.uvcportalbackend.api.models.requests.UserUpdateReqDTO;
import com.UVCLabs.uvcportalbackend.api.models.response.UserRegisterRespDTO;
import com.UVCLabs.uvcportalbackend.domain.models.ServiceStatusEnum;
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

@RestController
@RequestMapping("/general")
public class GeneralController {

    @GetMapping("/status")
    public ResponseEntity<ServiceStatusEnum> getStatus() {
        ServiceStatusEnum service = new ServiceStatusEnum();
        //TODO: implementar mecanismo para validar serviços
        return ResponseEntity.ok(service);
    }


}
