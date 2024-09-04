package com.ffreitas.websocketserver.chatmessage;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ChatMessageController {

    private final SimpMessagingTemplate messagingTemplate;
    private final ChatMessageService service;

    @MessageMapping("/chat")
    public void processMessage(@Payload ChatMessage chatMessage) {

        var savedChatMessage = service.save(chatMessage);

        var notification = ChatNotification.builder()
                .id(savedChatMessage.getId())
                .senderId(savedChatMessage.getSenderId())
                .recipientId(savedChatMessage.getRecipientId())
                .content(savedChatMessage.getContent())
                .build();

        messagingTemplate.convertAndSendToUser(chatMessage.getRecipientId(), "/queue/messages", notification);
    }

    @GetMapping("/messages/{senderId}/{recipientId}")
    public List<ChatMessage> getChatMessages(@PathVariable String senderId, @PathVariable String recipientId) {
        return service.findChatMessages(senderId, recipientId);
    }
}
