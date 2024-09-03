package com.ffreitas.websocketserver.chatmessage;

import com.ffreitas.websocketserver.chatroom.ChatRoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatMessageService {

    private final ChatMessageRepository chatMessageRepository;
    private final ChatRoomService chatRoomService;

    public ChatMessage save(ChatMessage chatMessage) {
        var chatID = chatRoomService.getChatRoomID(chatMessage.getSenderId(), chatMessage.getRecipientId(), true)
                .orElseThrow(() -> new IllegalArgumentException("Chat room not found"));

        chatMessage.setChatId(chatID);
        return chatMessageRepository.save(chatMessage);
    }

    public List<ChatMessage> findChatMessages(String senderId, String recipientId) {
        return chatRoomService.getChatRoomID(senderId, recipientId, false)
                .map(chatMessageRepository::findByChatId)
                .orElse(new ArrayList<>());
    }
}
