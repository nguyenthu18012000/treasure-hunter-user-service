package com.game.user.service.implement;

import com.game.common.dto.request.auth.RegisterRequestDTO;
import com.game.common.dto.response.user.UserResponseDTO;
import com.game.common.exception.CommonException;
import com.game.user.pojo.entity.postgres.UserEntity;
import com.game.user.postgres.UserRepository;
import com.game.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public void register(RegisterRequestDTO request) {

        UserEntity user = userRepository.getUserByUsername(request.getUsername());
        if (user != null) {
            throw new CommonException("100", "User already exists");
        }
        userRepository.insertUser(
                request.getUsername(),
                passwordEncoder.encode(request.getPassword())
        );
    }

    @Override
    public UserResponseDTO login(String username, String password) {
        UserEntity user = userRepository.getUserByUsername(username);

        if (user == null || !passwordEncoder.matches(password, user.getPassword())) {
            throw new CommonException("100", "Invalid username or password");
        }

        UserResponseDTO response = new UserResponseDTO();
        response.setId(user.getId());
        response.setUsername(user.getUsername());
        response.setCreatedAt(user.getCreatedAt());

        return response;
    }
}
