package PARK.service.impl;

import PARK.dto.UserDto;
import PARK.entity.User;
import PARK.mapper.UserMapper;
import PARK.repository.UserRepository;
import PARK.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    /**
     * Registers a new user.
     */
    @Override
    public ResponseEntity<UserDto> registerUser(UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        if (user.getIsOwner() == null) {
            user.setIsOwner(false);
        }
        User savedUser = userRepository.save(user);
        return ResponseEntity.ok(userMapper.toDto(savedUser));
    }

    /**
     * Authenticates a user with username and password.
     */
    @Override
    public ResponseEntity<?> loginUser(String username, String password) {
        User loggedInUser = userRepository.findByUsernameAndPassword(username, password);
        if (loggedInUser != null) {
            return ResponseEntity.ok(loggedInUser.getUserId());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password.");
        }
    }

    /**
     * Retrieves a user by their username.
     */
    @Override
    public ResponseEntity<UserDto> getUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            return ResponseEntity.ok(userMapper.toDto(user));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * Retrieves a user by their email.
     */
    @Override
    public ResponseEntity<UserDto> getUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            return ResponseEntity.ok(userMapper.toDto(user));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * Retrieves a user by their ID.
     */
    @Override
    public ResponseEntity<UserDto> getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(value -> ResponseEntity.ok(userMapper.toDto(value))).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
