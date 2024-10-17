package com.ffreitas.websocketserver.dto;

import java.io.Serializable;

public record MessageRequest(
        String message,
        String senderUsername,
        String receiverUsername
) implements Serializable { }
