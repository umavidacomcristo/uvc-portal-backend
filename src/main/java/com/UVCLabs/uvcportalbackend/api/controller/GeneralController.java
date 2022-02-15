package com.UVCLabs.uvcportalbackend.api.controller;

import com.UVCLabs.uvcportalbackend.domain.models.ServiceStatusEnum;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
