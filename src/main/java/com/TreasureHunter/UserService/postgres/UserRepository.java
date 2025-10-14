package com.TreasureHunter.UserService.postgres;

import com.TreasureHunter.UserService.pojo.entity.postgres.UserEntity;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.example.jooq.generated.Tables.USERS;

@Service
@RequiredArgsConstructor
public class UserRepository {
    private final DSLContext dsl;

    public List<UserEntity> getAllUsers() {
        return dsl.selectFrom(USERS).fetchInto(UserEntity.class);
    }

    public UserEntity getUserByUsername(String username) {
        return dsl.selectFrom(USERS)
                .where(USERS.USERNAME.eq(username))
                .fetchOneInto(UserEntity.class);
    }

    public void insertUser(String username, String password) {
        dsl.insertInto(USERS)
                .set(USERS.USERNAME, username)
                .set(USERS.PASSWORD, password)
                .set(USERS.CREATED_AT, LocalDateTime.now())
                .returning()
                .fetchOne();
    }
}
