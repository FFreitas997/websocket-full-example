package com.ffreitas.websocketserver.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository repository;

    public void save(User request) {
        log.info("Saving user: {}", request.getFullName());

        request.setStatus(Status.ONLINE);

        repository.save(request);
    }

    public void disconnect(User user) {
        log.info("Disconnecting user: {}", user.getFullName());

        var storedUser = repository.findByNicknameAndStatus(user.getNickname(), Status.ONLINE)
                .orElseThrow(() -> new IllegalArgumentException("User not found or already disconnected"));

        storedUser.setStatus(Status.OFFLINE);

        repository.save(storedUser);
    }

    public List<User> findConnectedUsers() {
        log.info("Finding all connected users");

        return repository.findByStatusOrderByNicknameAsc(Status.ONLINE);
    }
}
