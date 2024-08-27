package PARK.service;

import PARK.dto.UserDto;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<UserDto> registerUser(UserDto userDto);

    ResponseEntity<?> loginUser(String username, String password);

    ResponseEntity<UserDto> getUserByUsername(String username);

    ResponseEntity<UserDto> getUserByEmail(String email);

    ResponseEntity<UserDto> getUserById(Long id);
}
