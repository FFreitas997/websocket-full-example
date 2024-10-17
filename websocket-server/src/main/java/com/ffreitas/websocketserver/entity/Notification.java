package com.ffreitas.websocketserver.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
@Document
public class Notification {

    @Id
    private String id;

    private String message;

    private String sender;

    private String receiver;

    private String createdAt;

    private NotificationStatus status;
}
