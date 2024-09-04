package com.ffreitas.websocketserver.user;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @MessageMapping("/user.addUser")
    @SendTo("/user/public")
    public User saveUser(@Payload User request) {
        userService.save(request);
        return request;
    }

    @MessageMapping("/user.disconnectUser")
    @SendTo("/user/public")
    public User disconnect(@Payload User request) {
        userService.disconnect(request);
        return request;
    }

    @GetMapping("/users")
    public List<User> findConnectedUsers() {
        return userService.findConnectedUsers();
    }
}
