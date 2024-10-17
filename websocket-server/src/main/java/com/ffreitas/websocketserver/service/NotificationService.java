package com.ffreitas.websocketserver.service;

import com.ffreitas.websocketserver.dto.MessageRequest;
import com.ffreitas.websocketserver.entity.NotificationStatus;
import com.ffreitas.websocketserver.repository.NotificationRepository;
import com.ffreitas.websocketserver.entity.Notification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository repository;
    private final SimpMessagingTemplate messagingTemplate;


    public void sendMessage(MessageRequest request) {
        log.info("Sending WS message to {}", request.receiverUsername());

        Notification notification = Notification.builder()
                .sender(request.senderUsername())
                .receiver(request.receiverUsername())
                .message(request.message())
                .createdAt(LocalDateTime.now().toString())
                .build();

        repository.save(notification);

        messagingTemplate.convertAndSendToUser(request.receiverUsername(), "/message", notification);
        // url: /user/{username}/message
    }
}
