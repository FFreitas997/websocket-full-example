package com.ffreitas.websocketserver.chatroom;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatRoomService {

    private final ChatRoomRepository repository;

    public Optional<String> getChatRoomID(String senderId, String recipientId, boolean createIfNotExist) {
        return repository.findBySenderIdAndRecipientId(senderId, recipientId)
                .map(ChatRoom::getChatId)
                .or(() -> {
                    if (!createIfNotExist)
                        return Optional.empty();
                    var chatID = createChatID(senderId, recipientId);
                    return Optional.of(chatID);
                });
    }

    private String createChatID(String senderId, String recipientId) {
        var chatID = String.format("%s_%s", senderId, recipientId);

        var senderRecipient = ChatRoom.builder()
                .chatId(chatID)
                .senderId(senderId)
                .recipientId(recipientId)
                .build();

        var recipientSender = ChatRoom.builder()
                .chatId(chatID)
                .senderId(recipientId)
                .recipientId(senderId)
                .build();

        repository.save(senderRecipient);
        repository.save(recipientSender);

        return chatID;
    }
}
