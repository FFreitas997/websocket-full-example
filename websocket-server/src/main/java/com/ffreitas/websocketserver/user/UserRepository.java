package com.ffreitas.websocketserver.user;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByNicknameAndStatus(@NonNull String nickname, @NonNull Status status);

    List<User> findByStatusOrderByNicknameAsc(@NonNull Status status);
}
