package com.ffreitas.websocketserver.controller;

import com.ffreitas.websocketserver.dto.MessageRequest;
import com.ffreitas.websocketserver.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class MessageController {

    private final NotificationService service;

    @PostMapping("/message")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void sendMessage(@RequestBody MessageRequest request) { service.sendMessage(request); }
}
