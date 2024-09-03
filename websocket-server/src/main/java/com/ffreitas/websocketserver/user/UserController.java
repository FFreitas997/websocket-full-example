package com.ffreitas.websocketserver.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Users", description = "APIs for managing users")
public class UserController {

    private final UserService userService;

    @MessageMapping("/user.addUser")
    @SendTo("/user/public")
    @Operation(summary = "Add a user", description = "Add a user to the list of connected users")
    public User saveUser(@Payload User request) {
        userService.save(request);
        return request;
    }

    @MessageMapping("/user.disconnectUser")
    @SendTo("/user/public")
    @Operation(summary = "Disconnect a user", description = "Disconnect a user from the list of connected users")
    public User disconnect(@Payload User request) {
        userService.disconnect(request);
        return request;
    }

    @GetMapping("/users")
    @Operation(summary = "Find connected users", description = "Find all connected users")
    public List<User> findConnectedUsers() {
        return userService.findConnectedUsers();
    }
}
