package com.UVCLabs.uvcportalbackend.api.exceptionhandler;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Message {

    private Integer status;
    private LocalDateTime date;
    private String title;
}
